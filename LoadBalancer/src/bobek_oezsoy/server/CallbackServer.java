package bobek_oezsoy.server;

import java.rmi.Remote;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public interface CallbackServer extends Remote {
	public <T> T executeTask(Task<T> t) throws java.rmi.RemoteException;
}
