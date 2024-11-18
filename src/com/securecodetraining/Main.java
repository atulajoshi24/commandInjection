package com.securecodetraining;

import java.io.IOException;

public class Main {
	
	public static void main(String args[]) throws IOException {
		
		CommandInjection ci = new CommandInjection();
		//ci.vulnerable1();
		//ci.safe();
		//ci.vulnerable2();
		//ci.safe2();
		//ci.safeProcessBuilder();
		ci.vulnerableProcessBuilder();
	}

}
