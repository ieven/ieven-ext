package com.ieven.ext.util.util.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.ieven.ext.util.excepiton.BeanValidationException;
import com.ieven.ext.util.util.StringUtils;

/**
 * 简单类校验工具
 * 
 * @author lichong
 *
 */
public class BeanValidationUtil {

	/**
	 * 检查bean特定属性是否为空
	 * 
	 * @param bean
	 * @param beanProperties
	 * @return
	 * @throws BeanValidationException
	 */
	@SuppressWarnings("unchecked")
	public static String beanValidationIsNotNull(Object bean, Set<String> beanProperties)
			throws BeanValidationException {
		Map<String, String> map;
		try {
			map = BeanUtils.describe(bean);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			throw new BeanValidationException("bean校验异常", e);
		}
		StringBuilder builder = new StringBuilder("");
		for (String temp : beanProperties) {
			if (!StringUtils.hasText(map.get(temp))) {
				builder.append(temp + ",");
			}
		}
		if (builder.length() == 0) {
			return "";
		} else {
			return builder.substring(0, builder.length() - 1);
		}
	}

	/**
	 * 检查bean属性是否为空
	 * 
	 * @param bean
	 * @return
	 * @throws BeanValidationException
	 */
	@SuppressWarnings("unchecked")
	public static String beanValidationIsNotNull(Object bean) throws BeanValidationException {
		try {
			return beanValidationIsNotNull(bean, BeanUtils.describe(bean).keySet());
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			throw new BeanValidationException("bean校验异常", e);
		}
	}
}
