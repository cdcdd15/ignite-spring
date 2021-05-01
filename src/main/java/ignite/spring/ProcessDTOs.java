package ignite.spring;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;

public class ProcessDTOs implements Serializable{
	public static void main(String[] args) throws IgniteException {
		IgniteConfiguration cfg = CacheUtil.createCacheConfiguration();
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(cfg)) {
			IgniteCache<Integer, EmployeeDTO> cache = ignite.getOrCreateCache(CacheUtil.cacheName);
			Set<Integer> keys = CacheUtil.fetchAllKeysFromCache(ignite, cache);
			ClusterGroup clusterGroup = ignite.cluster().forRemotes();
			ignite.compute(clusterGroup).withName("event-task").run(new IgniteRunnable() {
		        @Override
		        public void run() {
		        	Map<Integer, EmployeeDTO> dtos = cache.getAll(keys);
		        	for(Integer key:keys) {
		            EmployeeDTO dto = cache.get(key);
						System.out.println("Executing Employee job " + dto.getName());
						dto.setSalary(dto.getSalary() + dto.getSalary()/10);
						cache.put(key, dto);
		        	}
		        }
		    });
		}
		System.out.println("end ProcessDTOs");
	}
}
