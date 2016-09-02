package com.ieven.ext.util.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ieven.ext.util.excepiton.BeanValidationException;
import com.ieven.ext.util.util.validation.BeanValidationUtil;

public class BeanValidationUtilTest {
	
	@Test
	public void testOneNull(){
		TestBean bean = new TestBean();
		bean.setAddress("北京");
		try {
			assertEquals("name",BeanValidationUtil.beanValidationIsNotNull(bean));
		} catch (BeanValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMultipleNull(){
		TestBean bean = new TestBean();
		try {
			assertEquals("address,name",BeanValidationUtil.beanValidationIsNotNull(bean));
		} catch (BeanValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
