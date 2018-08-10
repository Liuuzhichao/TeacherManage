package cn.sdut.rj1506lzc.dao;

import cn.sdut.rj1506lzc.entity.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cc on 2017/6/27.
 */
//记录老师基本信息的方法
public class TeacherDao extends BaseDao {

    /**
     * 查询教师的基本信息,用于添加到主页布局
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList selectAll() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> arrayList = new ArrayList<>();
        connection = getCon();
        String sqlSelect = "select * from information";
        preparedStatement = connection.prepareStatement(sqlSelect);
        ResultSet resultSet = preparedStatement.executeQuery(sqlSelect);
        while ( resultSet.next() ) {
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setCourse(resultSet.getString("course"));
            teacher.setPosition(resultSet.getString("position"));
            arrayList.add(teacher);
        }
        return arrayList;
    }

    /**
     * 添加教师的基本信息
     * @param teacher
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertAll(Teacher teacher) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "insert into information(id,name,course,position) values(?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,teacher.getId());
        preparedStatement.setString(2,teacher.getName());
        preparedStatement.setString(3,teacher.getCourse());
        preparedStatement.setString(4,teacher.getPosition());
        preparedStatement.executeUpdate();
    }

    /**
     * 通过教师id来删除教师信息
     * @param id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteById(int id) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "delete from information where id='"+id+"'";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    /**
     * 通过教师名字来删除教师信息
     * @param name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteByName(String name) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "delete from information where name='"+name+"'";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    /**
     * 通过教师ID来查询教师信息
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String selectById(String id) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "select * from information where id='"+id+"'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        String st = null;
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String course = resultSet.getString("course");
            String position = resultSet.getString("position");
            st = "编号:"+id+"  姓名:"+name+"  课程:"+course+"  职称:"+position;
        }
        return st;
    }

    /**
     * 通过教师姓名来查询教师信息
     * @param name
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String selectByName(String name) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "select * from information where name='"+name+"'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        String st = null;
        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString("id"));
            String course = resultSet.getString("course");
            String position = resultSet.getString("position");
            st = "编号:"+id+"  姓名:"+name+"  课程:"+course+"  职称:"+position;
        }
        return st;
    }

    /**
     * 根据用户名来得到教师密码,用于登录
     * @param name
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String getPwd(String name) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "select * from information where name='"+name+"'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        String pwd = null;
        while ( resultSet.next() ) {
            pwd = resultSet.getNString("pwd");
        }
        return pwd;
    }

}
