package cn.edu.buaa.judge.utils;

import java.util.ResourceBundle;

/**
 * @Description: 获取配置文件中规定的变量
 */
public class PropertiesUtil {

    /**
     * 获取名称为config的配置文件中的信息
     */
    private static final ResourceBundle resource = ResourceBundle.getBundle("config");


    public static final String StringValue(String key) {
        return resource.getString(key);
    }

    public static final int IntegerValue(String key) {
        return Integer.parseInt(resource.getString(key));
    }

    public static final long LongValue(String key) {
        return Long.parseLong(resource.getString(key));
    }

    public static final boolean BoolValue(String key) {
        return Boolean.parseBoolean(resource.getString(key));
    }
}
