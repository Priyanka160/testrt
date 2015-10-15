
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Firefox {

	// Create webdriver interface reference as fields of test class
	WebDriver driver;

	// Call class method setup, and instanciate webdriver interface reference with FirefoxDriver
	@BeforeClass
	public void setUp() throws IOException {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.rtcamp.com/rtmedia/");
	    
	}

	public static String generateRandomChars(int length) {
		String total = "iokijfmnbxcvfrpqsdfgvcxzdferiuytjndifur";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			char _char = total.charAt((int) (Math.random() * 100)
					% total.length());
			buf.append(_char);
		}
		return buf.toString();
	}
	
	// Call class method tearDown, and close firefox driver instance
	@AfterClass
	public void tearDown() throws IOException {
		
		driver.quit();
	}
	
   @Test(priority=1)
	public void signInwithvalidCredential() throws InterruptedException{
		
	   driver.findElement(By.cssSelector("input#bp-login-widget-user-login")).sendKeys("demo");
	   driver.findElement(By.cssSelector("input#bp-login-widget-user-pass")).sendKeys("demo");
	   driver.findElement(By.cssSelector("input#bp-login-widget-submit")).click();
	   Thread.sleep(2000);
	   if(isElementPresent(By.cssSelector("a.logout"))==true){
		   System.out.print("\n User should get successfully logged in");
	   }else{
		   System.out.print("\n unsuccessfully logged in");
	   }
	}
	
   @Test(priority=2)
  	public void Logout() throws InterruptedException{
  		
  	   driver.findElement(By.cssSelector("a.logout")).click();
  	 Thread.sleep(2000);
  	   if(isElementPresent(By.cssSelector("input#bp-login-widget-user-login"))==true){
  		   System.out.print("\n Logout Sucessfully");
  	   }else{
  		   System.out.print("\n Logout Uneucessful");
  	   }
  	}
   
   @Test(priority=3)
	public void signInwithInvalidCredential() throws InterruptedException{
	   driver.get("http://demo.rtcamp.com/rtmedia/");
	   driver.findElement(By.cssSelector("input#bp-login-widget-user-login")).sendKeys("demo");
	   driver.findElement(By.cssSelector("input#bp-login-widget-user-pass")).sendKeys("xyz");
	   driver.findElement(By.cssSelector("input#bp-login-widget-submit")).click();
	   Thread.sleep(2000);
	 String msg =driver.findElement(By.xpath("//div[@id='login_error']")).getText();
	 System.out.print("\n Application displays error message as :"+msg);
	 
	}
   
   @Test(priority=4)
	public void ProcessOfPost() throws InterruptedException, AWTException{
	   driver.get("http://demo.rtcamp.com/rtmedia/");
	   signInwithvalidCredential();
		String txt = "Text";
	   driver.findElement(By.xpath("//*[@id='whats-new']")).sendKeys(txt);
	   driver.findElement(By.xpath("//button[@id='rtmedia-add-media-button-post-update']/span")).click();
	   Robot r = new Robot();
	   r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  
	  //enter jpg in search field
	  r.keyPress(KeyEvent.VK_J);
	  r.keyRelease(KeyEvent.VK_J);
	  r.keyPress(KeyEvent.VK_P);
	  r.keyRelease(KeyEvent.VK_P);
	  r.keyPress(KeyEvent.VK_G);
	  r.keyRelease(KeyEvent.VK_G);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  
	  
	  driver.findElement(By.cssSelector("input#aw-whats-new-submit")).click();
	  Thread.sleep(2000);
	  String name=   driver.findElement(By.xpath("//ul[@id='activity-stream']/li[1]/div[2]/div[2]/p")).getText();
	   if(name.compareTo(txt)==0){
		   System.out.print("\n posted Sucessfully under friends tab");
	   }else{
		   System.out.print("\n Not posted under friends tab");
	   }
	   Logout();
	}
   
   
   @Test(priority=5)
	public void PrivacySettings() throws InterruptedException{
	   driver.get("http://demo.rtcamp.com/rtmedia/");
	   signInwithvalidCredential();
	   String txt = "Text";
	   driver.findElement(By.xpath("//*[@id='whats-new']")).sendKeys(txt);
	   
	   Select s = new Select(driver.findElement(By.id("rtSelectPrivacy")));
	   s.selectByValue("40");//select friends from rtselectprivacy dropdown
	   
	   driver.findElement(By.cssSelector("input#aw-whats-new-submit")).click();
	   Thread.sleep(2000);
	   
	   driver.findElement(By.xpath("//*[@id='activity-friends']/a")).click();//friends tab
	   Thread.sleep(2000);
	try{   
	String name=   driver.findElement(By.xpath("//ul[@id='activity-stream']/li[1]/div[2]/div[2]/p")).getText();
	   if(name.compareTo(txt)==0){
 		   System.out.print("\n posted Sucessfully under friends tab");
 	   }else{
 		   System.out.print("\n Not posted under friends tab");
 	   }}catch(Exception e){
 		  System.out.print("\n Not posted under friends tab");
 	   }
	
	
	driver.findElement(By.xpath("//*[@id='activity-all']/a")).click();//All Member tab
	   Thread.sleep(2000);
	   try{   
			String name=   driver.findElement(By.xpath("//ul[@id='activity-stream']/li[1]/div[2]/div[2]/p")).getText();
			   if(name.compareTo(txt)==0){
		 		   System.out.print("\n posted Sucessfully under All member tab");
		 	   }else{
		 		   System.out.print("\n Not posted under All member tab");
		 	   }}catch(Exception e){
		 		  System.out.print("\n Not posted under All member tab");
		 	   }
	   
	   driver.findElement(By.xpath("//*[@id='activity-groups']/a")).click();//My Group
	   Thread.sleep(2000);
	   try{   
			String name=   driver.findElement(By.xpath("//ul[@id='activity-stream']/li[1]/div[2]/div[2]/p")).getText();
			   if(name.compareTo(txt)==0){
		 		   System.out.print("\n posted Sucessfully under My Group tab");
		 	   }else{
		 		   System.out.print("\n Not posted under My Group tab");
		 	   }}catch(Exception e){
		 		  System.out.print("\n Not posted under My Group tab");
		 	   }
	   Logout();
	}
   
   
   @Test(priority=6)
  	public void UpdateProfile() throws InterruptedException{
	   driver.get("http://demo.rtcamp.com/rtmedia/");
	   signInwithvalidCredential();
	   WebElement element = driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']/a"));
		Actions action = new Actions(driver);
       action.moveToElement(element).build().perform();
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@id='wp-admin-bar-edit-profile']/a")).click();//click on edit profile
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@id='field_1']")).clear();
       driver.findElement(By.xpath("//*[@id='field_1']")).sendKeys("Demo 2");//change name
       driver.findElement(By.xpath("//*[@id='profile-group-edit-submit']")).click();//update
       Thread.sleep(2000);
       String msg = driver.findElement(By.xpath("//*[@id='message']/p")).getText();
       System.out.print("\n Application displays msg as :"+msg);
       String name = driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']/a")).getText();
       System.out.print("\n Application displays name as :"+name);
       driver.findElement(By.xpath("//*[@id='field_1']")).clear();
       driver.findElement(By.xpath("//*[@id='field_1']")).sendKeys("Demo");//reset name
       driver.findElement(By.xpath("//*[@id='profile-group-edit-submit']")).click();//updATE
        msg = driver.findElement(By.xpath("//*[@id='message']/p")).getText();
       System.out.print("\n Application displays msg as :"+msg);
       Logout();
  	}
   
   @Test(priority=7)
 	public void UploadMedia() throws InterruptedException, AWTException{
	   driver.get("http://demo.rtcamp.com/rtmedia/");
	   signInwithvalidCredential();
	   driver.get("http://demo.rtcamp.com/rtmedia/members/demo/media/album/");
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id='rtm_show_upload_ui']")).click();
	   driver.findElement(By.cssSelector("input#rtMedia-upload-button")).click();
	   Robot r = new Robot();
	   r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  r.keyPress(KeyEvent.VK_TAB);
	  r.keyRelease(KeyEvent.VK_TAB);
	  
	  //enter jpg in search field
	  r.keyPress(KeyEvent.VK_J);
	  r.keyRelease(KeyEvent.VK_J);
	  r.keyPress(KeyEvent.VK_P);
	  r.keyRelease(KeyEvent.VK_P);
	  r.keyPress(KeyEvent.VK_G);
	  r.keyRelease(KeyEvent.VK_G);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  
	  Select s = new Select(driver.findElement(By.cssSelector("select.rtmedia-user-album-list")));
	   s.selectByValue("1");//Album
	   
	   Select s1 = new Select(driver.findElement(By.id("rtSelectPrivacy")));
	   s1.selectByValue("40");//PrivacySetting
	   
	   driver.findElement(By.cssSelector("input.start-media-upload")).click();
	   
	  String title= driver.findElement(By.xpath("//*[@id='4853']/a/div[2]/h4")).getAttribute("title");
	   System.out.print("Media name:" + title +" uploaded on wall posts.");
	   Logout();
 	}
   
   
   @Test(priority=8)
	public void UploadURL() throws InterruptedException, AWTException{
	   driver.get("http://demo.rtcamp.com/rtmedia/");
	   signInwithvalidCredential();
	   driver.get("http://demo.rtcamp.com/rtmedia/members/demo/media/album/");
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id='rtm_show_upload_ui']")).click();
	   driver.findElement(By.cssSelector("li.rtm-url-import-tab")).click();
	  driver.findElement(By.cssSelector("textarea#rtmedia_url_upload_input")).sendKeys("https://rtcamp-481283.c.cdn77.org/wp-content/uploads/2013/11/rtCamp-logo-512x512-blue.png");
	  
	 	   
	   Select s1 = new Select(driver.findElement(By.id("rtSelectPrivacy")));
	   s1.selectByValue("40");//PrivacySetting
	   Thread.sleep(8000);
	   driver.findElement(By.cssSelector("input.start-media-upload")).click();
	   Thread.sleep(8000);
	   		driver.findElement(By.xpath("//H4[contains(text(),'Wall Posts')]")).click();
	   		Thread.sleep(5000);
	   String title= driver.findElement(By.xpath("//*[@id='4853']/a/div[2]/h4")).getAttribute("title");
	   System.out.print("Media name:" + title +" uploaded on wall posts.");
	   Logout();
	  
	}
   ///////////////////////////////////////////
   
   /*
    * check Element is present or not
    * 
    */
	public boolean isElementPresent(By locatorKey) {
    try {
        driver.findElement(locatorKey);
        return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
        return false;
    }
}

}
