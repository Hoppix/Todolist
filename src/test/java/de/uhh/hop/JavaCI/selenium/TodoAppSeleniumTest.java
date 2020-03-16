package de.uhh.hop.JavaCI.selenium;

import java.io.File;
import java.util.List;

import de.uhh.hop.JavaCI.JavaCiApplication;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class TodoAppSeleniumTest {
	private final String osName = System.getProperty("os.name").toLowerCase();
	private final int version = 80;
	private int bitArch = 64;
	private int port = JavaCiApplication.PORT;

	@Test
	public void createAndDeleteEntry() throws InterruptedException {
		WebDriver driver = initWebDriver(false);
		
		WebElement titleInput = driver.findElement(By.id("idTitleInput"));
		WebElement textInput = driver.findElement(By.id("idTextInput"));
		WebElement addButton = driver.findElement(By.className("addBtn"));
		WebElement item;
		WebElement doneButton;
		WebElement deleteButton;
		List<WebElement> listItems = driver.findElements(By.tagName("li"));	
		int currentSize = listItems.size();

		Assert.assertEquals(3, currentSize);

		// create a new todolist entry
		titleInput.sendKeys("TestTitle");
		textInput.sendKeys("TestText");
		addButton.click();
		Thread.sleep(100);

		listItems = driver.findElements(By.tagName("li"));
		Assert.assertEquals(currentSize + 1, listItems.size());

		// mark entry as done
		item = listItems.get(listItems.size() - 1);
		doneButton = driver.findElement(By.xpath("//html/body/ul/li[4]/span[2]"));		
		if(doneButton != null) doneButton.click();
		Thread.sleep(100);
		
		// entry should be marked as done
		listItems = driver.findElements(By.tagName("li"));
		item = listItems.get(listItems.size() - 1);
		Assert.assertEquals("true", item.getAttribute("done"));

		// delete entry
		item = listItems.get(listItems.size() - 1);
		deleteButton = driver.findElement(By.xpath("//html/body/ul/li[4]/span"));
		if(deleteButton != null) deleteButton.click();
		Thread.sleep(100);
		
		// entry should be deleted
		listItems = driver.findElements(By.tagName("li"));
		Assert.assertEquals(currentSize, listItems.size());
		
		driver.close();
	}

	private WebDriver initWebDriver(Boolean gui) {
		File seleniumDirectory = new File("selenium");
		System.setProperty("webdriver.chrome.driver",
				seleniumDirectory.getAbsolutePath() + getChromeDriver(osName, version));
		System.setProperty("phantomjs.binary.path",
				seleniumDirectory.getAbsolutePath() + getPhantomJsDriver(osName, version, bitArch));

		WebDriver driver;
		if (gui) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else {
			driver = new PhantomJSDriver();
			driver.manage().window().setSize(new Dimension(1920, 1080));
			driver.manage().window().maximize();
		}

		driver.get("http://localhost:" + port + "/index.html");

		return driver;
	}

	private String getPhantomJsDriver(String osName, int version, int arch) {
		if (osName.contains("windows")) {
			return "/phantomjs" + ".exe";
			//quote phantomjs in paper
		} else {
			return "/phantomjs-" + arch;
		}
	}

	private String getChromeDriver(String osName, int version) {
		if (osName.contains("windows")) {
			return "/chromedriver-" + version + ".exe";
		} else {
			return "/chromedriver-" + version;
		}
	}
}
