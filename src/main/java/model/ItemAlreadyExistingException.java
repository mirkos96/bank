package model;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ItemAlreadyExistingException extends Exception {
	
	private static final long serialVersionUID = 1L;

}
