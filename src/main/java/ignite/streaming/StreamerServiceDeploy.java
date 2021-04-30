package ignite.streaming;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.Ignition;

public class StreamerServiceDeploy {
	public static void main (String[] args) {
        // Mark this cluster member as client.
        Ignition.setClientMode(true);
        try (Ignite ignite = Ignition.start(CacheConfig.xmlConfigPath)) {
        	IgniteServices services = ignite.services(ignite.cluster().forServers());
        	services.deployClusterSingleton("wordStreamerServices", new StreamerServiceImpl());
        }

	}
}
