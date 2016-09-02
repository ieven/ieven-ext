package com.ieven.ext.mongo.po;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ieven.ext.mongo.conf.proxy.ConfigProxy;
import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.util.StringUtils;

/**
 * mongo bean
 * 
 * @author lichong
 *
 */
public class MongoBean {
	private List<String> mongoUrl;

	private List<Integer> mongoPort;

	private String mongoDbName;

	private boolean authentication;

	private String authenticationDbName;

	private String userName;

	private String password;

	private String description;

	private int minConnectionsPerHost;

	private int connectionsPerHost = -1;

	private int threadsAllowedToBlockForConnectionMultiplier = -1;

	private int serverSelectionTimeout = -1;

	private int maxWaitTime = -1;

	private int maxConnectionIdleTime = -1;

	private int maxConnectionLifeTime = -1;

	private int connectTimeout = -1;

	private int socketTimeout = -1;

	private Boolean socketKeepAlive = null;

	private String readPreference;

	private String writeConcern;

	private String readConcern;

	public String getAuthenticationDbName() {
		return authenticationDbName;
	}

	public List<String> getMongoUrl() {
		return mongoUrl;
	}

	public List<Integer> getMongoPort() {
		return mongoPort;
	}

	public String getMongoDbName() {
		return mongoDbName;
	}

	public boolean isAuthentication() {
		return authentication;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinConnectionsPerHost() {
		return minConnectionsPerHost;
	}

	public void setMinConnectionsPerHost(int minConnectionsPerHost) {
		this.minConnectionsPerHost = minConnectionsPerHost;
	}

	public int getConnectionsPerHost() {
		return connectionsPerHost;
	}

	public void setConnectionsPerHost(int connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}

	public int getThreadsAllowedToBlockForConnectionMultiplier() {
		return threadsAllowedToBlockForConnectionMultiplier;
	}

	public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
		this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}

	public int getServerSelectionTimeout() {
		return serverSelectionTimeout;
	}

