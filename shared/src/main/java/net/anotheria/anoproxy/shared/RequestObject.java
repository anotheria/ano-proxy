package net.anotheria.anoproxy.shared;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 15:44
 */
public class RequestObject {
	private String url;
	private String userAgent;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		return "RequestObject{" +
				"url='" + url + '\'' +
				", userAgent='" + userAgent + '\'' +
				'}';
	}
}
