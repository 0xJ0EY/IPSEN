package server;

import java.rmi.RemoteException;

/**
 * Provide: addition, subtraction, multiplication and division of two numbers.
 * 
 * CalculatorImpl implements the methods of the Calculator remote interface.
 * This class will be used to create a skeleton on the server
 * 
 * @author Koen Warner
 * @version 0.1, May 2015	
 *
 */
public class CalculatorImpl implements Calculator {

	protected CalculatorImpl() throws RemoteException {
	}
	
	public long addition(long a, long b) throws RemoteException {
		System.out.println("adding " + a + " and " + b);
		return a+b;
	}
	
	public long subtraction(long a, long b) throws RemoteException {
		System.out.println("subtracting " + b + " from " + a);
		return a-b;
	}
	
	public long multiplication(long a, long b) throws RemoteException {
		System.out.println("multiplying " + a + " and " + b);
		return a*b;
	}
	
	public long division(long a, long b) throws RemoteException {
		System.out.println("dividing " + a + " and " + b);
		return a/b;
	}
	
}
