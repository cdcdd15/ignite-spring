package ignite.streaming;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.cache.CacheException;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.IgniteInterruptedException;
import org.apache.ignite.cache.affinity.AffinityUuid;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;

public class StreamerServiceImpl implements Service, StreamerService {

	private static final long serialVersionUID = 2608659923044044612L;
	
	private long words;
	private long startTime;
	
	@IgniteInstanceResource
	private Ignite ignite;

	IgniteCache<AffinityUuid, String> stmCache;

	@Override
	public void cancel(ServiceContext ctx) {
		System.out.println("Service canceled with name: " + ctx.name());

	}

	@Override
	public void init(ServiceContext ctx) throws Exception {
		this.stmCache = ignite.getOrCreateCache(CacheConfig.wordCache());
		System.out.println("Service initializad with name: " + ctx.name());
	}

	@Override
	public void execute(ServiceContext ctx) throws Exception {
		try (IgniteDataStreamer<AffinityUuid, String> stmr = ignite.dataStreamer(stmCache.getName())) {
			this.startTime = System.currentTimeMillis();
			// Stream words from "alice-in-wonderland" book.
			while (!ctx.isCancelled()) {
				InputStream in = StreamWords.class.getResourceAsStream("alice_in_wonderland.txt");
				System.out.println(in);

				try (LineNumberReader rdr = new LineNumberReader(new InputStreamReader(in))) {
					for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
						for (String word : line.split(" "))
							if (!word.isEmpty()) {
								words++;
								// Stream words into Ignite.
								// By using AffinityUuid we ensure that identical
								// words are processed on the same cluster node.
								stmr.addData(new AffinityUuid(word), word);
							}
					}
				}
			}
		}catch (CacheException e) {
			if(!(e.getCause() instanceof IgniteInterruptedException)) {
				throw e;
			}
		}

	}

	@Override
	public long getWordsPerSecond() {
		return words * 1000 / (System.currentTimeMillis() - startTime);
	}

}
