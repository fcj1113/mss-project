package com.miaoshasha.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by fengchaojun on 2017/9/7.
 */
public class Utils {

    /**
     * 校验多个参数是否为空，有一个为空则返回false
     *
     * @param params
     * @return
     */
    public static boolean checkNull(String... params) {
        boolean flag = true;
        for (int i = 0; i < params.length; i++) {
            String str = params[i];
            if (str == null || str.length() == 0) {
                flag = false;
                break;
            }
        }

        return flag;
    }


    /**
     * 生成随机数
     *
     * @param start
     *            起始数（包含）
     * @param end
     *            结束数（包含）
     * @return
     */
    public static int genRandom(int start, int end) {
        int randNum = 0;
        Random rand = new Random();
        randNum = rand.nextInt((end + 1) - start) + start;
        return randNum;
    }


    /**
     * 通过uuid生成文件名称
     *
     * @return
     */
    public static String uuid() {
        String newFileName = "";
        UUID uuid = UUID.randomUUID();
        newFileName = uuid.toString().replace("-", "");
        return newFileName;
    }

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 货币格式化,转换成元,保留两位
     *
     * @param d
     * @return
     */
    public static String currencyFormatToYuan(double d) {
        NumberFormat currency = NumberFormat.getNumberInstance();
        BigDecimal bigDecimal = new BigDecimal(d);
        BigDecimal baseDecimal = new BigDecimal(100.00d);
        BigDecimal res = bigDecimal.divide(baseDecimal);
        return currency.format(res);
    }

    /**
     * 货币格式化,转换成元,保留两位
     *
     * @param str
     * @return
     */
    public static String currencyFormatToYuan(String str) {
        NumberFormat currency = NumberFormat.getNumberInstance();
        BigDecimal bigDecimal = new BigDecimal(str);
        BigDecimal baseDecimal = new BigDecimal(100.00d);
        BigDecimal res = bigDecimal.divide(baseDecimal);
        return currency.format(res);
    }


    /**
     * 货币格式化,转换成分
     *
     * @param d
     *            元
     * @return
     */
    public static long currencyFormatToFen(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        BigDecimal baseDecimal = new BigDecimal(100d);
        BigDecimal res = bigDecimal.multiply(baseDecimal);
        return res.intValue();
    }

    /**
     * 货币格式化,转换成分
     *
     * @param str
     * @return
     */
    public static long currencyFormatToFen(String str) {
        str = str.replace(",", "");//剔除逗号
        BigDecimal bigDecimal = new BigDecimal(str);
        BigDecimal baseDecimal = new BigDecimal(100d);
        BigDecimal res = bigDecimal.multiply(baseDecimal);
        return res.intValue();
    }


    /**
     * 获取文件后缀
     *
     * @param @param
     *            fileName
     * @param @return
     *            设定文件
     * @return String 返回类型
     */
    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
    }



    public static void main(String[] args){
      Map<String,String> sysMap  = System.getenv();
      System.out.println(sysMap.get("MSS_WORK_ID"));

    }
}
