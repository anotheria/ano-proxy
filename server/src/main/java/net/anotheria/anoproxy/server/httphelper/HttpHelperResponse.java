package net.anotheria.anoproxy.server.httphelper;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 16:18
 */
public class HttpHelperResponse {
	private boolean success = true;
	private int statusCode;
	private byte[] data;
	private String errorMessage;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
