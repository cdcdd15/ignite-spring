package ignite.lessons;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;

import ignite.streaming.CacheConfig;

public class IgniteCompute02 {
	public static void main(String[] args) throws Exception {
		// Mark this cluster member as server node, the default value = false.
        Ignition.setClientMode(false);
        try (Ignite ignite = Ignition.start(CacheConfig.xmlConfigPath)) {
        	IgniteCluster igniteCluster = ignite.cluster();
        	ClusterGroup clusterGroup = igniteCluster.forRemotes();
        	IgniteCompute igniteCompute = ignite.compute(clusterGroup);
        	igniteCompute.broadcast(() -> System.out.println("Hello world"));
        }
	}
}