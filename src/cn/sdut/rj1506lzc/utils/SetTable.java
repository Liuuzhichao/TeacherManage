package cn.sdut.rj1506lzc.utils;

import cn.sdut.rj1506lzc.dao.TeacherDao;
import cn.sdut.rj1506lzc.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public class SetTable {
    /**
     * 为给表格填充数据的数组赋值
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String[][] setTable () throws SQLException, ClassNotFoundException {
        TeacherDao teacherDao = new TeacherDao();
        ArrayList<Teacher> arrayList = teacherDao.selectAll();
        String [][]st = new String[arrayList.size()][4];
        for ( int i = 0; i < arrayList.size(); i++ ) {
            Teacher teacher = arrayList.get(i);
            st[i][0] = String.valueOf(teacher.getId());
            st[i][1] = teacher.getName();
            st[i][2] = teacher.getCourse();
            st[i][3] = teacher.getPosition();
        }
        return st;
    }

}
