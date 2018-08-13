package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.TeacherDao;
import cn.sdut.rj1506lzc.entity.Teacher;
import cn.sdut.rj1506lzc.utils.SetTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cc on 2017/6/28.
 */
public class WindowsMain extends JFrame implements ActionListener{

    JMenuItem query_name, query_id;
    JMenuItem add_teacher, add_admin;
    JMenuItem del_teacher, del_admin;
    JMenuItem backUp;

    public WindowsMain () throws SQLException, ClassNotFoundException {
        init();
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
        JMenu add = new JMenu("添加");
        JMenu delete = new JMenu("删除");
        JMenu query = new JMenu("查询");
        JMenu backup = new JMenu("备份");
        add_teacher = new JMenuItem("添加教师信息");
        add_teacher.addActionListener(this);
        add_admin = new JMenuItem("添加管理员");
        add_admin.addActionListener(this);
        del_teacher = new JMenuItem("删除教师信息");
        del_teacher.addActionListener(this);
        del_admin = new JMenuItem("删除管理员");
        del_admin.addActionListener(this);
        query_name = new JMenuItem("按名字查询");
        query_name.addActionListener(this);
        query_id = new JMenuItem("按编号查询");
        query_id.addActionListener(this);
        backUp = new JMenuItem("备份教师信息");
        backUp.addActionListener(this);
        jMenuBar.add(add);
        jMenuBar.add(delete);
        jMenuBar.add(query);
        jMenuBar.add(backup);
        add.add(add_teacher);
        add.add(add_admin);
        delete.add(del_teacher);
        delete.add(del_admin);
        query.add(query_name);
        query.add(query_id);
        backup.add(backUp);
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
        JTable jTable = new JTable(data,name);//创建表格
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
     * 对菜单栏进行监听
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getActionCommand() == "添加教师信息" ) {
            AddTeacher addTeacher = new AddTeacher();
            this.dispose();
        } else if ( e.getActionCommand() == "添加管理员" ) {
            AddAdmin addAdmin = new AddAdmin();
            this.dispose();
        } else if ( e.getActionCommand() == "删除教师信息" ) {
            DeleteTeacher deleteTeacher = new DeleteTeacher();
            this.dispose();
        } else if ( e.getActionCommand() == "删除管理员" ) {
            DeleteAdmin deleteAdmin = new DeleteAdmin();
            this.dispose();
        } else if ( e.getActionCommand() == "按名字查询" ) {
            SelectByName selectByName = new SelectByName();
            this.dispose();
        } else if ( e.getActionCommand() == "按编号查询" ) {
            SelectById selectById = new SelectById();
            this.dispose();
        } else if ( e.getActionCommand() == "备份教师信息") {
            try {
                BackUp backUp = new BackUp();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        }
    }

}
