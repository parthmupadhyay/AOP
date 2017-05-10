package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.annotation.Aspect;  // if needed
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint; // if needed

@Aspect
public class RetryAspect 
{
	@Around("execution(public void edu.sjsu.cmpe275.aop.ProfileService.*(..))")
    public Object retry(ProceedingJoinPoint thisJoinPoint) throws Throwable 
	{
        NetworkException exception = null;
        boolean retry=false;
        for (int i = 1; i <= 3; i++) 
        {
        	if(retry)
        	{
        		System.out.println("Network Error Retry #" + (i-1));
        	}
            try 
            {
            	
                return thisJoinPoint.proceed();
            }
            catch (NetworkException e) 
            {
            	System.out.println("Network Exception:"+e.getMessage());
                exception = e;
                retry=true;
            }
        }
        throw exception;
    }
	
}