package bobek_oezsoy.loadBalancer;

import bobek_oezsoy.algorithm.Algorithm;
import bobek_oezsoy.algorithm.LeastConnection;
import bobek_oezsoy.algorithm.WeightedRoundRobin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class LoadBalancer {
	private Algorithm algorithm;

	public LoadBalancer(String algorithm) {
		useAlgorithm(algorithm);
	}

	public Algorithm getAlgorithm() {
		return this.algorithm;
	}

	// This method starts a RMI registry on the local host, if
	// it does not already exists at the specified port number.
	private void startRegistry(int port) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(port);
	}
	
	public static void main(String[] args) {
		LoadBalancer lb = new LoadBalancer("least connection");
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String portNum, registryURL;
		try {
			System.out.println("Enter the RMIregistry port number:");
			portNum = (br.readLine()).trim();
			int port = Integer.parseInt(portNum);
			lb.startRegistry(port);
			CallbackLoadBalancer exportedObj = new LoadBalancerImpl(
					lb.getAlgorithm());
			registryURL = "rmi://localhost:" + portNum + "/loadBalancer";
			Naming.bind(registryURL, exportedObj);
			System.out.println("Load Balancer ready!");
		}
		catch (Exception re) {
			System.out.println("Exception in HelloServer.main: " + re);
		}
	}

	public void useAlgorithm(String algorithm) {
		if (algorithm.equalsIgnoreCase("least connection"))
			this.algorithm = new LeastConnection();

		if (algorithm.equalsIgnoreCase("Weighted Round-Round"))
			this.algorithm = new WeightedRoundRobin();
	}
}