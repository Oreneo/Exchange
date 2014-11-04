package il.co.orentomer.test;

import il.co.orentomer.model.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import scala.xml.Elem;
import scala.xml.XML;

/**
 * @author Oren Nahum and Tomer Berger
 * 
 * Tests the XmlParse methods in order to validate XmlFiles
 * have been read correctly
 */

public class XmlParseTest {

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

	@Test
	public void testGetUnitFromCodeUnitMap() {
	}

	@Test
	public void testGetRateFromCodeRateMap() {
	}

	/**
	 * Tests readXmlFile method of XmlParse with JUnit 
	 *
	 */
	@Test
	public void testReadXmlFile() throws ParserConfigurationException, SAXException, IOException {
		scala.xml.Elem tempXmlFile = (Elem) XML.loadFile("xmlFileCopy.xml");
		XmlParse.readXmlFile();
		assertEquals(tempXmlFile, XmlParse.XmlFile());
	}

	@Test
	public void testWriteToXmlFile() {
	}

	/**
	 * Tests readXmlFromUrl method of XmlParse with JUnit 
	 *
	 */
	@Test
	public void testReadXmlFromUrl() throws ParserConfigurationException, SAXException, IOException {
		scala.xml.Elem tempXmlFile = (Elem) XML.loadFile("xmlFileCopy.xml");
		XmlParse.readXmlFromUrl();
		assertEquals(tempXmlFile, XmlParse.XmlUrl());
	}
}