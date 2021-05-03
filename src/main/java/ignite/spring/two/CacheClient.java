package ignite.spring.two;

import java.util.List;
import java.util.Random;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;


public class CacheClient {
	
	private static String cacheName = "angajatCache";
	
	public static void main(String[] args) throws IgniteException {
		Ignition.setClientMode(true);
		
		try (Ignite ignite = Ignition.start("C:\\javaDev\\apache-ignite\\examples\\config\\example-ignite.xml")) {
			
			//Configure PARTITIONED cache to store 'Angajat' data
			CacheConfiguration<Long, Angajat> angajatCfg = new CacheConfiguration(cacheName);
			angajatCfg.setCacheMode(CacheMode.PARTITIONED);
			angajatCfg.setIndexedTypes(Long.class, Angajat.class);
			IgniteCache<Long, Angajat> angajatCache = ignite.getOrCreateCache(angajatCfg);
			Angajat angajat = new Angajat();
			long id = new Random().nextLong();
			//id = 1L;
			angajat.setId(id);//dc id-ul deja exista in cash nu va adauga intrarea
			angajat.setName("Name9");
			angajatCache.put(angajat.getId(), angajat);
			
			//Get the names of all the employees
			String sql = "SELECT c.name FROM Angajat c";
			QueryCursor<List<?>> cursor = angajatCache.query(new SqlFieldsQuery(sql));
			System.out.println(cursor.getAll());
		}
	}
}
