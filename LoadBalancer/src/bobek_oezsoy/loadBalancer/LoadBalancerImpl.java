package bobek_oezsoy.loadBalancer;

import bobek_oezsoy.algorithm.Algorithm;
import bobek_oezsoy.client.CallbackClient;
import org.apache.log4j.Logger;
import bobek_oezsoy.server.CallbackServer;
import bobek_oezsoy.server.Task;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This Balancer distribute the client request to the Servers.
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class LoadBalancerImpl extends UnicastRemoteObject implements
		CallbackLoadBalancer {

	private ConcurrentHashMap<CallbackServer, Vector<CallbackClient>> registered;
	private Logger log = Logger.getLogger(LoadBalancerImpl.class.getName());
	private Algorithm algorithm;

	/**
	 * Creates and exports a new UnicastRemoteObject object using an anonymous
	 * port.
	 * 
	 * The object is exported with a server socket created using the
	 * {@link java.rmi.server.RMISocketFactory} class.
	 * 
	 * @throws java.rmi.RemoteException
	 *             if failed to export object
	 * @since JDK1.1
	 */
	public LoadBalancerImpl(Algorithm algorithm) throws RemoteException {
		super();
		registered = new ConcurrentHashMap<CallbackServer, Vector<CallbackClient>>();
		this.algorithm = algorithm;
	}

	public synchronized void registerServer(CallbackServer server)
			throws java.rmi.RemoteException {
		if (!registered.containsKey(server)) {
			registered.put(server, new Vector<CallbackClient>());
			log.info("Registered new server ");
		} else {
			log.warn("server already registered ");
		}

	}

	public synchronized void unregisterServer(CallbackServer server)
			throws java.rmi.RemoteException {
		if (registered.containsKey(server)) {
			registered.remove(server);
		}
	}

	public synchronized void registerClient(CallbackClient client)
			throws java.rmi.RemoteException {
		if (!checkIfIn(client))
			registered.get(algorithm.getBestServer(registered)).add(client);

	}

	public synchronized void unregisterClient(CallbackClient client)
			throws java.rmi.RemoteException {
		for (Map.Entry<CallbackServer, Vector<CallbackClient>> e : registered
				.entrySet())
			for (CallbackClient cc : e.getValue())
				if (cc.equals(client))
					registered.get(e.getKey()).remove(cc);
	}

	@Override
	public <T> T executeTask(Task<T> t, CallbackClient client)
			throws RemoteException {
		for (Map.Entry<CallbackServer, Vector<CallbackClient>> e : registered
				.entrySet())
			for (CallbackClient cc : e.getValue())
				if (cc.equals(client))
					return e.getKey().executeTask(t);
		return null;
	}

	private boolean checkIfIn(CallbackClient client) {
		for (Map.Entry<CallbackServer, Vector<CallbackClient>> e : registered
				.entrySet())
			for (CallbackClient cc : e.getValue())
				if (cc.equals(client))
					return true;

		return false;
	}
}