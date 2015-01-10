package bobek_oezsoy.server;

import bobek_oezsoy.loadBalancer.CallbackLoadBalancer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class Server {
	public static void main(String[] args) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String portNum, registryURL, lookupURL;

		try {
			System.out.println("Enter the RMIregistry port number:");
			portNum = (br.readLine()).trim();
			int RMIPortNum = Integer.parseInt(portNum);
			System.out.println("Enter the RMIregistry IP-Address:");
			String ipaddress = (br.readLine()).trim();
			System.out.println("Enter the server name:");
			String name = (br.readLine()).trim();
			lookupURL = "rmi://" + ipaddress + ":" + RMIPortNum
					+ "/loadBalancer";
			// find the remote object and cast it to an
			// interface object
			CallbackLoadBalancer h = (CallbackLoadBalancer) Naming
					.lookup(lookupURL);
			System.out.println("Lookup completed ");

			CallbackServer callbackObj = new ServerImpl();

			// register for callback
			try {
				h.registerServer(callbackObj);

				registryURL = "rmi://" + ipaddress + ":" + portNum + "/" + name;
				Naming.rebind(registryURL, callbackObj);
			} catch (Exception e) {
			}
			System.out.println("Callback Server ready.");
		} catch (Exception re) {
			System.out.println("Exception in HelloServer.main: " + re);
		}
	}
}