package il.co.orentomer.model

import il.co.orentomer.view._
import org.apache.log4j._
import javax.swing.SwingUtilities;
import java.io.IOException

	/**
	* Program entry point. Holds Gui and XmlParse threads for continuos checkups
	* Holds logger for log4j entries
	* configures and adds file for log4j
	*/

object MainProgram {
	var gui = Gui.getInstance() 
	var logger = Logger.getLogger("MainProgram")
	
	def main(args:Array[String]) = {
    
		BasicConfigurator.configure()  //log4j basic config
		
		try {
			logger.addAppender(new FileAppender(new SimpleLayout(), "logforj.txt"))
		}
		catch {
			case e: IOException => {
				e.printStackTrace
				logger.error("Logger file not found")
			}	
		}
		var xmlParseThread = new Thread(XmlParse)
		xmlParseThread.start()

		logger.info("xmlParse Thread started")
		SwingUtilities.invokeLater(new Runnable() {
			override def run() : Unit = {
				gui = Gui.getInstance()
				var guiThread = new Thread(gui)
				guiThread.start()
			}
		});
	}
}