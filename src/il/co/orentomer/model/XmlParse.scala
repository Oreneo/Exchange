package il.co.orentomer.model

import il.co.orentomer.view._
import scala.xml._
import java.net._
import java.util._
import java.io.IOException
import org.apache.log4j._;
import sun.java2d.loops.CustomComponent

/**
 * XmlParse object : In charge of reading and writing to and from xml data
 * whether it is url or hard copy
 * Holds HashMaps for currency codes to rates, and codes to units
 * Threaded object , for continuous checkup on rates updating from www.boi.org.il/currency.xml
 */

object XmlParse extends Runnable {
	var codeRateMap:Map[String, String] = new HashMap[String, String](15);   //code <--> rate
	var codeUnitMap:Map[String, String] = new HashMap[String, String](15);   //code <--> unit
	var url:URL = null 
	var conn:URLConnection = null
	var XmlUrl:scala.xml.Elem = null
	var XmlFile:scala.xml.Elem = null
	var lastUpdated:String = null
	
	//try to open url of boi site xml readings. throws MalformedURLException if unable to open
	try {
		url = new URL("http://www.boi.org.il/currency.xml")
	}
	catch {
		case e: MalformedURLException => {
			e.printStackTrace
			MainProgram.logger.error("Unable to open url")
		}
	}

	try {
		conn = url.openConnection               
		XmlUrl = XML.load(conn.getInputStream)    		 // xml from site
		scala.xml.XML.save("xmlFileCopy.xml", XmlUrl)    // saves XmlUrl to xmlFileCopy.xml
		XmlFile = XML.loadFile("xmlFileCopy.xml")        // XmlFile loads the same file hardcopy
		lastUpdated = ((XmlFile\\"LAST_UPDATE").text)    // reading from XmlFile....
	}
	catch {
		case e: IOException => {
			e.printStackTrace
			MainProgram.logger.error("Unable to load from xml")
		}
	}

	//Reads from url site to xml elemement
	def readXmlFromUrl():Unit = {   
		conn = url.openConnection
		XmlUrl = XML.load(conn.getInputStream);
	}
	
	//writes from xml element to xml file
	def writeToXmlFile():Unit = {
		scala.xml.XML.save("xmlFileCopy.xml", XmlUrl)
	}
	
	//reads from xml file to xml element
	def readXmlFile():Unit = {            
		XmlFile = XML.loadFile("xmlFileCopy.xml")
	}
	
	def getRateFromCodeRateMap(rate:String):String = {
	  var temp:String = codeRateMap.get(rate);
	  temp;
	}
	
	def getUnitFromCodeUnitMap(rate:String):String = {
	  var temp:String = codeUnitMap.get(rate);
	  temp;
	}
	
	override def run() = {
		codeUnitMap.put("NIS", "1")
		codeRateMap.put("NIS", "1")
		
		while(true)
		{
			// update round.
			try {
				readXmlFromUrl();
				writeToXmlFile();
				readXmlFile();     //only if reading from url successful - copy it
			}
			catch {
				case e: IOException => {
					e.printStackTrace
					MainProgram.logger.error("Unable to load from xml - continue to use last copy")
				}
			}
			
			//updating maps
			for(ob<-(XmlFile\\"CURRENCY")) {
				codeRateMap.put((ob\\"CURRENCYCODE").text, (ob\\"RATE").text)
			}
			
			for(ob<-(XmlFile\\"CURRENCY")) {
				codeUnitMap.put((ob\\"CURRENCYCODE").text, (ob\\"UNIT").text)
			}
			
			lastUpdated = ((XmlFile\\"LAST_UPDATE").text)
			
			//try to update currency table from xml
			try {
				ModelApp.updateCurrencies();
			}
			catch {
				case e: CustomException => {
					e.printStackTrace
					MainProgram.logger.error("Thread error")
				}
			}
			
			MainProgram.logger.info("Updated rates from xml file")
			MainProgram.gui.myRepaint()  //repaint on main object
			try {
				Thread.sleep(30000);
			}
			catch {
				case e: InterruptedException => {
					e.printStackTrace
					MainProgram.logger.error("Thread error")
				}
			}
		}  // while
	}  // run
}