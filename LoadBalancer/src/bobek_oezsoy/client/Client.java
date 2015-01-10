package bobek_oezsoy.client;

import bobek_oezsoy.loadBalancer.CallbackLoadBalancer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.rmi.Naming;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class Client {
	public static void main(String args[]) {
		try {
			int RMIPort;
			String hostName;
			InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			System.out.println("Enter the Load Balancer host name/ip address:");
			hostName = br.readLine();
			System.out.println("Enter the Load Balancer port number:");
			String portNum = br.readLine();
			RMIPort = Integer.parseInt(portNum);

			String lookupURL = "rmi://" + hostName + ":" + RMIPort
					+ "/loadBalancer";
			// find the remote object and cast it to an
			// interface object
			CallbackLoadBalancer h = (CallbackLoadBalancer) Naming
					.lookup(lookupURL);
			System.out.println("Lookup completed!");

			CallbackClient callbackObj = new ClientImpl();
			h.registerClient(callbackObj);
			System.out.println("Client session was created successfully!");
			Pi task = new Pi(50);
			BigDecimal pi = h.executeTask(task, callbackObj);
			System.out.println(pi);
			System.out.println("Registered for callback.");

			//h.unregisterClient(callbackObj);
			System.out.println("Client session was closed successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in CallbackClient: " + e);
		}
		System.exit(0);
	}
}