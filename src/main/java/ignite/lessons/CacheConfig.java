/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ignite.lessons;

import javax.cache.configuration.FactoryBuilder;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import org.apache.ignite.cache.affinity.AffinityUuid;
import org.apache.ignite.configuration.CacheConfiguration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Configuration for the streaming cache to store the stream of words.
 * This cache is configured with sliding window of 1 second, which means that
 * data older than 1 second will be automatically removed from the cache.
 */
public class CacheConfig {
    /**
     * Configure streaming cache.
     */

	public final static String xmlConfigPath = "C:\\javaDev\\apache-ignite\\examples\\config\\example-ignite.xml";
	
}