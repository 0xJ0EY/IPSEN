package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Provides communication with the RMI clients.
 * 
 * Server creates remote object Calculator and puts it in the RMI registry.
 * Client can ask for this remote object.
 * 
 * Running the Server application
 * - start a termial
 * - go to the directory RMI_Calculator_Server
 * - on cmd line: java -cp bin nl.hsleiden.rmi.calculator.server.Server
 * 
 * @author Koen Warner
 * @version 0.1, May 2015	
 */
public class Server {

	public Server() {
	}
	
	/** 
	 * Run the server functions
	 */
	private void runServer() {
		
		try {
			
			// Create Calculator instance
			CalculatorImpl calcImpl = new CalculatorImpl();
			// Create server calculator skeleton and cast to remote object
			Calculator calcSkeleton = (Calculator) UnicastRemoteObject.exportObject(calcImpl, 0);
			System.out.println("Calculator skeleton created");
			// Start registry. Default port 1099. Run RMI registry on local host
			LocateRegistry.createRegistry(1099);
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
			System.out.println("RMI Registry starter");
			// Register Calculator skeleton with Registry (bind calculator to RMI registry)
			registry.rebind("Calculator", calcSkeleton);
            System.out.println("Calculator skeleton bound");
            System.out.println("Server running...");
            
			// if you'd like to run rmiregistry from the commend line
			//	run it from the project's bin directory, so rmiregistry can find the necessary classes
			
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e);
		}
		
	}
	
	public static void main(String[] args){

		new Server().runServer();
	}
}
