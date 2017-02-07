package com.ieven.ext.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultResponseTest {
	@Test
	public void testWithMsg() {
		DefaultResponse response = new DefaultResponse(0, "测试");
		System.out.println(response);
		assertEquals("测试", response.getMsg());
	}

	@Test
	public void testOnlyCode() {
		DefaultResponse response = new DefaultResponse(0);
		System.out.println(response);
		assertEquals("处理成功", response.getMsg());
	}
}
