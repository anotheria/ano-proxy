package net.anotheria.anoproxy.server.resource;

import net.anotheria.anoproxy.server.httphelper.HttpHelper;
import net.anotheria.anoproxy.server.httphelper.HttpHelperResponse;
import net.anotheria.anoproxy.shared.ReplyObject;
import net.anotheria.anoproxy.shared.RequestObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 15:43
 */
@Path("/getter")
public class GetterResource {

	private static ProxyConfiguration configuration = new ProxyConfiguration();

	@POST
	public ReplyObject get(RequestObject requestObject){

		System.out.println("Get user request "+requestObject);


		String url = requestObject.getUrl();
		String userAgent = configuration.getRandomUserAgent();

		HttpHelperResponse response = HttpHelper.getResponse("GET", url, userAgent);
		if (!response.isSuccess()){
			return ReplyObject.error(response.getErrorMessage());
		}

		ReplyObject ret = ReplyObject.success();
		ret.setHttpStatusCode(response.getStatusCode());
		ret.setData(response.getData());

		return ret;
	}
}
