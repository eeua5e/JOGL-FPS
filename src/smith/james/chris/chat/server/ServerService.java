/*
 *
 */
package smith.james.chris.chat.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerService.
 */
public class ServerService {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws RemoteException the remote exception
	 * @throws MalformedURLException the malformed url exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException{
		LocateRegistry.createRegistry(2020);
		ChatServer chatServ = new ChatServer();
		Naming.rebind("//localhost:2020/chatServ", chatServ);
	}
}
