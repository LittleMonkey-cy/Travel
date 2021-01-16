package cn.love.travel.util;

import java.util.Random;
import java.util.UUID;

/**
* @author 陈钰
* @Description  文件上传工具类
* @Date 2020/12/20
* @Param
* @return
**/
public class UploadUtils {
    public static  String getRealName(String name){
        // 获取最后一个
        int index=name.lastIndexOf("\\");
        return name.substring(index+1);
    }
    public static String getUUIDName(String realName){
        int index=realName.lastIndexOf(".");
        if (index==-1){
            return UUID.randomUUID().toString().replace("-","").toUpperCase();
        }else {
            return UUID.randomUUID().toString().replace("-","").toUpperCase()+realName.substring(index);
        }
    }
    public static String getDir(){
        String s="0123456789ABCDEF";
        Random r=new Random();
        return "/"+s.charAt(r.nextInt(16))+"/"+s.charAt(r.nextInt(16));
    }

    public static void main(String[] args) {
        String s="1.jpg";

        String realName = getRealName(s);
        System.out.println(realName);

        String uuidName = getUUIDName(realName);
        System.out.println(uuidName);

        String dir = getDir();
        System.out.println(dir);
    }
}
