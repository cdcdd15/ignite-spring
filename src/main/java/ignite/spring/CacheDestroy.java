package ignite.spring;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

public class CacheDestroy {
	public static void main(String[] args) throws IgniteException {
		IgniteConfiguration cfg = CacheUtil.createCacheConfiguration();
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(cfg)) {
			IgniteCache<Integer, EmployeeDTO> cache = ignite.getOrCreateCache(CacheUtil.cacheName);
			cache.destroy();
		}
		System.out.println("end CacheDestroy");
	}
}
