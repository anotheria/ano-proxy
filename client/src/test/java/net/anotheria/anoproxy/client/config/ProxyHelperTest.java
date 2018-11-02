package net.anotheria.anoproxy.client.config;

import net.anotheria.anoproxy.client.ProxyHelper;
import net.anotheria.anoproxy.shared.ReplyObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 02.11.18 17:43
 */
public class ProxyHelperTest {
	
	@Test
	public void testExistingUrl() throws Exception{
		ReplyObject reply = new ProxyHelper().getURLContent("https://www.moskito.org");

		assertTrue(reply.isSuccess());
		assertEquals(200, reply.getHttpStatusCode());

		String content = new String(reply.getData(), "UTF-8");
		System.out.println(content);
	}

	@Test
	public void testNonExistingUrl(){
		ReplyObject reply = new ProxyHelper().getURLContent("https://www.moskito.org/nonexsitins");

		assertTrue(reply.isSuccess());
		assertEquals(404, reply.getHttpStatusCode());
	}

	@Test
	public void testMalformedUrl(){
		ReplyObject reply = new ProxyHelper().getURLContent("https://www.moskito.orgggg");

		assertFalse(reply.isSuccess());
		
	}
}