	public void setServerSelectionTimeout(int serverSelectionTimeout) {
		this.serverSelectionTimeout = serverSelectionTimeout;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public int getMaxConnectionIdleTime() {
		return maxConnectionIdleTime;
	}

	public void setMaxConnectionIdleTime(int maxConnectionIdleTime) {
		this.maxConnectionIdleTime = maxConnectionIdleTime;
	}

	public int getMaxConnectionLifeTime() {
		return maxConnectionLifeTime;
	}

	public void setMaxConnectionLifeTime(int maxConnectionLifeTime) {
		this.maxConnectionLifeTime = maxConnectionLifeTime;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Boolean isSocketKeepAlive() {
		return socketKeepAlive;
	}

	public void setSocketKeepAlive(boolean socketKeepAlive) {
		this.socketKeepAlive = socketKeepAlive;
	}

	public String getReadPreference() {
		return readPreference;
	}

	public void setReadPreference(String readPreference) {
		this.readPreference = readPreference;
	}

	public String getWriteConcern() {
		return writeConcern;
	}

	public void setWriteConcern(String writeConcern) {
		this.writeConcern = writeConcern;
	}

	public String getReadConcern() {
		return readConcern;
	}

	public void setReadConcern(String readConcern) {
		this.readConcern = readConcern;
	}

	public void setMongoUrl(List<String> mongoUrl) {
		this.mongoUrl = mongoUrl;
	}

	public void setMongoPort(List<Integer> mongoPort) {
		this.mongoPort = mongoPort;
	}

	public void setMongoDbName(String mongoDbName) {
		this.mongoDbName = mongoDbName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 将mongo bean根据配置文件进行初始化
	 */
	public MongoBean init() {
//		Map<String, String> configMap = ConfigProxy.getMongoConfigMap();
//		List<String> mongoUrl = Arrays.asList(configMap.get("mongoUrl").split(","));
//		List<String> mongoPortTemp = Arrays.asList(configMap.get("mongoPort").split(","));
//		List<Integer> mongoPort = new ArrayList<>();
//		for (String port : mongoPortTemp) {
//			try {
//				mongoPort.add(Integer.parseInt(port));
//			} catch (Exception e) {
//				// TODO: handle exception
//				throw new ConfigException("mongo配置文件中端口:" + port + "配置不正确：只能为数字", e);
//			}
//		}
//		if (mongoPort.size() != mongoUrl.size()) {
//			throw new ConfigException("mongo配置文件中url与端口数量不匹配");
//		}
//		boolean authentication;
//		try {
//			authentication = Boolean.parseBoolean(configMap.get("authentication"));
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中authentication配置不正确，只能为true或false");
//		}
//		if (!StringUtils.hasLength(configMap.get("mongoDbName"))) {
//			throw new ConfigException("mongo配置文件中mongoDbName不能为空");
//		}
//		this.authentication = authentication;
//		this.mongoDbName = configMap.get("mongoDbName");
//		this.mongoPort = mongoPort;
//		this.mongoUrl = mongoUrl;
//		if (authentication && (!StringUtils.hasLength(configMap.get("userName"))
//				|| !StringUtils.hasLength(configMap.get("authenticationDbName"))
//				|| !StringUtils.hasLength(configMap.get("password")))) {
//			throw new ConfigException("mongo配置文件中authentication为true时，userName、authenticationDbName和password不能为空");
//		}
//		this.password = configMap.get("password");
//		this.userName = configMap.get("userName");
//		this.authenticationDbName = configMap.get("authenticationDbName");
//		// 以下为读取数据库连接池属性配置
//		this.description = configMap.get("description");
//		try {
//			if (StringUtils.hasLength(configMap.get("minConnectionsPerHost"))) {
//				this.minConnectionsPerHost = Integer.parseInt(configMap.get("minConnectionsPerHost"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中minConnectionsPerHost配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("connectionsPerHost"))) {
//				this.connectionsPerHost = Integer.parseInt(configMap.get("connectionsPerHost"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中connectionsPerHost配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("threadsAllowedToBlockForConnectionMultiplier"))) {
//				this.threadsAllowedToBlockForConnectionMultiplier = Integer
//						.parseInt(configMap.get("threadsAllowedToBlockForConnectionMultiplier"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中threadsAllowedToBlockForConnectionMultiplier配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("serverSelectionTimeout"))) {
//				this.serverSelectionTimeout = Integer.parseInt(configMap.get("serverSelectionTimeout"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中serverSelectionTimeout配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("maxWaitTime"))) {
//				this.maxWaitTime = Integer.parseInt(configMap.get("maxWaitTime"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中maxWaitTime配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("maxConnectionIdleTime"))) {
//				this.maxConnectionIdleTime = Integer.parseInt(configMap.get("maxConnectionIdleTime"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中maxConnectionIdleTime配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("maxConnectionLifeTime"))) {
//				this.maxConnectionLifeTime = Integer.parseInt(configMap.get("maxConnectionLifeTime"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中maxConnectionLifeTime配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("connectTimeout"))) {
//				this.connectTimeout = Integer.parseInt(configMap.get("connectTimeout"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中connectTimeout配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("socketTimeout"))) {
//				this.socketTimeout = Integer.parseInt(configMap.get("socketTimeout"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中socketTimeout配置不正确，只能为数字");
//		}
//
//		try {
//			if (StringUtils.hasLength(configMap.get("socketKeepAlive"))) {
//				this.socketKeepAlive = Boolean.parseBoolean(configMap.get("socketKeepAlive"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ConfigException("mongo配置文件中socketKeepAlive配置不正确，只能为true或false");
//		}
//
//		this.readPreference = configMap.get("readPreference");
//		this.writeConcern = configMap.get("writeConcern");
//		this.readConcern = configMap.get("readConcern");
		return this;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder("mongoUrl: " + mongoUrl.toString() + "\n");
		builder.append("mongoPort: " + mongoPort.toString() + "\n");
		builder.append("mongoDbName: " + mongoDbName + "\n");
		builder.append("authentication： " + authentication + "\n");
		builder.append("authenticationDBName： " + authenticationDbName + "\n");
		builder.append("userName： " + userName + "\n");
		builder.append("password： " + password + "\n");
		builder.append("description： " + description + "\n");
		builder.append("minConnectionsPerHost： " + minConnectionsPerHost + "\n");
		builder.append("connectionsPerHost： " + connectionsPerHost + "\n");
		builder.append(
				"threadsAllowedToBlockForConnectionMultiplier： " + threadsAllowedToBlockForConnectionMultiplier + "\n");
		builder.append("serverSelectionTimeout： " + serverSelectionTimeout + "\n");
		builder.append("maxWaitTime： " + maxWaitTime + "\n");
		builder.append("maxConnectionIdleTime： " + maxConnectionIdleTime + "\n");
		builder.append("maxConnectionLifeTime： " + maxConnectionLifeTime + "\n");
		builder.append("connectTimeout： " + connectTimeout + "\n");
		builder.append("socketTimeout： " + socketTimeout + "\n");
		builder.append("socketKeepAlive： " + socketKeepAlive + "\n");
		builder.append("readPreference： " + readPreference + "\n");
		builder.append("writeConcern： " + writeConcern + "\n");
		builder.append("readConcern： " + readConcern + "\n");
		return builder.toString();
	}
}
