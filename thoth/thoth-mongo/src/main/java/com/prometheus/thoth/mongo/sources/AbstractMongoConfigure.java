package com.prometheus.thoth.mongo.sources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;

/**
 * abstract class for mongo configure Created by zhuhuaiqi on 2017/3/20.
 */
public abstract class AbstractMongoConfigure {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String host, database;
	private int port;
	private String username;
	private String password;
	@Value("${mongo.isAuth:false}")
	private boolean isAuth;
	@Value("${mongo.socketKeepAlive:false}")
	private boolean socketKeepAlive;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * Method that creates MongoDbFactory Common to both of the MongoDb connections
	 */
	public MongoDbFactory mongoDbFactory() throws Exception {
		if (isAuth) {
			logger.info("----mongo auth is:true-[mongodb 使用安全连接]");
			MongoCredential credential = MongoCredential.createPlainCredential(username, database,
					password.toCharArray());
			String sURI = "";
			if (host.contains(",")) {
				String[] hosts = host.split(",");
				int len = hosts.length;
				for (int i = 0; i < len; i++) {
					String uri = String.format("mongodb://%s:%s@%s:%d/%s", username, password, hosts[i], port,
							database);
					if (i == 0)
						sURI = uri;
					else
						sURI = sURI + "," + uri;
				}

			} else {
				sURI = String.format("mongodb://%s:%s@%s:%d/%s", username, password, host, port, database);
			}

			logger.info("--------|MongoDbFactory|---{$sURI} is:\n{}", sURI);

			MongoClientOptions mongoClientOptions = MongoClientOptions.builder().socketKeepAlive(socketKeepAlive)
					.build();
			MongoClientOptions.Builder builder = new MongoClientOptions.Builder(mongoClientOptions);
			MongoClientURI uri = new MongoClientURI(sURI, builder);
			return new SimpleMongoDbFactory(uri);
		}
		logger.info("----mongo auth is:false-[mongodb 使用无安全验证连接]");
		return new SimpleMongoDbFactory(new MongoClient(host, port), database);
	}

	/*
	 * Factory method to create the MongoTemplate
	 */
	abstract public MongoTemplate getMongoTemplate() throws Exception;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
