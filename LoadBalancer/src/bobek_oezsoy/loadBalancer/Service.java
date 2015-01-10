package bobek_oezsoy.loadBalancer;

import bobek_oezsoy.server.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public class Service implements Task<String>, Serializable {
	private String fileName;

	public Service(String fileName) {
		this.fileName = fileName;
	}

	public Service() {
		fileName = "server.properties";
	}

	public boolean createUsage() {
		HashMap<String, Object> usageList = new HashMap<String, Object>();
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory
				.getOperatingSystemMXBean();
		for (Method method : operatingSystemMXBean.getClass()
				.getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.getName().startsWith("get")
					&& Modifier.isPublic(method.getModifiers())) {
				Object value = null;
				try {
					value = method.invoke(operatingSystemMXBean);
				} catch (Exception e) {
					value = null;
				} finally {
					usageList.put(method.getName(), value);
				}
			}
		}
		try {
			saveParamChanges(fileName, usageList);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String execute() {
		return createUsage() ? "The data of the server was saved in the file '"
				+ fileName + "'." : "An error appeard while creating the file.";
	}

	public void saveParamChanges(String fileName, HashMap usageList)
			throws Exception {
		Properties props = new Properties();
		for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) usageList
				.entrySet())
			props.setProperty(entry.getKey(), "" + entry.getValue());

		File f = new File(fileName);
		OutputStream out = new FileOutputStream(f);
		props.store(out, "This is an optional header comment string");
	}
}