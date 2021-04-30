package ignite.lessons;

import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;

public class ServerNodeStartup {
    	
	public final static String xmlConfigPath = CacheConfig.xmlConfigPath;
	
    public static void main(String[] args) throws IgniteException {
		// Mark this cluster member as server node, the default value = false.
        Ignition.setClientMode(false);
    	Ignition.start(xmlConfigPath);
    }
}