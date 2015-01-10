package bobek_oezsoy.algorithm;

import bobek_oezsoy.client.CallbackClient;
import bobek_oezsoy.server.CallbackServer;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class LeastConnection implements Algorithm {
	@Override
	public CallbackServer getBestServer(
			ConcurrentHashMap<CallbackServer, Vector<CallbackClient>> registered) {
		if (!registered.isEmpty()) {
			int smallest = 0;
			CallbackServer bestServer = null;
			for (Map.Entry<CallbackServer, Vector<CallbackClient>> e : registered
					.entrySet()) {
				if (smallest == 0) {
					smallest = e.getValue().size();
					bestServer = e.getKey();
				} else {
					if (e.getValue().size() < smallest) {
						smallest = e.getValue().size();
						bestServer = e.getKey();
					}
				}
			}
			return bestServer;
		} else
			return null;
	}
}