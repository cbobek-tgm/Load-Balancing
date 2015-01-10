package bobek_oezsoy.server;

/**
 * 
 * 
 * @author Osman Oezsoy, Christian Bobek
 * @version 09-01-2015
 */
public interface Task<T> {
	public T execute();
}