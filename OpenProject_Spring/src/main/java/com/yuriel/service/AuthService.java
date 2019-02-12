package com.yuriel.service;

public interface AuthService {
	public int authenticate(String email, String authCode) throws Exception;
	
	public void deleteAuthCode(String email) throws Exception;
}