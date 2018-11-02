package net.anotheria.anoproxy.client.config;

import net.anotheria.anoproxy.client.ProxyHelper;
import net.anotheria.anoproxy.shared.ReplyObject;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 17:55
 */
@Ignore
public class NanoPoolTest {
	@Test
	public void testExistingUrl() throws Exception{
		ReplyObject reply = new ProxyHelper().getURLContent("https://eth.nanopool.org/account/0x58485b2e5dc37887bcadd7f7405256bf5d3f866a");

		assertTrue(reply.isSuccess());
		assertEquals(200, reply.getHttpStatusCode());

		String content = new String(reply.getData(), "UTF-8");
		System.out.println(content);
	}

}
