package ignite.spring;

import java.io.Serializable;
import java.util.Random;
import java.util.Set;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

public class InsertDTOs implements Serializable{
	public static void main(String[] args) throws IgniteException {
		IgniteConfiguration cfg = CacheUtil.createCacheConfiguration();
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(cfg)) {
			IgniteCache<Integer, EmployeeDTO> cache = ignite.getOrCreateCache(CacheUtil.cacheName);
			EmployeeDTO dto = new EmployeeDTO();
//			int id = new Random().nextInt();
			int id = 1;
			dto.setEmployed(true);
			dto.setName("John");
			dto.setId(id);
			dto.setSalary(10000);
			EmployeeDTO dto2 = new EmployeeDTO();
//			int id2 = new Random().nextInt();
			int id2 = 2;
			dto2.setEmployed(true);
			dto2.setName("Doe");
			dto2.setId(id2);
			dto2.setSalary(3500);
			cache.put(id, dto);
			cache.put(id2, dto2);
			Set<Integer> keys = CacheUtil.fetchAllKeysFromCache(ignite, cache);
			System.out.println("keys size=" + keys.size());
			if (keys.size() != 2) {
				throw new RuntimeException("keys.size() != 2");
			}
		}
		System.out.println("end InsertDTOs");
	}
}
