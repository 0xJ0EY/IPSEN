package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * The remote interface to a calculator implementation.
 * 
 * Contains the method declarations which a class must implement. These methods will be called by the 
 * client over RMI.
 *  
 * We must extends the Remote interface because this interface will be called remotely in between 
 * the client and server.
 * 
 * Thanks to: java2all, http://java2all.com/technology/rmi/rmi-program/rmi-example, though this code 
 * has been slightly changed.
 * 
 * @author Koen Warner
 * @version 0.1, May 2015
 */
public interface Calculator extends Remote {
	
	// The RemoteException is an exception that can occur when a failure occurs in the RMI process.
	long addition(long a, long b) throws RemoteException;
	long subtraction(long a, long b) throws RemoteException;
	long multiplication(long a, long b) throws RemoteException;
	long division(long a, long b) throws RemoteException;
	
}