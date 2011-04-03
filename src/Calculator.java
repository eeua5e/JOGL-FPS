/*
 * 
 */
import java.rmi.Remote;
import java.rmi.RemoteException;


// TODO: Auto-generated Javadoc
/**
 * The Interface Calculator.
 */
public interface Calculator extends Remote{
	
	/**
	 * Adds the.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the double
	 * @throws RemoteException the remote exception
	 */
	public double add(double x, double y) throws RemoteException;
	
	/**
	 * Subtract.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the double
	 * @throws RemoteException the remote exception
	 */
	public double subtract(double x, double y) throws RemoteException;
	
	/**
	 * Multiply.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the double
	 * @throws RemoteException the remote exception
	 */
	public double multiply(double x, double y) throws RemoteException;
	
	/**
	 * Divide.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the double
	 * @throws RemoteException the remote exception
	 */
	public double divide(double x, double y) throws RemoteException;
}
