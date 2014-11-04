package il.co.orentomer.view;

import il.co.orentomer.model.*;
import org.apache.log4j.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Oren Nahum & Tomer Berger
 *	
 */

public class Gui extends JFrame implements Runnable {
	/**
	 * Interacts with user , displaying gui components 
	 * for user to choose which coins to convert
	 */
	static final long serialVersionUID = 0;    
	private static Gui gui = new Gui();      //contains the reference to the singleton object
	//	private static final Color Blue = null;  //background color
	private JFrame guiFrame = new JFrame();  //main frame of Gui
	private JPanel exchangePanel = new JPanel ();   //panel for convert calculations
	private JLabel originLabel = new JLabel("Origin Currency:");   //label component
	private JLabel destLabel = new JLabel("Target Currency:");     //label component

	//Holds currency codes for all coins
	private String[] currencyCodesArray = {
			"New Israeli Shekel <NIS>",
			"American Dollars <USD>", 
			"Great Britain Pounds <GBP>",
			"Japanese Yens <JPY>",
			"Eurpean Union Euros <EUR>",
			"Australian Dollars <AUD>",
			"Canadian Dollars <CAD>",
			"Denemark Krone <DKK>",
			"Norwaygian Krone <NOK>",
			"South African Rands <ZAR>", 
			"Swedish Krones <SEK>", 
			"Switzerland Kronas <CHF>",
			"Jordanian Dinars <JOD>",
			"Lebanon Pounds <LBP>",
	"Egypt Pounds <EGP>"};

	//combo boxes for exchange panel , to hold strings of currency codes. additional temp combobox for swap technique
	private final JComboBox<String> originComboBox = new JComboBox<String>(currencyCodesArray);
	private final JComboBox<String> destComboBox = new JComboBox<String>(currencyCodesArray);
	private final JComboBox<String> temp = new JComboBox<String>();
	private JButton calc = new JButton("Convert");   //buttons
	private JButton swap = new JButton("⇆");		 
	public final JFormattedTextField inputField = new JFormattedTextField();    //user input value to convert

	private JPanel currencyTablePanel = new JPanel();       //panel for currency table

	//array of currency codes and rates for all coins, to be displayed in a table
	private Object [][] tableArray = { 
			{"New Israeli Shekel", "NIS", "1"},
			{"American Dollars", "USD", XmlParse.codeRateMap().get("USD")}, 
			{"Great Britain Pounds", "GBP", XmlParse.codeRateMap().get("GBP")},
			{"Japanese Yens", "JPY", XmlParse.codeRateMap().get("JPY")},
			{"European Union", "EUR", XmlParse.codeRateMap().get("EUR")}, 
			{"Australian Dollars", "AUD", XmlParse.codeRateMap().get("AUD")},
			{"Canadian Dollars", "CAD", XmlParse.codeRateMap().get("CAD")},
			{"Denemark Krones", "DKK", XmlParse.codeRateMap().get("DKK")},
			{"Norwaygian Krones", "NOK", XmlParse.codeRateMap().get("NOK")},
			{"South African Rands", "ZAR", XmlParse.codeRateMap().get("ZAR")},
			{"Swedish Krones", "SEK", XmlParse.codeRateMap().get("SEK")},
			{"Switzerland Krones", "CHF", XmlParse.codeRateMap().get("CHF")}, 
			{"Jordanian Dinars", "JOD", XmlParse.codeRateMap().get("JOD")}, 
			{"Lebanon Pounds", "LBP", XmlParse.codeRateMap().get("LBP")}, 
			{"Egypt Pounds", "EGP", XmlParse.codeRateMap().get("EGP")}
	};

	private String [] tableHeaders = {"Coin", "Currency Code", "Rate"};   //headers of table
	private JTable currenciesTable = new JTable(tableArray, tableHeaders);  //forms table from received headers and array
	private JLabel date = new JLabel("Updated On: " + XmlParse.lastUpdated());  //latest update from xml (read from url)
	private	JTabbedPane tabs = new JTabbedPane();                           //tabbed pane to switch between panels
	private	final JLabel result = new JLabel();                             //result of calculation (conversion)
	private	Logger logger = MainProgram.logger().getLogger("GUI");			//for log4j entries

