package il.co.orentomer.model;

/**
 * @author Oren Nahum and Tomer Berger
 * Custom made exception to be thrown when necessary
 * sends msg to super (constructor)
 */

public class CustomException extends Exception {

	static final long serialVersionUID = 0;
	
	public CustomException() {
		super(); 
	}

	/**
	 * Mainly used constructor : sends message to superclass
	 *
	 */
	
	public CustomException(String message) { 
		super(message); 
	}

	public CustomException(Throwable cause) {  
		super(cause);  
	}  

	public CustomException(String message, Throwable cause) {  
		super(message, cause);  
	} 
}