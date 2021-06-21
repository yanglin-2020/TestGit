package com.company;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        String sql3 = "";
        try {
            //1,加载Mysql驱动
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://220.196.1.144:5570/sceo_erp_2018_183?characterEncoding=UTF-8";
            String user = "root";
            String password = "huidong123123";
            //2，获得链接对象
            Connection conn = DriverManager.getConnection(url, user, password);
            //3，创建statement对象
            Statement stmt = conn.createStatement();
            //4，执行statement
            String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'sceo_erp_2018_183'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString("table_name");
                if (tableName.contains("data_") || tableName.contains("table")) {
                    if (!tableName.equals("algjlagjkljagl_table1611909485392")&&!tableName.equals("bgacgggccjabjefstk_table1567666740490")
                            &&!tableName.equals("bgachcffeddcjhuqpd_table1605665900484")&&!tableName.equals("bgacheiacjdjjmntnd_table1605671630218")
                            &&!tableName.equals("bgacheiacjdjjmntnd_table1605684907514") &&!tableName.equals("bgacheibhjhjggqopv_table1605669177407")
                            &&!tableName.equals("bgafbgiefjbadkhbmv_table1604993538818")&&!tableName.equals("bgafbgiefjbadkhbmv_table1604994143604")
                            &&!tableName.equals("bgafbgiefjbadkhbmv_table1604994144000")&&!tableName.equals("bgafcdibecfhamsznk_table1604653751799")
                            &&!tableName.equals("bgaffjcejabffcbtpb_table1605592681891") &&!tableName.equals("bgafghbbhegiisybvw_table1605667880603")
                            &&!tableName.equals("bgagefjjbjhealrujv_table1605665900484")&&!tableName.equals("bgagegffgghigtlohv_table1605667880603")
                            &&!tableName.equals("ceshfkghkjahk_table1605494753934") &&!tableName.equals("data_ceshitwo_v1")
                            &&!tableName.equals("data_huiwuyingxiaoliucheng_v1") &&!tableName.equals("data_huowuyunshuliucheng_v2")
                            &&!tableName.equals("data_one_v1")&&!tableName.equals("kjfhakjhkjah_table1606285766999")) {
                        Statement stmt2 = conn.createStatement();
                        String sql2 = "select column_name,column_type from information_schema.columns where table_schema='sceo_erp_2018_183' and table_name= '" + tableName + "' ";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        //System.out.println(tableName);
                        while (rs2.next()) {
                            String columnName = rs2.getString("column_name");
                            if (isChinese(columnName) && columnName.contains("_")) {
                                String columnType = rs2.getString("column_type");
                                //System.out.println("旧的："+columnName);
                                String newColumnName = columnName.substring(columnName.indexOf("_") + 1, columnName.length());
                                //System.out.println("新的："+newColumnName);
                                sql3 = "alter table " + tableName + " change   column " + columnName + "  " + newColumnName + " " + columnType;
                                System.out.println(sql3);
                                Statement stmt3 = conn.createStatement();
                                stmt3.execute(sql3);
                            }
                        }
                        rs2.close();
                    }
                }
            }
            //6释放资源 原则  后打开的先关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            if((e + "").contains("Duplicate column name")){
                System.out.println("出错了======"+sql3);
            }
        }
    }

    //判断字符串是否包含字符串
    public static boolean isChinese(String str) throws UnsupportedEncodingException {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            String temp = URLEncoder.encode(str.charAt(i) + "", "utf-8");
            if (temp.equals(str.charAt(i) + ""))
                continue;
            String[] codes = temp.split("%");
            //判断是中文还是字符(下面判断不精确，部分字符没有包括)
            for (String code : codes) {
                if (code.compareTo("40") > 0)
                    return true;
            }
        }
        return false;
    }
}
