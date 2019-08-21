import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test_Sitio_DriverIncluido{

    WebDriver driver = null;
    public String url_QA = "https://www.seleniumhq.org/"; // URL under test
    String titulo_test_esperado = "Selenium Projects";

    @BeforeTest
    public void setUp () {
        System.out.println("Inicio Test...");
    }

    @Test
    public void Selenium_Projects() throws Exception{

        System.out.println("Test: Selenium Projects");

        //   EJECUCION LOCAL (Agregar el driver al projecto para independencia)
        System.setProperty("webdriver.chrome.driver", ".\\web_driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url_QA); // Inicio Sitio
        TimeUnit.SECONDS.sleep(2);

        if(isElementPresent(By.id("menu_projects"))){
            driver.findElement(By.id("menu_projects")).click();
            String titulo_url = driver.getTitle();
            System.out.println("Titulo de pagina: " + titulo_url);
            Assert.assertEquals(titulo_url, titulo_test_esperado);
        }else{
            Assert.fail("Menú Projects no encontrado.");
        }

        driver.navigate().refresh();
        TimeUnit.SECONDS.sleep(2);
    }


    @AfterTest
    public void FinDriver() {
        System.out.println("Fin Test");
        if (driver != null){
            driver.quit();
        }
    }

    // Si el elemento existe en el sitio
    public boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {return false;}
        catch (Exception p){return false;}
    }

}
