package retail_assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class retail {
	
	WebDriver driver;
	@BeforeMethod
	public void login()
	{
		try
		{
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://retailm1.upskills.in/admin");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
		
	}
	
	@AfterMethod
	public void close()
	{
		driver.quit();
	}
	
	@Test
	public void select_mail_option()
	{
		try
		{
		driver.findElement(By.xpath("//*[@id='menu-marketing']")).click();
		driver.findElement(By.xpath("//li[@id='menu-marketing']/ul/li[4]/a")).click();
		String expected = "Mail";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		System.out.println("The mail option selected successfully");
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}
	
	@Test
	public void send_mail() throws InterruptedException
	{
		try
		{
		driver.findElement(By.xpath("//*[@id='menu-marketing']")).click();
		driver.findElement(By.xpath("//li[@id='menu-marketing']/ul/li[4]/a")).click();
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='input-to']")));
		dropdown.selectByVisibleText("Customer Group");
		driver.findElement(By.xpath("//*[@name='subject[1]']")).sendKeys("Random Subject");
		driver.findElement(By.xpath("//*[@class='note-editable panel-body']")).sendKeys("Random message");
		driver.findElement(By.xpath("//*[@id='button-send']")).click();
		String expected  =  "Your message has been successfully sent!";
		Thread.sleep(4000);
		String actual = driver.findElement(By.xpath("//*[@class='alert alert-success']")).getText();
		System.out.println("Before Assert");
		Assert.assertEquals(actual, expected);
		System.out.println("Mail is sent successfully");
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}

	
	@Test
	public void cancel_mail() throws InterruptedException
	{
		try
		{
		driver.findElement(By.xpath("//*[@id='menu-marketing']")).click();
		driver.findElement(By.xpath("//li[@id='menu-marketing']/ul/li[4]/a")).click();
		driver.findElement(By.xpath("//*[@name='subject[1]']")).sendKeys("Random Subject");
		driver.findElement(By.xpath("//*[@class='note-editable panel-body']")).sendKeys("Random message");
		driver.findElement(By.xpath("//*[@data-original-title='Cancel']")).click();
		Thread.sleep(3000);
		String actual = driver.findElement(By.xpath("//*[@class='note-editable panel-body']/p")).getText();
		String expected ="";
		Assert.assertEquals(actual, expected);
		System.out.println("Mail cancelled successfully");
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}
	
	@Test
	public void select_catalog_product_option()
	{
		try
		{
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		String actual = driver.getTitle();
		String expected = "Products";
		Assert.assertEquals(actual, expected);
		System.out.println("catalog product selected");
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}

	
	@Test
	public void check_add_product_button()
	{
		try
		{
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@data-original-title='Add New']")).click();
		String actual = driver.findElement(By.xpath("//*[@class='panel-title']")).getText();
		String expected = "Add Product";
		Assert.assertEquals(actual, expected);
		System.out.println("Add products button is working");
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}
	
	
	@Test
	public void check_delete_option()
	{
		try
		{
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//table/tbody/tr/td/input[1]")).click();
		driver.findElement(By.xpath("//*[@data-original-title='Delete']")).click();
		driver.switchTo().alert().accept();
		String actual = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]")).getText();
		Assert.assertTrue(actual.contains("Success: You have modified products!"));
		System.out.println("The product has been deleted successfully");
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}
	
	
	@Test
	public void add_product_feature()
	{
		try
		{
		String product_name = "abcdefg";
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@data-original-title='Add New']")).click();
		driver.findElement(By.xpath("//*[@name='product_description[1][name]']")).sendKeys(product_name);
		driver.findElement(By.xpath("//*[@name='product_description[1][meta_title]']")).sendKeys("abcdefg");
		driver.findElement(By.xpath("//*[@class='nav nav-tabs']/li[2]")).click();
		driver.findElement(By.xpath("//*[@id='input-model']")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@form='form-product']")).click();
		String actual = driver.findElement(By.xpath("//*[@class='alert alert-success']")).getText();
		Assert.assertTrue(actual.contains("Success:"));
		driver.findElement(By.xpath("//*[@name='filter_name']")).sendKeys(product_name);
		driver.findElement(By.xpath("//*[@id='button-filter']")).click();
		String actual_1 = driver.findElement(By.xpath("//tbody/tr/td[3]")).getText();
		Assert.assertTrue(actual_1.contains(product_name));
		System.out.println("The new product is added");
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
		
	}
	
	@Test
	public void check_filter_product_name()
	{
		try
		{
		String filter = "abc" ;
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@name='filter_name']")).sendKeys(filter);
		driver.findElement(By.xpath("//*[@id='button-filter']")).click();
		String actual = driver.findElement(By.xpath("//tbody/tr/td[3]")).getText();
		Assert.assertTrue(actual.contains(filter));
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}
	

	@Test
	public void check_filter_model() throws InterruptedException
	{
		try
		{
		String filter ="1234";
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@name='filter_model']")).sendKeys(filter);
		driver.findElement(By.xpath("//*[@id='button-filter']")).click();
		Thread.sleep(2000);
		String actual = driver.findElement(By.xpath("//tbody/tr/td[4]")).getText();
		Assert.assertTrue(actual.contains(filter));
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
		
	}
	
	@Test
	public void check_filter_price() throws InterruptedException
	{
		try
		{
		String filter ="0";
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@name='filter_price']")).sendKeys(filter);
		driver.findElement(By.xpath("//*[@id='button-filter']")).click();
		Thread.sleep(2000);
		String actual = driver.findElement(By.xpath("//tbody/tr/td[5]")).getText();
		Assert.assertTrue(actual.contains(filter));
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}
	
	
	@Test
	public void check_filter_quantity() throws InterruptedException
	{
		try
		{
		String filter ="1";
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@name='filter_quantity']")).sendKeys(filter);
		driver.findElement(By.xpath("//*[@id='button-filter']")).click();
		Thread.sleep(2000);
		String actual = driver.findElement(By.xpath("//tbody/tr/td[6]")).getText();
		Assert.assertTrue(actual.contains(filter));
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
	}
	
	
	@Test
	public void check_filter_status() throws InterruptedException
	{
		try
		{
		driver.findElement(By.xpath("//*[@id='menu-catalog']")).click();
		driver.findElement(By.xpath("//li[@id='menu-catalog']/ul/li[2]/a")).click();
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@name='filter_status']")));
		dropdown.selectByVisibleText("Enabled");
		driver.findElement(By.xpath("//*[@id='button-filter']")).click();
		Thread.sleep(2000);
		String actual = driver.findElement(By.xpath("//tbody/tr/td[7]")).getText();
		Assert.assertTrue(actual.contains("Enabled"));
		}
		catch(Exception e)
		{
			System.out.println("An error occoured : "+e);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
