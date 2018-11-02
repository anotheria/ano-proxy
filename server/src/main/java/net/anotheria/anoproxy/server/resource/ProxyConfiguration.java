package net.anotheria.anoproxy.server.resource;

import java.util.Random;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 16:16
 */
public class ProxyConfiguration {
	private static final String USER_AGENTS[] = {
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36",
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.30 Safari/537.36",
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:63.0) Gecko/20100101 Firefox/63.0"
	};

	private transient static Random rnd = new Random(System.nanoTime());

	public String getRandomUserAgent(){
		return USER_AGENTS[rnd.nextInt(USER_AGENTS.length)];
	}
}
