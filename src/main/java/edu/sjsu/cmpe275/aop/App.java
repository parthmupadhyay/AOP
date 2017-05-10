package edu.sjsu.cmpe275.aop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ProfileService profileService =  (ProfileService) context.getBean("profileService");
    
        try 
        {
        	//profileService.shareProfile("Alice", "Carl", "Bob");
        	profileService.shareProfile("Alice", "Alice", "Bob");
        	//profileService.shareProfile("Alice", "Alice", "Alice");
        	profileService.shareProfile("Bob", "Alice", "Carl");
        	//profileService.shareProfile("Alice", "Carl", "Bob");
        	//profileService.shareProfile("Carl", "Alice", "Bob");
        	//profileService.shareProfile("Carl", "Alice", "Carl");
        	//profileService.readProfile("Alice", "Alice");
            //profileService.readProfile("Carl", "Alice");
            profileService.unshareProfile("Alice", "Bob");
            profileService.readProfile("Carl", "Alice");
            //profileService.shareProfile("Alice", "Alice", "Carl");
            //profileService.readProfile("Alice","Bob");
            
            //profileService.unshareProfile("Alice", "Bob");
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.close();
    }
}