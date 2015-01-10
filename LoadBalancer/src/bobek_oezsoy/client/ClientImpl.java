package bobek_oezsoy.client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This Client wants to calculate pi with per user defined digits. To do this the
 * client send per RMI the request to calculate to the LoadBalancer. This
 * LoadBalancer doesn't calculate, he just sends the request to one of many
 * Servers.
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class ClientImpl extends UnicastRemoteObject implements Serializable,
		CallbackClient {

	/**
	 * Creates and exports a new UnicastRemoteObject object using an anonymous
	 * port.
	 * <p/>
	 * <p>
	 * The object is exported with a server socket created using the
	 * {@link java.rmi.server.RMISocketFactory} class.
	 * 
	 * @throws java.rmi.RemoteException
	 *             if failed to export object
	 * @since JDK1.1
	 */
	public ClientImpl() throws RemoteException {
	}

	public String notify(String message) {
		String returnMessage = "Call back received: " + message;
		System.out.println(returnMessage);
		return returnMessage;
	}

}