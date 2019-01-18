package com.handler;

import java.sql.SQLException;

@SuppressWarnings("serial")
public class ServiceException extends Exception {

	public ServiceException(String string, SQLException e) {
		super(string, e);
	}
}