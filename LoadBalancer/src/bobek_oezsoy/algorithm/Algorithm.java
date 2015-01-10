package bobek_oezsoy.algorithm;

import bobek_oezsoy.client.CallbackClient;
import bobek_oezsoy.server.CallbackServer;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public interface Algorithm {
	CallbackServer getBestServer(
			ConcurrentHashMap<CallbackServer, Vector<CallbackClient>> registered);
}