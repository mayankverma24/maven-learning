package maventutorial.maven_learning;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class day3Test {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("day3-This is Before suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("day3-This is After suite");
	}

	@AfterClass
	public void afclass() {
		System.out.println("day3-This is After class");
	}

	@BeforeClass
	public void bfclass() {
		System.out.println("day3-This is Before class");
	}

	@AfterTest
	public void hjhj() {
		System.out.println("day3-This is After Test");
	}

	@Test(groups = { "smoke" })
	public void af() {
		System.out.println("day3-This is Test 1" + ", Thread ID is " + Thread.currentThread().getId());
	}

	/*
	 * @Test(groups = { "smoke" }, expectedExceptions = { IOException.class,
	 * NullPointerException.class }) public void test2() throws Exception{
	 * System.out.println("day3-This is Test 2" + ", Thread ID is " +
	 * Thread.currentThread().getId()); throw new Exception(); }
	 * 
	 * @Test(expectedExceptions = { IOException.class }) public void test3() throws
	 * Exception{ System.out.println("day3-This is Test 3" + ", Thread ID is " +
	 * Thread.currentThread().getId()); throw new Exception();
	 * 
	 * }
	 */

	@BeforeMethod
	public void bf() {
		System.out.println("day3-This is Before Method");
	}
}
