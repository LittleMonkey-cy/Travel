package cn.love.travel.util;

import java.util.UUID;

/**
* @author 陈钰
* @Description 产生UUID随机字符串工具类
* @Date 2020/12/20
* @Param 
* @return 
**/
public final class UuidUtil {
	private UuidUtil(){}
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		System.out.println(UuidUtil.getUuid());
		System.out.println(UuidUtil.getUuid());
		System.out.println(UuidUtil.getUuid());
		System.out.println(UuidUtil.getUuid());
	}
}
