package deliverable2;


import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I would like to see Ruby compilation visualizer  using the BDD mode,
 * So that I can know what is happening in that compiler. 
 * @author Jian Ren
 *
 */

public class deliverable3 {

	static WebDriver driver = new HtmlUnitDriver();
	static WebDriver driver2 = new HtmlUnitDriver();
	
	// Start at the home page for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
		driver2.get("http://lit-bayou-7912.herokuapp.com/");
	}
	
	/**User story 1:
	 * i want to test "tokenization" of the input data
	 * @author davidren
	 */	
	
	// Scenario 1.1:
	// when i typed "x = 3" in the visualizer and click "Tokenize"
	// it will contain the tokens "on_ident","on_int", "on_sp", "on_op",etc.
	@Test
	public void testTokenize1() {
		try {
			driver.findElement(By.tagName("textarea")).sendKeys("x = 3");
			WebElement button = driver.findElement(By.xpath("//input[@name='commit']"));
			button.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String txt = code.getText();
			assertTrue(txt.contains("on_ident"));
			assertTrue(txt.contains("on_int"));
			assertTrue(txt.contains("on_sp"));
			assertTrue(txt.contains("on_op"));	
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Senario 1.2:
	// if the code i typed is "x = y"
	// Then I should NOT see that it contains "on_int"
	@Test
	public void testTokenize2() {
		try {
			driver.findElement(By.tagName("textarea")).sendKeys("x = y");
			WebElement button = driver.findElement(By.name("commit"));
			button.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String txt = code.getText();
			assertFalse(txt.contains("on_int"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Scenario 1.3:
	// When a variable is declared, and printed with "puts"
	// both the declaration and put statement 
	// should be same as ":on_ident" .
	
	@Test
	public void testTokenize3() {
		try{
			driver.findElement(By.tagName("textarea")).sendKeys("x = 3 \nputs x");
			WebElement button = driver.findElement(By.name("commit"));
			button.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String txt = code.getText();
			assertTrue(txt.contains(":on_ident, \"x\"]"));
			assertTrue(txt.contains(":on_ident, \"puts\"]"));
		}catch(NoSuchElementException nseex){
			fail();
		}
	}
		
	/**User story 2:
	 * i want to parse the ruby code
	 * So I can grasp the ruby's abstract syntax tree
	 * @author davidren
	 */	
	
	// Scenario 2.1
	// given that web page is loaded
	// and my code is "x = 3"
	// when I click Parse button
	// we should see that it contains "x", and "4"
	@Test
	public void testParseAssign() {
		try {
			driver.findElement(By.tagName("textarea")).sendKeys("x = 4");
			WebElement button = driver.findElement(By.name("commit")); // how to locate to the 2nd commit other than using xpath
			button.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String txt = code.getText();
			assertTrue(txt.contains("x"));
			assertTrue(txt.contains("4"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	
	// Scenario 2.2	
	// Given the Webpage is loaded
	// and my code is " puts "hail to pitt!""
	// we should see that it both contains "puts" and "hail to pitt!"
	@Test
	public void testParse1() {
		try {
			driver.findElement(By.tagName("textarea")).sendKeys("puts \"hail to pitt!\"");
			WebElement button = driver.findElement(By.xpath("//input[@name='commit'][2]"));
			button.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String txt = code.getText();
			assertTrue(txt.contains("puts"));
			assertTrue(txt.contains("hail to pitt!"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Scenario 2.3
	//given that two ruby sentences are parsed that have different amounts of whitespace 
	//but have same operation
	//the length of the parse should still be same
		@Test
		public void testWhitespace() {
			try{
				driver.findElement(By.tagName("textarea")).sendKeys("x=4");
				WebElement button1 = driver.findElement(By.xpath("//input[@name='commit'][2]"));
				button1.click();
				WebElement code = driver.findElement(By.tagName("code"));
				String txt1 = code.getText();
				
				driver2.findElement(By.id("code_code")).sendKeys("x = 4");
				WebElement button2 = driver2.findElement(By.xpath("//input[@name='commit'][2]"));
				button2.click();
				WebElement code2 = driver2.findElement(By.tagName("code"));
				String txt2 = code2.getText();
				
				assertTrue(txt1.length()== txt2.length());
				
			}catch(NoSuchElementException nseex){
				fail();
			}
		}
		
	
	
	
	/**User Story 3:
	 * As a user/Ruby programmer,
	 * I would like to better understand compilation of my Ruby code,
	 * So that I can know what the result is after the virtual machine is compiled
	 * @author davidren
	 */	
	
	// Scenario 3.1
	// if my code is "a = 1+2*3/4"
	// Then I should see it contains "opt_mult","opt_div","opt_plus"
	@Test
	public void testCompileAssignHasOprt() {
		try {
			driver.findElement(By.tagName("textarea")).sendKeys("a = 1+2*3/4");
			WebElement button = driver.findElement(By.xpath("//input[@value='Compile']"));
			button.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String txt = code.getText();
			assertTrue(txt.contains("opt_mult"));
			assertTrue(txt.contains("opt_div"));
			assertTrue(txt.contains("opt_plus"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Scenario 3.2
	// Given that my code is "a = 1+2*3/4"
	// When I click Parse button
	// Then I should see the size is as expected as : 2;
	@Test
	public void testCompile2() {
		try {
			driver.findElement(By.tagName("textarea")).sendKeys("a = 1+2*3/4");
			WebElement button = driver.findElement(By.xpath("//input[@value='Compile']"));
			button.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String txt = code.getText();
			assertTrue(txt.contains("local table (size: 2"));	
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 3.3
	// When i typed "2+3" but without puts statement and click compile
	// Then I should see the put and operator
	@Test
	public void testRunCompile() {
		try {
	        driver.findElement(By.id("code_code")).sendKeys("2+3");
	        WebElement button = driver.findElement(By.xpath("//input[@value='Compile']"));
	        button.click();
	    	WebElement code = driver.findElement(By.tagName("code"));
	    	String txt = code.getText();
	    	assertTrue(txt.contains("putobject 2"));
	    	assertTrue(txt.contains("putobject 3"));
	    	assertTrue(txt.contains("opt_plus"));
	    }  catch (NoSuchElementException newPage) {
	        fail();
	    }		
	
	}
}