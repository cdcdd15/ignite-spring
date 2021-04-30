package ignite.lessons;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;

import ignite.streaming.CacheConfig;

public class IgniteCompute01 {
	public static void main(String[] args) throws Exception {
		// Mark this cluster member as server node, the default value = false.
        Ignition.setClientMode(false);
        try (Ignite ignite = Ignition.start(CacheConfig.xmlConfigPath)) {
        	IgniteCompute igniteCompute = ignite.compute();
        	igniteCompute.broadcast(() -> System.out.println("Hello world"));
        }
	}
}
