package Package;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginJson {
    WebDriver driver = null;
    @DataProvider(name = "credentials")
    public Object[][] getData() {
        Object[][] obj = {
                {"honeysri365@gmail.com", "HoneySri@55", "valid username and valid password"},
                {"honeysri3655@gmail.com", "HoneySri@55", "invalid username and valid password"},
                {"honeysri365@gmail.com", "", "blank password"},
                {"", "HoneySri@55", "blank username "}



        };
        return obj;
    }
    @BeforeMethod
    public void setUp() throws IOException, ParseException, org.json.simple.parser.ParseException, IOException {
        String chromeDriverPath = "C:\\Software\\chromedriver_ver113\\chromedriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        String LoginDataPath = "src/main/resources/LoginData.json";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        FileReader fr = new FileReader(LoginDataPath);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fr);
        JSONObject jsonObject = (JSONObject) obj;
        String url = (String) jsonObject.get("url");
        System.out.println(url);
        driver.get(url);
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }
    @Test(dataProvider = "credentials")
    public void verifyLoginCredentials(String scenario, String username, String password) throws IOException, org.json.simple.parser.ParseException {



        System.out.println("type=" + scenario + " " + "username= " + username + " " + "password=" + password);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("harshitah566@sasi.ac.in");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Harshi@1578");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String TestCases = "src/main/resources/ValidLogin.json";
        FileReader fr = new FileReader(TestCases);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fr);
        if (scenario.equals("bothCorrect")) {



            System.out.println("login success");



        } else if (scenario.equals("bothWrong")) {
            System.out.println("login fail");
        } else if (scenario.equals("correctUsername")) {
            System.out.println("enter correct uname");
        } else {
            System.out.println("enter correct password");
        }
    }
}



