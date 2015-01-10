package bobek_oezsoy.client;

import java.rmi.Remote;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public interface CallbackClient extends Remote {
	public abstract String notify(String message)
			throws java.rmi.RemoteException;
}