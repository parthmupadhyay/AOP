package edu.sjsu.cmpe275.aop.aspect;

import org.springframework.beans.factory.annotation.Autowired;  // if needed
import edu.sjsu.cmpe275.aop.ProfileService;
import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;

import org.aspectj.lang.annotation.Aspect;  // if needed
import org.aspectj.lang.annotation.Before;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.JoinPoint;


@Aspect
public class AuthorizationAspect 
{

	private HashMap<String,Set<String>> shareMap=new HashMap<String,Set<String>>();
	@Autowired ProfileService profileService;
	
	@Before("execution(* edu.sjsu.cmpe275.aop.ProfileServiceImpl.readProfile(..))")
	public void beforeRead(JoinPoint joinPoint) throws Throwable
	{
		Object[] args=joinPoint.getArgs();
		String userId=(String)args[0];
		String profileuserId=(String)args[1];
		if(userId.equals(profileuserId))
			return;
		if(shareMap.containsKey(userId))
		{
			Set<String> profileSet=shareMap.get(userId);
			for(String prof:profileSet)
			{
				if(prof.equals(profileuserId))
				{
					return ;
				}
			}
			throw new AccessDeniedExeption("Cannot read, access is denied");
		
		}
		else
			throw new AccessDeniedExeption("Cannot read, access is denied");
		
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.ProfileService.shareProfile(..))")
	public void beforShare(JoinPoint joinPoint) throws AccessDeniedExeption 
	{
		Object[] args=joinPoint.getArgs();
		String userId=(String)args[0];
		String profileuserId=(String)args[1];
		String targetuserId=(String)args[2];
		if(profileuserId.equals(targetuserId))
			return;
		if(!shareMap.containsKey(targetuserId))
		{
			shareMap.put(targetuserId, new HashSet<String>());
		}
		if(userId.equals(profileuserId))
		{
			shareMap.get(targetuserId).add(profileuserId);
			return;
		}
		else
		{
			if(shareMap.containsKey(userId))
			{
				Set<String> shareSet=shareMap.get(userId);
				for(String profid:shareSet)
				{
					if(profid.equals(profileuserId))
					{
						shareMap.get(targetuserId).add(profileuserId);
						return;
					}
				}
				throw new AccessDeniedExeption("Can not share, access is denied");
			}
			else
				throw new AccessDeniedExeption("Can not share, access is denied");
		}
		
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.ProfileService.unshareProfile(..))")
	public void beforeUnshare(JoinPoint joinPoint) throws Throwable 
	{
		Object[] args=joinPoint.getArgs();
		String userId=(String)args[0];
		String targetuserId=(String)args[1];
		if(userId.equals(targetuserId))
			return;
		if(shareMap.containsKey(targetuserId))
		{
			Set<String> shareSet=shareMap.get(targetuserId);
			for(String profid:shareSet )
			{
				if(profid.equals(userId))
				{
					shareSet.remove(profid);
					return;
				}
					
			}
			throw new AccessDeniedExeption("Cannot unshare profile, which is not shared");
		}
		else
			throw new AccessDeniedExeption("Cannot unshare profile, which is not shared");
	}
	
}
