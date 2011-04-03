/*
 * 
 */
import java.rmi.Naming;


// TODO: Auto-generated Javadoc
/**
 * The Class CalculatorClient.
 */
public class CalculatorClient {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		double x = Double.parseDouble(args[1]); 
		double y = Double.parseDouble(args[2]);
		try { 
			Calculator calc = (Calculator) Naming.lookup("rmi://" + args[0] + "/CalcService"); 
			System.out.println("Client bound: OK"); 
			System.out.println(x + " + " + y + " = " + calc.add(x, y)); 
			System.out.println(x + " - " + y + " = " + calc.subtract(x, y)); 
			System.out.println(x + " * " + y + " = " + calc.multiply(x, y)); 
			System.out.println(x + " / " + y + " = " + calc.divide(x, y)); 
		} catch (java.rmi.NotBoundException nbe) {
			System.out.println("Client bound: error: " + nbe);
		} catch (java.net.MalformedURLException mue) { 
			System.out.println("Client bound: error: " + mue); 
		} catch (java.rmi.RemoteException re){
			System.out.println("Remote error: " + re);
		}
	}
}
