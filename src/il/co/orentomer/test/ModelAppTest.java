package il.co.orentomer.test;

import static org.junit.Assert.*;
import il.co.orentomer.model.ModelApp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Oren Nahum and Tomer Berger
 * @throws Exception
 */

public class ModelAppTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//@Test
	//public void testUpdateCurrencies() {
	//}

	/**
	 * Tests convert method of ModelApplication with JUnit 
	 *
	 */
	@Test
	public void testConvert() {
		double num1 = 0;
		double num2 = 0;
		// signature: convert(originRateNum, destRateNum, originUnit, destUnit, value);
		// converting 100 USD to NIS and then back to USD -> assertTrue
		num1 = ModelApp.convert(3.49, 1, 1, 1, 100);
		num2 = ModelApp.convert(1, 3.49, 1, 1, num1);	
		assertTrue(num2 == 120);
	}
}