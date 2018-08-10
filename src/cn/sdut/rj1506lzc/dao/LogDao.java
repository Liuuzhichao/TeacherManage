package cn.sdut.rj1506lzc.dao;

import cn.sdut.rj1506lzc.entity.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cc on 2017/6/27.
 */
//记录某一天的上课情况或者某位老师的上课情况
public class LogDao extends BaseDao {

    /**
     * 插入教师授课日志信息
     * @param log
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertTime(Log log) throws SQLException, ClassNotFoundException {
        connection = getCon();
        String sql = "insert into log(name,beginTime,endTime,time) values(?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,log.getName());
        preparedStatement.setString(2,log.getStartTime());
        preparedStatement.setString(3,log.getEndTime());
        preparedStatement.setString(4,log.getTime());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Log> selectByName(String name) throws SQLException, ClassNotFoundException {
        connection = getCon();
        ArrayList<Log> arrayList = new ArrayList<Log>();
        String sql = "select * from log where name='"+name+"'";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        while (resultSet.next()) {
            Log log = new Log();
            log.setName(resultSet.getString("name"));
            log.setStartTime(resultSet.getString("beginTime"));
            log.setEndTime(resultSet.getString("endTime"));
            log.setTime(resultSet.getString("time"));
            arrayList.add(log);
        }
        return arrayList;
    }

}
