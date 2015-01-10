package bobek_oezsoy.algorithm;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import bobek_oezsoy.client.CallbackClient;
import bobek_oezsoy.server.CallbackServer;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class WeightedRoundRobin implements Algorithm {
	@Override
	public CallbackServer getBestServer(
			ConcurrentHashMap<CallbackServer, Vector<CallbackClient>> registered) {
		if (!registered.isEmpty()) {
			CallbackServer bestServer = null;
			
			return bestServer;
		} else
			return null;
	}
}