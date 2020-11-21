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

public class SCountry {
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
    @Before
    public void start(){
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver,1000);
    }
    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(500);
        List<WebElement> ListElement = driver.findElements(By.className("row"));
        List<String> Countries = new ArrayList<>();
        List<String> Zones = new ArrayList<>();
        String zone;
        for (int i = 0; i < ListElement.size(); i++) {
            Countries.add(ListElement.get(i).findElement(By.cssSelector("a")).getAttribute("textContent"));
            if (Integer.parseInt(ListElement.get(i).findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent")) != 0)
            {
                System.out.println("У "+Countries.get(i) + " зона не одна");
                ListElement.get(i).findElement(By.cssSelector("a")).click();
                Thread.sleep(500);
                ListElement = driver.findElements(By.cssSelector("#table-zones tr"));
                Zones.clear();
                for (int j = 1; j < ListElement.size()-1; j++) {
                    Zones.add(ListElement.get(j).findElement(By.cssSelector("td:nth-child(3)")).getAttribute("textContent"));
                }
                if (SortArray(Zones))
                {
                    System.out.println("Зоны отсортированы");
                }
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                Thread.sleep(500);
                ListElement = driver.findElements(By.className("row"));
            }
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
