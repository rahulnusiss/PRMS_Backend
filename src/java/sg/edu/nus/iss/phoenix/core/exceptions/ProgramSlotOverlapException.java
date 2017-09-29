package sg.edu.nus.iss.phoenix.core.exceptions;

/**
 * ProgramSlotOverlapException This exception will be thrown from DAO object if
 * load, update or delete for one object fails to find the correct row.
 **/

public class ProgramSlotOverlapException extends Exception {
	/**
	 * For eclipse based unique identity
	 */
	private static final long serialVersionUID = -8937329631346507674L;

	/**
	 * Constructor for ProgramSlotOverlapException. The input message is returned in
	 * toString() message.
                      * @param msg
	 */
	public ProgramSlotOverlapException(String msg) {
		super(msg);
	}

}
