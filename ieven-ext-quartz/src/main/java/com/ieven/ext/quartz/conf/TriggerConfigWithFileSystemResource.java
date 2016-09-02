package com.ieven.ext.quartz.conf;

import java.util.List;

import org.dom4j.Element;

import com.ieven.ext.quartz.po.TriggerBean;
import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.io.AbstractRegisterConfigWithClasspathResource;
import com.ieven.ext.util.io.AbstractRegisterConfigWithFileSystemListener;
import com.ieven.ext.util.po.RegistType;

/**
 * 从配置文件jobTactics.xml获取trigger map工具类
 * 
 * @author lichong
 *
 */
public class TriggerConfigWithFileSystemResource extends AbstractRegisterConfigWithFileSystemListener {

	private static final String CONFIG_PATH = "conf/job/JobTactics.xml";

	private static final String CONFIG_FILE_NAME = "JobTactics.xml";
	
	public TriggerConfigWithFileSystemResource() {
		this(RegistType.SPECIFIC_XPATH, "content/trigger/leaf");
	}

	private TriggerConfigWithFileSystemResource(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for (Element element : list) {
			if (element.attributeValue("name") == null || element.attributeValue("name").equals("")) {
				throw new ConfigException(CONFIG_FILE_NAME + "中trigger name标签为空");
			}
			if (element.attributeValue("des") == null || element.attributeValue("des").equals("")) {
				throw new ConfigException(CONFIG_FILE_NAME + "中trigger des标签为空");
			}
			if (element.attributeValue("group") == null || element.attributeValue("group").equals("")) {
				throw new ConfigException(CONFIG_FILE_NAME + "中trigger group标签为空");
			}
			if (element.attributeValue("cron") == null || element.attributeValue("cron").equals("")) {
				throw new ConfigException(CONFIG_FILE_NAME + "中trigger cron标签为空");
			}
			String name = element.attributeValue("name");
			String des = element.attributeValue("des");
			String group = element.attributeValue("group");
			String cron = element.attributeValue("cron");
			String url = element.attributeValue("url");
			TriggerBean triggerBean = new TriggerBean();
			triggerBean.withCron(cron).withDes(des).withGroup(group).withName(name);
			this.map.put(name, triggerBean);
		}
	}

	@Override
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return CONFIG_PATH;
	}

}
