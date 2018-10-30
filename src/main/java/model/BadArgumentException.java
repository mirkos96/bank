package model;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)

public class BadArgumentException extends Exception {
	
	public BadArgumentException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
