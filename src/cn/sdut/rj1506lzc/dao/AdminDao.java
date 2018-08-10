package cn.sdut.rj1506lzc.dao;

import cn.sdut.rj1506lzc.entity.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cc on 2017/7/5.
 */
public class AdminDao extends BaseDao {

    /**
     * 根据用户名得到管理员密码
     * @param name
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String getPwd(String name) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "select * from admin where name='"+name+"'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        String pwd = null;
        while ( resultSet.next() ) {
            pwd = resultSet.getNString("pwd");
        }
        return pwd;
    }

    /**
     * 插入管理员账号密码
     * @param admin
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertAll(Admin admin) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "insert into admin(name,pwd) values(?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,admin.getName());
        preparedStatement.setString(2,admin.getPwd());
        preparedStatement.executeUpdate();
    }

    /**
     * 通过用户名来删除管理员账号
     * @param name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteByName(String name) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "delete from admin where name='"+name+"'";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

}
