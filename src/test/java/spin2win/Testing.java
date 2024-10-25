package spin2win;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Testing {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Initialize WebDriver and open the target URL
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://app.staging.bm3group.com");
        driver.manage().window().maximize();
    }

    @Test
    public void automateWheelsTest() throws InterruptedException{
        // Login steps
        WebElement emailField = driver.findElement(By.xpath("//input[@id='input-31']"));
        emailField.sendKeys("admin@brandm3dia.com");

        WebElement passwordField = driver.findElement(By.xpath("//input[@id='input-34']"));
        passwordField.sendKeys("brandm3dia");

        WebElement signInButton = driver.findElement(By.xpath("//span[normalize-space()='Sign In']"));
        signInButton.click();
        
        
        // Navigate to Wheels
        WebElement wheelsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='v-list-item__title'][normalize-space()='Wheels']")));
        //WebElement wheelsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='v-list-item__title'][normalize-space()='Wheels']")));
        wheelsTab.click();

        Thread.sleep(5000);
        // Select List of Wheels
        WebElement listOfWheels = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'List of Wheels')]")));
        //WebElement listOfWheels = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'List of Wheels')]")));
        listOfWheels.click();

        Thread.sleep(5000);
        // Search for Staff in the search field
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        //WebElement searchField = driver.findElement(By.xpath("//td[normalize-space()='Staff']"));
        searchField.click();
        Thread.sleep(5000);
        searchField.sendKeys("Staff");

        Thread.sleep(5000);
        // Click on the Staff element in the search results
        WebElement staff = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space()='Staff']")));
        wait.until(ExpectedConditions.elementToBeClickable(staff)).click();

        Thread.sleep(5000);
        // Click the "Copy URL" button
        WebElement copyURLButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Click to copy URL')]")));
        copyURLButton.click();

        Thread.sleep(5000);
        // Open the copied URL in the current browser window
        driver.get("https://app.staging.bm3group.com/spin/staff");

        Thread.sleep(5000);
        // Click on "Spin now" button
        WebElement spinNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='animation']//img")));
        spinNowButton.click();

        Thread.sleep(5000);
        // Wait for the game result to appear, with a maximum wait time of 30 seconds
        // Locator for the win message (Congratulations)
By winMessageLocator = By.xpath("//*[contains(text(),'Congratulations')]");

// Locator for the loss message (Sorry)
By lossMessageLocator = By.xpath("//*[contains(text(),'Sorry')]");

try {
    // Wait up to 30 seconds for either win or loss message to appear
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    if (wait.until(ExpectedConditions.visibilityOfElementLocated(winMessageLocator)) != null) {
        System.out.println("Game Result: Congratulations! You won!");
    }
} catch (TimeoutException e) {
    // If the win message does not appear, check for the loss message
    try {
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(lossMessageLocator)) != null) {
            System.out.println("Game Result: Sorry! You lost the game.");
        }
    } catch (TimeoutException ex) {
        System.out.println("Game Result: Unable to determine outcome.");
    }
}
    }

    @AfterClass
    public void tearDown() {
        {
            driver.quit();
        }
    }
}



