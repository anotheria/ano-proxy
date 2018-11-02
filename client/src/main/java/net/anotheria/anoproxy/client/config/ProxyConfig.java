package net.anotheria.anoproxy.client.config;

import org.configureme.ConfigurationManager;
import org.configureme.annotations.Configure;
import org.configureme.annotations.ConfigureMe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 17:11
 */
@ConfigureMe(name="anoproxy")
public class ProxyConfig {
	@Configure
	private ServerConfig[] servers;

	private static Logger log = LoggerFactory.getLogger(ProxyConfig.class);

	private Random rnd = new Random(System.nanoTime());

	public ServerConfig getRandomServer(){
		if (servers==null || servers.length==0)
			throw new IllegalStateException("No servers configured");
		return servers[rnd.nextInt(servers.length)];
	}

	public static ProxyConfig get(){
		return ProxyConfigHolder.instance;
	}

	public ServerConfig[] getServers() {
		return servers;
	}

	public void setServers(ServerConfig[] servers) {
		this.servers = servers;
		System.out.println("called setServers "+Arrays.toString(servers));
	}

	@Override
	public String toString() {
		return "ProxyConfig{" +
				"servers=" + Arrays.toString(servers) +
				'}';
	}

	static class ProxyConfigHolder{
		private static ProxyConfig instance;
		static{
			instance = new ProxyConfig();
			try{
				ConfigurationManager.INSTANCE.configure(instance);
			}catch(IllegalArgumentException e){
				log.warn("No anoproxy.json config file found. Continuing with defaults, but it won't work properly");
			}
			log.info("Proxy configured: "+instance);
		}
	}



}