	private Gui() {
		guiFrame.setResizable(false);         //sets frame to be unresizeable
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //exit program when hit X on window
		guiFrame.setTitle("Oren and Tomer Rates Exchange");           //title of main frame
		guiFrame.setSize(700, 500);
		guiFrame.setLocationRelativeTo(null); //Centers JFrame to middle of the screen

		exchangePanel = new ImagePanel(new ImageIcon("ExchangeRates.jpg").getImage());   //fetch image from folder with ImagePanel class
		ImageIcon icon = new ImageIcon("Icon.jpg");         //get icon jpeg from folder
		guiFrame.setIconImage(icon.getImage());
		try{
			logger.addAppender(new FileAppender(new SimpleLayout(), "logforj.txt"));     //opening for log messages
		}
		catch (IOException e){
			e.printStackTrace();
		}
		logger.info("GUI constructed");   // log4j entry
		originLabel.setForeground(Color.green);
		destLabel.setForeground(Color.green);

		//add elements to exchangePanel
		exchangePanel.add(originLabel);
		exchangePanel.add(originComboBox);
		exchangePanel.add(swap);
		exchangePanel.add(destLabel);
		exchangePanel.add(destComboBox);
		exchangePanel.add(inputField);
		exchangePanel.add(calc);
		inputField.setColumns(20);

		// setting the table to be uneditable
		DefaultTableModel myModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		//configure and add elements to currencyTablePanel
		currencyTablePanel.setLayout(new BorderLayout());
		currencyTablePanel.add(date, BorderLayout.PAGE_END);
		currencyTablePanel.add(currenciesTable.getTableHeader(), BorderLayout.PAGE_START);
		currencyTablePanel.add(currenciesTable, BorderLayout.CENTER);
		currencyTablePanel.setBackground(Color.PINK); //color of panel backgroud.
		guiFrame.setContentPane(tabs);
		//The JFrame uses the BorderLayout layout manager.
		//Put the two JPanels and JButton in different areas.

		guiFrame.add(exchangePanel, "Exchange");  
		guiFrame.add(currencyTablePanel, "Currency Table");
		guiFrame.setVisible(true);
		guiFrame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				//release reference to objects before closing program
				gui = null;
				logger.info("Exit GUI clicked - terminate program");
				e.getWindow().dispose();
			}
		});

		//swaps combo Boxes codes
		swap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				temp.setModel(originComboBox.getModel());
				originComboBox.setModel(destComboBox.getModel());
				destComboBox.setModel(temp.getModel());
			}
		});

		calc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String input = inputField.getText();
				double value = Double.parseDouble(input);

				String originCurrency = (String) originComboBox.getSelectedItem();
				String destCurrency = (String) destComboBox.getSelectedItem();

				//use enum value to depict which coin is referred to
				String originCur = Code.values()[originComboBox.getSelectedIndex()].toString();
				String destCur = Code.values()[destComboBox.getSelectedIndex()].toString();

				//pre calculating arguments to be sent to convert method -> rates and units.
				double originRateNum = Double.parseDouble((XmlParse.getRateFromCodeRateMap(originCur)));
				double destRateNum = Double.parseDouble((XmlParse.getRateFromCodeRateMap(destCur)));
				int originUnit = Integer.parseInt((XmlParse.getUnitFromCodeUnitMap(originCur)));
				int destUnit = Integer.parseInt((XmlParse.getUnitFromCodeUnitMap(destCur)));

				//Calculates coin value in another rate
				double convertedValue = ModelApp.convert(originRateNum, destRateNum, originUnit, destUnit, value);

				//configure and display result on Gui frame (exchange panel) , afterwards adds a logger entry and revalidates the panel
				result.setText(value + " " + originCurrency + " equals to " + " " + convertedValue + " " + destCurrency);
				result.setFont(new Font("Times New Roman", Font.BOLD, 16));
				result.setBackground(Color.blue);
				result.setForeground(Color.CYAN);
				exchangePanel.add(result);
				logger.info("Successfully Calculated");
				exchangePanel.revalidate();
			}
		});
	}

	/**
	 * Returns reference to gui object
	 * @return
	 */
	public static Gui getInstance() {
		if(gui == null)
			return (new Gui());
		return gui;
	}
	/**
	 * 
	 * @return
	 */
	public int getTableArraySize() {
		return tableArray.length;
	} 
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Object getTableArray(int i, int j) {
		return tableArray[i][j];
	}

	/**
	 * setter for tableArray, for updates
	 * @param i
	 * @param j
	 * @param sum
	 * @throws CustomException
	 */
	public void setTableArray(int i, int j, double sum) throws CustomException {
		if (i<0 && j<0 && sum<0)
			throw (new CustomException("invalid arguments"));
		else 
			tableArray[i][j] = sum;
	}

	/**
	 * repaints Gui for updates
	 */
	public void myRepaint(){
		currenciesTable.repaint();
	}


	/**
	 * Override run method in order to thread the object
	 *
	 */
	@Override
	public void run() {
		while (true) {   //infinite loop for thread
			//prevents user from entering anything but digits and decimal point to input field
			if (inputField.getText().isEmpty() || !(Pattern.matches("[0-9.]+", inputField.getText())))
				calc.setEnabled(false);
			else 
				calc.setEnabled(true);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}