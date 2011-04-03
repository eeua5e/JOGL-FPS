/*
 * 
 */
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculatorService.
 */
public class CalculatorService { 
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws RemoteException the remote exception
	 * @throws MalformedURLException the malformed url exception
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException{
		LocateRegistry.createRegistry(2020);
		RemoteCalculator remcalc = new RemoteCalculator();
		Naming.rebind("//localhost:2020/CalcService", remcalc);
	}
}