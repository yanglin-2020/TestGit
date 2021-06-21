package com.company;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args) throws Exception {
        String str = "费用所属时间_dateTime1563760140749";
        //System.out.println(isChinese(str));
        String str2 = str.substring(str.indexOf("_")+1,str.length());
        System.out.println(str2);
    }
//    public static boolean isChinese(String str) throws UnsupportedEncodingException
//      {
//              int len = str.length();
//                 for(int i = 0;i < len;i ++)
//                     {
//                         String temp = URLEncoder.encode(str.charAt(i) + "", "utf-8");
//                         if(temp.equals(str.charAt(i) + ""))
//                                continue;
//                         String[] codes = temp.split("%");
//             //判断是中文还是字符(下面判断不精确，部分字符没有包括)
//                         for(String code:codes)
//                            {
//                                if(code.compareTo("40") > 0)
//                                        return true;
//                             }
//                     }
//                 return false;
//             }
    }
