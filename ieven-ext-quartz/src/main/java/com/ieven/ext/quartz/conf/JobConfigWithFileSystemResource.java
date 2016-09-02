package com.ieven.ext.quartz.conf;

import java.util.List;

import org.dom4j.Element;

import com.ieven.ext.quartz.po.JobBean;
import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.io.AbstractRegisterConfigWithClasspathResource;
import com.ieven.ext.util.io.AbstractRegisterConfigWithFileSystemListener;
import com.ieven.ext.util.po.RegistType;

/**
 * 从配置文件jobTactics.xml获取job map工具类
 * @author lichong
 *
 */
public class JobConfigWithFileSystemResource extends AbstractRegisterConfigWithFileSystemListener{

	private static final String CONFIG_PATH = "conf/job/JobTactics.xml";
	
	private static final String CONFIG_FILE_NAME = "JobTactics.xml";
	
	public JobConfigWithFileSystemResource()
	{
		this(RegistType.SPECIFIC_XPATH, "content/job/leaf");
	}
	
	private JobConfigWithFileSystemResource(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for(Element element : list)
		{
			if(element.attributeValue("name")==null||element.attributeValue("name").equals(""))
			{
				throw new ConfigException(CONFIG_FILE_NAME + "中job name标签为空");
			}
			if(element.attributeValue("des")==null||element.attributeValue("des").equals(""))
			{
				throw new ConfigException(CONFIG_FILE_NAME + "中job des标签为空");
			}
			if(element.attributeValue("group")==null||element.attributeValue("group").equals(""))
			{
				throw new ConfigException(CONFIG_FILE_NAME + "中job group标签为空");
			}
			if(element.attributeValue("clazz")==null||element.attributeValue("clazz").equals(""))
			{
				throw new ConfigException(CONFIG_FILE_NAME + "中job clazz标签为空");
			}
			if(element.attributeValue("trigger")==null||element.attributeValue("trigger").equals(""))
			{
				throw new ConfigException(CONFIG_FILE_NAME + "中job trigger标签为空");
			}
			String name = element.attributeValue("name");
			String des = element.attributeValue("des");
			String group = element.attributeValue("group");
			String clazz = element.attributeValue("clazz");
			String[] triggers = element.attributeValue("trigger").split(",");
			JobBean jobBean = new JobBean();
			jobBean.withClazz(clazz)
					.withDes(des)
					.withGroup(group)
					.withName(name)
					.withTrigger(triggers);
			this.map.put(name, jobBean);
		}
	}

	@Override
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return CONFIG_PATH;
	}

}
