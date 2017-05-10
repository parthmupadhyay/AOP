package edu.sjsu.cmpe275.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;
import junit.framework.Assert;

public class ProfileServiceTest {

    /***
     * These are dummy test cases. You may add test cases based on your own need.
     */
	public ProfileService profileService;
	@Before
	public void prepare()
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        profileService =  (ProfileService) context.getBean("profileService");
	}
	
    @Test
    public void testUserShareOwnProfile() 
    { 
    	try 
    	{
			profileService.shareProfile("Alice", "Alice", "Bob");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testShareSomeOnesProfile() 
    {
    	try 
    	{
			profileService.shareProfile("Alice", "Carl", "Bob");
			Assert.assertFalse("Failed", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertTrue("Success", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testShareFriendsProfile()
    {
    	try 
    	{
			profileService.shareProfile("Alice", "Alice", "Bob");
			profileService.shareProfile("Bob", "Alice", "Carl");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testShareOwnProfileWithSelf()
    {
    	try 
    	{
			profileService.shareProfile("Alice", "Alice", "Alice");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testShareAlreadySharedProfile()
    {
    	try 
    	{
			profileService.shareProfile("Alice", "Alice", "Bob");
			profileService.shareProfile("Bob", "Alice", "Bob");
			profileService.shareProfile("Alice", "Alice", "Bob");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @Test
    public void testReadUnsharedProfile()
    {
    	try 
    	{
			profileService.readProfile("Alice", "Carl");
			Assert.assertFalse("Failed", true);
		} catch (AccessDeniedExeption e) 
    	{
			System.out.println(e.getMessage());
			Assert.assertTrue("Success", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testReadOwnProfile()
    {
    	try 
    	{
    		profileService.readProfile("Alice", "Alice");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testReadSharedProfile()
    {
    	try 
    	{
    		profileService.shareProfile("Alice", "Alice", "Bob");
    		profileService.readProfile("Bob", "Alice");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testReadPseudoSharedProfile()
    {
    	try 
    	{
    		profileService.shareProfile("Alice", "Alice", "Bob");
    		profileService.shareProfile("Bob", "Alice", "Carl");
    		profileService.readProfile("Carl", "Alice");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testReadAfterUnshare()
    {
    	try 
    	{
    		profileService.shareProfile("Alice", "Alice", "Bob");
    		profileService.unshareProfile("Alice", "Bob");
			profileService.readProfile("Bob", "Alice");
			Assert.assertFalse("Failed", true);
		} catch (AccessDeniedExeption e) 
    	{
			System.out.println(e.getMessage());
			Assert.assertTrue("Success", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testUnshareOwnProfile()
    {
    	try 
    	{
    		profileService.unshareProfile("Alice", "Alice");
			Assert.assertTrue("Success", true);
		} catch (AccessDeniedExeption e) 
    	{
			Assert.assertFalse("Failed", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testUnshareProfileAlreadyNotShared()
    {
    	try 
    	{
    		profileService.unshareProfile("Alice", "Bob");
			Assert.assertFalse("Failed", true);
		} catch (AccessDeniedExeption e) 
    	{
			System.out.println(e.getMessage());
			Assert.assertTrue("Success", true);
    	} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}