package net.anotheria.anoproxy.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import net.anotheria.anoproxy.client.config.ProxyConfig;
import net.anotheria.anoproxy.client.config.ServerConfig;
import net.anotheria.anoproxy.shared.ReplyObject;
import net.anotheria.anoproxy.shared.RequestObject;


/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 17:11
 */
public class ProxyHelper {
	private ProxyConfig config = ProxyConfig.get();

	public ReplyObject getURLContent(String url){
		RequestObject request = new RequestObject();
		request.setUrl(url);

		ServerConfig serverConfig = config.getRandomServer();

		String proxyUrl = "http://"+serverConfig.getHost()+":"+serverConfig.getPort()+"/rest/getter";

		Client client = JerseyClientUtil.getClientInstance();
		ClientResponse clientResponse = null;
		try{
			WebResource webResource = client.resource(proxyUrl);
			clientResponse = webResource.header("Content-Type", "application/json;charset=utf-8").accept("application/json")
					.post(ClientResponse.class, request);

			ReplyObject replyObject = clientResponse.getEntity(ReplyObject.class);

			if (clientResponse.getStatus() != 200) {
				return ReplyObject.error("Can't obtain response from proxy server, status: "+clientResponse.getStatus()+", "+clientResponse.getStatusInfo());
			}

			return replyObject;
		}finally {
			if (clientResponse != null) {
				clientResponse.close();
			}
		}
	}
}
