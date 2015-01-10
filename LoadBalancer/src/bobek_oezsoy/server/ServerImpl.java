package bobek_oezsoy.server;

import bobek_oezsoy.loadBalancer.CallbackLoadBalancer;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import bobek_oezsoy.client.CallbackClient;
import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class ServerImpl extends UnicastRemoteObject implements Serializable,
		CallbackServer {

	private Logger log = Logger.getLogger(ServerImpl.class.getName());

	public ServerImpl() throws RemoteException {
		super();

	}

	/**
	 * Executes the task, that implements the Interface Task
	 * 
	 * @param t
	 *            the task, that implements the Interface Task
	 * @return the result of the task
	 */
	@Override
	public <T> T executeTask(Task<T> t) {
		log.info("Server executed task " + t.getClass());
		System.out.println("Server executed task " + t.getClass());
		return t.execute();
	}
}