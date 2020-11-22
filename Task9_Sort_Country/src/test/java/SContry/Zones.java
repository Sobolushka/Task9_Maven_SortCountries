package SContry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Zones {
    private WebDriver driver;
    private WebDriverWait wait;
    public boolean SortArray(List<String> array)
    {
        List<String> SortArray = array;
        Collections.sort(SortArray);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != SortArray.get(i))
                return false;
        }
        return true;
    }
    public boolean SortArray(String[] array)
    {
        String[] SortArray = array;
        Arrays.sort(SortArray);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != SortArray[i])
                return false;
        }
        return true;
    }
    @Before
    public void start(){
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver,1000);
    }
    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(500);
        List<WebElement> ListElement = driver.findElements(By.className("row"));
        List<String> Countries = new ArrayList<>();
        List<WebElement> subListElement = new ArrayList<>();
        String zone;
        List<String> Zones = new ArrayList<>();

        for (int i = 0; i < ListElement.size(); i++) {
            Countries.add(ListElement.get(i).findElement(By.cssSelector("a")).getAttribute("textContent"));
                ListElement.get(i).findElement(By.cssSelector("a")).click();
                Thread.sleep(500);
                Zones.clear();
                subListElement = driver.findElements(By.cssSelector("#table-zones tr td:nth-child(3) select option[selected]"));
                for (int j = 0; j < subListElement.size(); j++) {
                    Zones.add(subListElement.get(j).getAttribute("textContent"));
                }
            if (SortArray(Zones))
            {
                System.out.println("У "+Countries.get(i) + " зоны отсортированы");
            }
                driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
                Thread.sleep(500);
                ListElement = driver.findElements(By.className("row"));
        }
        if (SortArray(Countries))
        {
            System.out.println("Страны отсортированы");
        }

    }
    @After
    public void stop(){

        driver.quit();
        driver = null;
    }
}
