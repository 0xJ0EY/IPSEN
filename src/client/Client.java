package client;


import server.Calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Gets a Calculator remote object from the CalculatorServer and uses the methods of this Calculator object.
 * 
 * Compiling the client application
 * To be able to compile the Client class, it must have a reference to the Calculator class in the server project
 * - R-click client project>Build Path>Configure Build Path>Java Build Path...>tab_Projects>Add...: 
 * 	 - Select the server project
 * - Import the Calculator class in this Client class by adding the following import statement:
 * 	 - import nl.hsleiden.rmi.calculator.server.Calculator;
 * - Under the RMI_Calculator_Client project
 * 	- Create directories bin/nl/hsleiden/rmi/calculator/server
 * 	- Add Calculator.class to this directory (copy over from server project)
 * 
 * 
 * Running the Client application
 * - go to the directory RMI_Calculator_Client
 * - on cmd line: java -cp bin nl.hsleiden.rmi.calculator.client.Client
 * 
 * @author Koen Warner
 * @version 0.1, May 2015	
 */
public class Client {
	
	public static void main(String[] args){

			try {
				System.out.println("Getting access to the registry");
				// Get access to the RMI registry on the remote server;
				Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
				System.out.println("Getting the Calculator stub from registry");
				// Get remote Calculator object (the stub) from registry
	            Calculator calcStub= (Calculator) registry.lookup("Calculator");
				// Do remote calculations
	            System.out.println("Performing arithmetics");
				System.out.println("Addition: " + calcStub.addition(10, 5));
				System.out.println("Subtraction: " + calcStub.subtraction(10, 5));
				System.out.println("Multiplication: " + calcStub.multiplication(10, 5));
				System.out.println("Division: " + calcStub.division(10, 5));
				System.out.println("Done!");
			} catch (Exception e) {
				System.out.println("EXCEPTION: " + e);
			}
	}

}
