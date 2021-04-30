package ignite.transactions;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.transactions.Transaction;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;

import ignite.streaming.CacheConfig;

public class TransactionClient {
	public static void main(String[] args) {
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(CacheConfig.xmlConfigPath)) {
			CacheConfiguration<Integer, Account> cacheConfiguration = new CacheConfiguration<Integer, Account>();
			cacheConfiguration.setName("accountAsyncCache");
			cacheConfiguration.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
			
			IgniteCache<Integer, Account> igniteCache = ignite.getOrCreateCache(cacheConfiguration);
			
			// porneste (si inchide dupa try-with-resources) tranzactia si updateaza suma din cont
			try(Transaction transaction = ignite.transactions().txStart(TransactionConcurrency.PESSIMISTIC, TransactionIsolation.REPEATABLE_READ)){
				Account account = igniteCache.get(1);
				
				int ballance = account.getBallance();
				
				//deposit 10$ in the account
				account.setBallance(ballance + 10);
				
				igniteCache.put(1, account);
				
				//commit the transaction to the distributed cache
				transaction.commit();
			}
			System.out.println("Contul dupa depozit " + igniteCache.get(1));
		}


	}

}
