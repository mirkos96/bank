package model;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)

public class ThePasswordDoesNotMatchException extends Exception {
	
	public ThePasswordDoesNotMatchException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
