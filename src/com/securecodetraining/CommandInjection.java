package com.securecodetraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

public class CommandInjection {

	
	public void vulnerable() throws IOException {
				
		String input = "/c start dir & netstat"; // value supplied by user input

		Runtime r = Runtime.getRuntime();
		Process p = r.exec("cmd.exe " + input);
		
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}


	}
	
	
	public void safe() {
		
		System.out.println("DIR --> "+System.getProperty("dir"));
		
	    File dir = new File(".");
	    if (!dir.isDirectory()) {
	      System.out.println("Not a directory");
	    } else {
	      for (String file : dir.list()) {
	        System.out.println(file);
	      }
	    }
		  
	}
	
	public void vulnerableProcessBuilder() throws IOException {

		String userInput = "127.0.0.1 && calc.exe";
		String path = "C:\\Windows\\system32\\cmd.exe";
		ProcessBuilder pb = new ProcessBuilder(path);
		pb.command(path,"/c","ping.exe "+userInput);
		Process pingProcess = pb.start();
		
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pingProcess.getInputStream()));
		
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}
		

	}
	

	
	public void safeProcessBuilder() throws IOException {
	
		String userInput = "127.0.0.1 && calc.exe";
		String path = "C:\\Windows\\system32\\cmd.exe";
		ProcessBuilder pb = new ProcessBuilder(path);
		pb.command().add("/c");
		pb.command().add("ping.exe");
		pb.command().add(userInput);
		Process pingProcess = pb.start();
		
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pingProcess.getInputStream()));
		
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}


	}
	


}
