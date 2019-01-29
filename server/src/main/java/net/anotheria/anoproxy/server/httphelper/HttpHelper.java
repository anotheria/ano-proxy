package net.anotheria.anoproxy.server.httphelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * This help class is a wrapper around apache http client lib.
 *
 * @author lrosenberg
 * @since 15.07.13 11:14
 */
public class HttpHelper {

    /**
     * {@link Logger} instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

	/**
	 * HttpClient instance.
	 */
	private static CloseableHttpClient httpClient = null;

	static{
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(5, TimeUnit.SECONDS);
		ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Charset.forName("UTF-8")).build();

		connectionManager.setDefaultConnectionConfig(connectionConfig);
		connectionManager.setMaxTotal(200);
		connectionManager.setDefaultMaxPerRoute(100);

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(3000).setConnectionRequestTimeout(4000).build();
        httpClient = HttpClients.custom()
				.setKeepAliveStrategy(new ProxyConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connectionManager).build();

        IdleConnectionMonitorThread connectionMonitor = new IdleConnectionMonitorThread(connectionManager);
        connectionMonitor.start();
	}


	/**
	 * Executes a http GET request using the given URL and credentials.
	 *
	 * @param url  the http URL to connect to.
	 * @param userAgent user agent
	 *
	 * @return  the response to the request.
	 * @throws IOException in case of a problem or the connection was aborted
	 * @throws ClientProtocolException in case of an http protocol error
	 */
	public static HttpResponse getHttpResponse(String url, String userAgent) throws IOException {
		HttpGet request = new HttpGet(url);
		request.addHeader("user-agent", userAgent);

		return httpClient.execute(request);
	}

	/**
	 * Check if HttpResponse contains status code 200(SC_OK).
	 * @param response instance of HttpResponse.
	 * @return {@code true} if HttpResponse contains status code 200(SC_OK), {@code false} otherwise.
	 */
	public static boolean isScOk(HttpResponse response) {
		return response != null && response.getStatusLine()!= null &&
				response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
	}

	/**
	 * Get text content from the response if response status code is 200.
	 * @param response instance of HttpResponse.
	 * @return String representation of HttpEntity.
	 * @throws IOException if an I/O error occurs
	 * @see #isScOk(HttpResponse)
	 */
	public static byte[] getResponseContent(HttpResponse response) throws IOException {
		final HttpEntity entity = response.getEntity();
		if (entity != null) {
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				entity.writeTo(out);//call this in any case!!!
				return isScOk(response) ? out.toByteArray() : null;
			} finally {
				//ensure entity is closed.
				EntityUtils.consume(entity);
			}
		} else {
			return null;
		}
	}

	public static HttpHelperResponse getResponse(String method, String url, String userAgent){
		HttpHelperResponse ret = new HttpHelperResponse();

		try {
			HttpResponse response = getHttpResponse(url, userAgent);
			ret.setSuccess(true);
			ret.setStatusCode(response.getStatusLine().getStatusCode());
			if (isScOk(response))
				ret.setData(getResponseContent(response));
		}catch(IOException e){
			ret.setSuccess(false);
			ret.setErrorMessage(e.getMessage());
            LOGGER.error("can't execute http request with method <{}> url <{}>, and userAgent <{}>", method, url, userAgent, e);
		}

		return ret;
	}


}
