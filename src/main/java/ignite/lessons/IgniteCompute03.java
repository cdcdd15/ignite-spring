package ignite.lessons;

import java.util.Arrays;
import java.util.Collection;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.lang.IgniteClosure;

import ignite.streaming.CacheConfig;

public class IgniteCompute03 {
	public static void main(String[] args) throws Exception {
		// Mark this cluster member as server node, the default value = false.
		Ignition.setClientMode(false);
		try (Ignite ignite = Ignition.start(CacheConfig.xmlConfigPath)) {
			IgniteCluster igniteCluster = ignite.cluster();
			ClusterGroup clusterGroup = igniteCluster.forRemotes();
			IgniteCompute igniteCompute = ignite.compute(clusterGroup);
			IgniteClosure<String, Integer> igniteClosure = (String w) -> {
				System.out.println("Executing word: " + w);
				return w.length();
			};
			Collection<Integer> result = igniteCompute.apply(igniteClosure,
					Arrays.asList("Apache Ignite Rules".split(" ")));
			int res = result.stream().mapToInt(i -> i).sum();
			System.out.println("Sum=" + res);
		}
	}
}
