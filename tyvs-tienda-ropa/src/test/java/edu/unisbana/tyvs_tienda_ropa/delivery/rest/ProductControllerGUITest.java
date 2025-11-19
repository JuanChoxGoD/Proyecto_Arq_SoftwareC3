package edu.unisbana.tyvs_tienda_ropa.delivery.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

class ProductControllerSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Asegúrate de tener ChromeDriver en tu PATH
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void shouldDisplayProductsPage() {
        driver.get("http://localhost:8080/products");
        // Busca un elemento visible, por ejemplo un título
        driver.findElement(By.tagName("h1"));
    }
}
