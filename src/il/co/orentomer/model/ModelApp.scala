package il.co.orentomer.model

import il.co.orentomer.view._
//import javax.sql.rowset.spi.XmlReader
import org.xml.sax.XMLReader
import org.omg.CORBA.Object

object ModelApp extends ModelInterface {
  /**
   * implements the ModelInterface methods convert and updateCurrencies
   * In charge of converting from one coin to another and updating table
   */

	var gui = Gui.getInstance()       //reference to Gui for updating components
	
	/**
	* convert method : recieves sum, rates and units for both coins,
	* and converts the rates accordingly
	*/
	def convert(originRateNum:Double,destRateNum:Double,originUnit:Int,destUnit:Int,sum:Double):Double = {
		// if sum<0  enter logger error and exit function
		if (sum<0)
			MainProgram.logger.error("Failed to calculate. Value<0")
		require(sum > 0)
		
		//calculate sum based on unit value and rate
		if ((originUnit!=1) && (destUnit!=1))
			(((originRateNum/destRateNum)*sum)/(originUnit))*destUnit;
		else if (originUnit!=1)
			((originRateNum/destRateNum)*sum)/(originUnit);
		else if (destUnit!=1)
			((originRateNum/destRateNum)*sum)*(destUnit);
		else 
			((originRateNum/destRateNum)*sum);	
	}

	/**
	* updateCurrencies method : updates the table of rates
	* 
	*/
	@throws[CustomException]("Error updating rates table")
	def updateCurrencies():Unit = {
		var i = 0
		for(i<-0 to (gui.getTableArraySize()-1)) {
			var rate = (XmlParse.codeRateMap.get(gui.getTableArray(i, 1))).toDouble
			var unit = (XmlParse.codeUnitMap.get(gui.getTableArray(i, 1))).toDouble
			gui.setTableArray(i, 2, rate/unit)
		}
	}
}