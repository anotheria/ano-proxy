package net.anotheria.anoproxy.client.config;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 17:19
 */

public class ProxyConfigTest {
	@Test public void  testRandomServer(){
		ProxyConfig config = ProxyConfig.get();
		ServerConfig server = config.getRandomServer();
		assertNotNull(server);

		System.out.println("server "+server);

	}
}
