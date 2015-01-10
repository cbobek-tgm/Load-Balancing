package bobek_oezsoy.loadBalancer;

import java.rmi.Remote;
import bobek_oezsoy.server.CallbackServer;
import bobek_oezsoy.client.CallbackClient;
import bobek_oezsoy.server.Task;

/**
 * Callback interface contains following methods register(callBack): send
 * clientcallback obj to server executeTask(Task): send an object to server
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public interface CallbackLoadBalancer extends Remote {

	public void registerServer(CallbackServer server)
			throws java.rmi.RemoteException;

	public void registerClient(CallbackClient client)
			throws java.rmi.RemoteException;

	public void unregisterServer(CallbackServer server)
			throws java.rmi.RemoteException;

	public void unregisterClient(CallbackClient client)
			throws java.rmi.RemoteException;

	public <T> T executeTask(Task<T> t, CallbackClient client)
			throws java.rmi.RemoteException;
}
