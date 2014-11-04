package il.co.orentomer.model;

/**
 * @author Oren Nahum and Tomer Berger
 *Interface to be implemented in Model application class
 */


public interface ModelInterface {
	/**
	 * ModelInterface: converts sum of one currency to another
	 * @param originRateNum - getting the rate of the original currency
	 * @param destRateNum - getting the rate of the destination currency
	 * @param originUnit - getting the number of units of the original currency
	 * @param destUnit - getting the number of units of the destination currency
	 * @param amount - getting the amount from the input of the user
	 * @return - return the result of the conversion
	 */
	public double convert(double originRateNum, double destRateNum, int originUnit, int destUnit, double amount);
	/**
	 * updateCurrencies : updates the table of rates
	 * @throws CustomException
	 */
	public void updateCurrencies() throws CustomException;
}