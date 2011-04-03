/*
 * 
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// TODO: Auto-generated Javadoc
/**
 * The Class RemoteCalculator.
 */
public class RemoteCalculator extends UnicastRemoteObject implements Calculator {
	
	/**
	 * Instantiates a new remote calculator.
	 *
	 * @throws RemoteException the remote exception
	 */
	public RemoteCalculator() throws RemoteException {
	} 

	/* (non-Javadoc)
	 * @see Calculator#add(double, double)
	 */
	public double add(double x, double y) throws RemoteException {
		return x + y;
	}

	/* (non-Javadoc)
	 * @see Calculator#subtract(double, double)
	 */
	public double subtract(double x, double y) throws RemoteException {
		return x - y;
	}

	/* (non-Javadoc)
	 * @see Calculator#multiply(double, double)
	 */
	public double multiply(double x, double y) throws RemoteException {
		return x * y;
	}

	/* (non-Javadoc)
	 * @see Calculator#divide(double, double)
	 */
	public double divide(double x, double y) throws RemoteException {
		return x / y;
	}
}