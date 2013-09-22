package com.loongsoft.knowledgebase.util;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
/**
 * 辅助类
 * @author suoyanming 2013-8-19
 *
 */
public class Util {
	
	/**
	 * 
	 *  json 转换config配置，实现转换指定的属性
	 *  
	 */
	public static JsonConfig GetjsonConfig(final String[] s) {
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {

			@Override
			public boolean apply(Object source, String name, Object value) {
				// TODO Auto-generated method stub
				if (juge(s, name)) {
					return true;
				} else {
					return false;
				}
			}
		});
		return config;

	}

	protected static boolean juge(String[] s, String s2) {
		boolean b = false;
		for (String s1 : s) {
			if (s2.equals(s1)) {
				b = true;
			}
		}
		return b;
	}
	
	/**
	 * 字符串分隔
	 */
	public static String[] splitStr(String str,String type){
		String[] strs = null ;
		if(str!=null){
			strs = str.split(type);
			/*strs[strs.length-1]=null;*/
		}
		return strs;
	}
	
	
}
