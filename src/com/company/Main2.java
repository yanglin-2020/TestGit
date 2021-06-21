package com.company;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main2 {

    public static void main(String[] args) {
      try{
          //1,加载Mysql驱动
          Class.forName("com.mysql.jdbc.Driver");
          String url ="jdbc:mysql://220.196.1.144:5570/sceo_erp_2018_183";
          String user = "root";
          String password = "huidong123123";
          //2，获得链接对象
          Connection conn = DriverManager.getConnection(url, user, password);
          //3，创建statement对象
          Statement stmt = conn.createStatement();
          //4，执行statement
          String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'sceo_erp_2018_183'";
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              String tableName = rs.getString("table_name");
             if(!tableName.equals("act_de_model")&&!tableName.equals("act_de_model_publish")){
                 Statement stmt2 = conn.createStatement();
                 System.out.println(tableName);
                 String sql2 = "alter table "+tableName+" convert to character set utf8mb4 collate utf8mb4_bin";
                 stmt2.execute(sql2);
             }
          }
          //6释放资源 原则  后打开的先关闭
          rs.close();
          stmt.close();
          conn.close();
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    //判断字符串是否包含字符串
    public static boolean isChinese(String str) throws UnsupportedEncodingException {
        int len = str.length();
        for(int i = 0;i < len;i ++){
            String temp = URLEncoder.encode(str.charAt(i) + "", "utf-8");
            if(temp.equals(str.charAt(i) + ""))
                continue;
            String[] codes = temp.split("%");
            //判断是中文还是字符(下面判断不精确，部分字符没有包括)
            for(String code:codes)
            {
                if(code.compareTo("40") > 0)
                    return true;
            }
        }
        return false;
    }
}
