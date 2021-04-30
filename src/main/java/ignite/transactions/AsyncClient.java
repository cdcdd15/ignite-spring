package ignite.transactions;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.lang.IgniteFuture;

import ignite.streaming.CacheConfig;

public class AsyncClient {

	public static void main(String[] args) {
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(CacheConfig.xmlConfigPath)) {
			CacheConfiguration<Integer, Account> cacheConfiguration = new CacheConfiguration<Integer, Account>();
			cacheConfiguration.setName("accountAsyncCache");
			cacheConfiguration.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
			
			IgniteCache<Integer, Account> igniteCache = ignite.getOrCreateCache(cacheConfiguration);
			
			//porneste modul asincron
			IgniteCache<Integer, Account> asincIgniteCache = igniteCache.withAsync();
			
			//stocheaza o intrare sub forma de pereche cheie-val in cache in mod asinc
			asincIgniteCache.getAndPut(1, new Account(40, 1000));
			
			//obtine Future-ul pentru operatia anterioara
			IgniteFuture<Account> igniteFuture = asincIgniteCache.future();
			
			//asculta in mod asincron ca operatia sa fie completa
			igniteFuture.listen(f -> System.out.println("Valoare din cache obtinuta:" + f.get()));
			
			//asteapta ca operatia asinc sa se intample
			igniteFuture.get();
		}

	}

}
