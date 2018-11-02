package net.anotheria.anoproxy.shared;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 15:44
 */
public class ReplyObject {
	private boolean success = true;
	private int httpStatusCode = 0;
	private String errorMessage;
	private byte[] data;
	private int size;


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static ReplyObject error(String errorMessage) {
		ReplyObject ret = new ReplyObject();
		ret.setErrorMessage(errorMessage);
		ret.setSuccess(false);
		return ret;
	}

	public static ReplyObject success() {
		ReplyObject ret = new ReplyObject();
		ret.setSuccess(true);
		return ret;
	}

	public void setData(byte[] data) {
		this.data = data;
		if (data!=null)
			setSize(data.length);
	}

	public byte[] getData() {
		return data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
