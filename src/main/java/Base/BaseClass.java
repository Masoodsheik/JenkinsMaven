package Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	public ExtentReports extents;
	public ExtentTest test;
	public ExtentHtmlReporter htmlreport;
	WebDriver driver;

	@BeforeTest
	public void startReport() {
		htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestingExtentReport.Html");
		extents = new ExtentReports();
		extents.attachReporter(htmlreport);

	}

	@Test
	public void TC_001() {
		test = extents.createTest("Application launch");
		System.setProperty("webdriver.gecko.driver", "E:\\com.Loggers\\driver\\geckodriver.exe");

		driver = new FirefoxDriver();

		driver.get("https://www.phptravels.org/register.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
		driver.findElement(By.xpath("//input[@id='inputFirstName']")).sendKeys("Nayeem");
		String getValues = driver.findElement(By.xpath("//input[@id='inputFirstName']")).getText();
		test.log(Status.PASS, "User enter the values"+getValues);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Syed");

		System.out.println("Log is successfull done ....!!!!");

	}

	@AfterTest
	public void Teardown() {
		driver.close();
		extents.flush();
	}
}
