package net.anotheria.anoproxy.client.config;

import org.configureme.annotations.Configure;
import org.configureme.annotations.ConfigureMe;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 17:12
 */
@ConfigureMe(allfields = true)
public class ServerConfig {
	@Configure
	private String host;
	@Configure
	private int port;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ServerConfig{" +
				"host='" + host + '\'' +
				", port=" + port +
				'}';
	}
}
