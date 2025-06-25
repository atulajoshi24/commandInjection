package com.securecodetraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rce {

	/*
	 * public void vulnerablePing(String userInput) throws IOException {
	 * 
	 * ProcessBuilder pingCommand = new ProcessBuilder("cmd.exe", "ping -c 4 " +
	 * userInput); Process pingProcess = pingCommand.start();
	 * 
	 * BufferedReader stdInput = new BufferedReader(new
	 * InputStreamReader(pingProcess.getInputStream())); String s = null; while ((s
	 * = stdInput.readLine()) != null) { System.out.println(s); }
	 * 
	 * }
	 * 
	 * public void safePing(String userInput) throws IOException {
	 * 
	 * ProcessBuilder pingCommand = new ProcessBuilder("ping","-c 4",userInput);
	 * Process pingProcess = pingCommand.start(); BufferedReader stdInput = new
	 * BufferedReader(new InputStreamReader(pingProcess.getInputStream())); String s
	 * = null; while ((s = stdInput.readLine()) != null) { System.out.println(s); }
	 * 
	 * BufferedReader stdError = new BufferedReader(new
	 * InputStreamReader(pingProcess.getErrorStream())); String error = null; while
	 * ((error = stdError.readLine()) != null) { System.out.println(error); }
	 * 
	 * }
	 */

	public void safeListDirectory(String directoryName) throws IOException {

		ProcessBuilder listDirectoryCommand = new ProcessBuilder("cmd.exe", "/c", "dir", directoryName);
		Process pingProcess = listDirectoryCommand.start();
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pingProcess.getInputStream()));
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		BufferedReader stdError = new BufferedReader(new InputStreamReader(pingProcess.getErrorStream()));
		String error = null;
		while ((error = stdError.readLine()) != null) {
			System.out.println(error);
		}

	}

	public void vulnerableListDirectory(String directoryName) throws IOException {

		// add input validation using Regexes to Whitelist allowed paths

		ProcessBuilder listDirectoryCommand = new ProcessBuilder("cmd.exe", "/c", "dir " + directoryName);
		Process pingProcess = listDirectoryCommand.start();
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pingProcess.getInputStream()));
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		BufferedReader stdError = new BufferedReader(new InputStreamReader(pingProcess.getErrorStream()));
		String error = null;
		while ((error = stdError.readLine()) != null) {
			System.out.println(error);
		}

	}

	public void vulnerableListDirectoryUsingRuntimeApi(String userInput) throws IOException {

		String command = "cmd.exe /c dir " + userInput;

		Process pingProcess = Runtime.getRuntime().exec(command);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pingProcess.getInputStream()));
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		BufferedReader stdError = new BufferedReader(new InputStreamReader(pingProcess.getErrorStream()));
		String error = null;
		while ((error = stdError.readLine()) != null) {
			System.out.println(error);
		}
	}

	public void safeListDirectoryUsingRuntimeApi(String userInput) throws IOException {

		String[] cmdArray = new String[4];
		cmdArray[0] = "cmd.exe";
		cmdArray[1] = "/c";
		cmdArray[2] = "dir";
		cmdArray[3] = userInput;

		Process pingProcess = Runtime.getRuntime().exec(cmdArray);


		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pingProcess.getInputStream()));
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		BufferedReader stdError = new BufferedReader(new InputStreamReader(pingProcess.getErrorStream()));
		String error = null;
		while ((error = stdError.readLine()) != null) {
			System.out.println(error);
		}

	}

}