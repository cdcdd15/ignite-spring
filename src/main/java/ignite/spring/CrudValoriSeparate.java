package ignite.spring;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

public class CrudValoriSeparate {
	public static void main(String[] args) throws IgniteException {
		//se da ca arg cashe name
		CacheConfiguration<?, ?> cacheCfg = new CacheConfiguration("cosminCache");
		cacheCfg.setIndexedTypes(Integer.class, EmployeeDTO.class);
		
		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setLifecycleBeans(new MyLifecycleBean());
		cfg.setPeerClassLoadingEnabled(true);
		cfg.setIgniteInstanceName("ignite-cluster-node");
		cfg.setCacheConfiguration(cacheCfg);
		
		try (Ignite ignite = Ignition.start(cfg)) {
			IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCache");
			cache.put(1, "String_1");
			cache.put(2, "String_2");
			ignite.compute().broadcast(() -> System.out.println(cache.get(1) + " " + cache.get(2)));
		}
		System.out.println("final");
	}
}
