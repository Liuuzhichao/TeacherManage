package cn.sdut.rj1506lzc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cc on 2017/6/27.
 */
public class BaseDao {

    Connection connection;
    PreparedStatement preparedStatement;

    //数据库的连接
    public static Connection getCon() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        //此处使用数据库名
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/teacher?useUnicode=true&characterEncoding=utf-8","root","usbw");
    }

}
