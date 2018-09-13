package com.xter.support.db;

/**
 * Created by XTER on 2017/10/28.
 * 存储接口，一般由本地数据源继承，做到与sqlite的数据的统一
 */
public interface ISharedRepos {
	String getSharedString(String fileName, String key, String defaultValue);

	int getSharedInt(String fileName, String key, int defaultInt);

	boolean getSharedBool(String fileName, String key, boolean defaultBool);

	void saveSharedString(String fileName, String key, String value);

	void saveSharedInt(String fileName, String key, int value);

	void saveSharedBool(String fileName, String key, boolean value);
}
