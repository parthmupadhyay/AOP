package edu.sjsu.cmpe275.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

public class ProfileServiceImpl implements ProfileService{
	
	@Override
	public Profile readProfile(String userId, String profileUserId) throws AccessDeniedExeption, NetworkException 
	{
		System.out.printf("User %s successfully reads user %s's profile\n", userId, profileUserId);
		return null;
	}

	@Override
	public void shareProfile(String userId, String profileUserId, String targetUserId)
			throws AccessDeniedExeption, NetworkException 
	{
		System.out.printf("User %s shares user %s's profile with user %s\n", userId, profileUserId, targetUserId);
	}

	@Override
	public void unshareProfile(String userId, String targetUserId) throws AccessDeniedExeption, NetworkException {
		// TODO Auto-generated method stub
		System.out.printf("User %s unshares his/her own profile with user %s\n", userId, targetUserId);
		
	}

}
