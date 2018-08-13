package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.LogDao;
import cn.sdut.rj1506lzc.dao.TeacherDao;
import cn.sdut.rj1506lzc.entity.Log;
import cn.sdut.rj1506lzc.entity.Teacher;
import cn.sdut.rj1506lzc.utils.SetTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cc on 2017/7/5.
 */
public class WindowsLayout extends JFrame implements ActionListener{

    JMenuItem sign_in, sign_out;
    JMenuItem back;
    Log log;

    public WindowsLayout (Log log) throws SQLException, ClassNotFoundException {
        init();
        this.log = log;
        setBounds(240,45,1000,700);
        setTitle("教师管理系统");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() throws SQLException, ClassNotFoundException {
        setLayout(null);

        /**
         * 添加,设置菜单栏属性
         */
        JMenuBar jMenuBar = new JMenuBar();
        JMenu sign = new JMenu("打卡");
        JMenu func = new JMenu("功能");
        sign_in = new JMenuItem("签到");
        sign_in.addActionListener(this);
        sign_out = new JMenuItem("签退");
        sign_out.addActionListener(this);
        back = new JMenuItem("返回");
        back.addActionListener(this);
        jMenuBar.add(sign);
        jMenuBar.add(func);
        sign.add(sign_in);
        sign.add(sign_out);
        func.add(back);
        setJMenuBar(jMenuBar);

        /**
         * 添加标签
         */
        JLabel jLabel = new JLabel("欢迎来到教师管理系统",JLabel.CENTER);
        jLabel.setBounds(90,10,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        /**
         * 添加表格
         */
        String[] name = {"编号","姓名","所授课程","职称"};//表头
        String[][] data = SetTable.setTable();//表格内容
        JTable jTable = new JTable(data,name);
        //设置表头字体
        jTable.getTableHeader().setFont(new Font("宋体",Font.BOLD,20));
        //设置表头高度
        jTable.getTableHeader().setPreferredSize(new Dimension(1,35));
        //设置表格内字体
        jTable.setFont(new Font("楷体",Font.BOLD,17));
        jTable.setRowHeight(30);//设置表格的行高
        setTableColumnCenter(jTable);//设置字体居中
        JScrollPane jScrollPane = new JScrollPane(jTable);//滚动控件
        jScrollPane.setLocation(90,100);
        jScrollPane.setSize(800,500);
        add(jScrollPane);

    }

    /**
     * 为给表格填充数据的数组赋值
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    /*String[][] setTable () throws SQLException, ClassNotFoundException {
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
    }*/

    /**
     * 表格居中显示方法
     * @param jTable
     */
    void setTableColumnCenter(JTable jTable) {
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,defaultTableCellRenderer);
    }

    /**
     * 菜单栏 按钮 监听
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        LogDao logDao = new LogDao();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e.getActionCommand() == "签到") {
            Date beginTime = new java.util.Date();//当前时间,开始时间
            String begin = sdf.format(beginTime);
            log.setStartTime(begin);//设置开始时间
            JOptionPane.showMessageDialog(this,"签到成功",
                    "正确",JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand() == "签退" ) {
            Date endTime = new java.util.Date();//当前时间,结束时间
            String end = sdf.format(endTime);
            log.setEndTime(end);//设置结束时间
            Date endTime1 = null;
            Date beginTime = null;
            try {
                endTime1 = sdf.parse(log.getEndTime());
                beginTime = sdf.parse(log.getStartTime());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            /**
             * 求授课时长
             */
            long ms = endTime1.getTime() - beginTime.getTime();
            long day = ms / (24 * 60 * 60 * 1000);
            long hour = (ms / (60 * 60 * 1000) - day * 24);
            long minute = ((ms / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long second = (ms / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);
            String time =  day + "天" + hour + "小时" + minute +"分" + second + "秒";
            log.setTime(time);//设置授课时长
            System.out.println(log.getStartTime()+"　"+log.getEndTime());
            try {
                logDao.insertTime(log);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(this,"签退成功",
                    "正确",JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand() == "返回") {
            this.dispose();
            LogIn logIn = new LogIn();
        }
    }
}
