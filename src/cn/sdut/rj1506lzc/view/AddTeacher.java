package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.TeacherDao;
import cn.sdut.rj1506lzc.entity.Teacher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Cc on 2017/7/5.
 */
public class AddTeacher extends JFrame implements ActionListener {

    JTextField in_id, in_name, in_course, in_position;
    JButton enter;
    JMenuItem query_name, query_id;
    JMenuItem add_teacher, add_admin;
    JMenuItem del_teacher, del_admin;
    JMenuItem backUp;

    public AddTeacher() {
        init();
        setBounds(240,45,1000,700);
        setTitle("添加教师基本信息");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {

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
         * 欢迎语标签
         */
        JLabel jLabel = new JLabel("请添加教师的相关信息",JLabel.CENTER);
        jLabel.setBounds(90,50,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        JLabel id = new JLabel("编  号");
        id.setBounds(350,150,100,40);
        id.setFont(new Font("楷体",Font.BOLD,25));
        add(id);

        /**
         * 信息标签
         */
        JLabel name = new JLabel("姓  名");
        name.setBounds(350,210,100,40);
        name.setFont(new Font("楷体",Font.BOLD,25));
        add(name);

        JLabel course = new JLabel("课  程");
        course.setBounds(350,270,100,40);
        course.setFont(new Font("楷体",Font.BOLD,25));
        add(course);

        JLabel position = new JLabel("职  称");
        position.setBounds(350,330,100,40);
        position.setFont(new Font("楷体",Font.BOLD,25));
        add(position);

        /**
         * 信息文本框
         */
        in_id = new JTextField(12);
        in_id.setBounds(460,150,160,40);
        in_id.setFont(new Font("楷体",Font.BOLD,25));
        in_id.addActionListener(this);
        add(in_id);

        in_name = new JTextField(12);
        in_name.setBounds(460,210,160,40);
        in_name.setFont(new Font("楷体",Font.BOLD,25));
        in_name.addActionListener(this);
        add(in_name);

        in_course = new JTextField(12);
        in_course.setBounds(460,270,160,40);
        in_course.setFont(new Font("楷体",Font.BOLD,25));
        in_course.addActionListener(this);
        add(in_course);

        in_position = new JTextField(12);
        in_position.setBounds(460,330,160,40);
        in_position.setFont(new Font("楷体",Font.BOLD,25));
        in_position.addActionListener(this);
        add(in_position);

        /**
         * 确认按钮
         */
        enter = new JButton("确定");
        enter.setBounds(410,410,150,50);
        enter.setFont(new Font("楷体",Font.BOLD,20));
        enter.addActionListener(this);
        add(enter);

    }

    /**
     * 对菜单栏和按钮进行监听
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
        } else if ( e.getActionCommand() == "") {
            try {
                BackUp backUp = new BackUp();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        }

        if ( e.getSource() == enter || e.getSource() == in_position ) {
            Teacher teacher = new Teacher();
            teacher.setId(Integer.parseInt(in_id.getText()));
            teacher.setName(in_name.getText());
            teacher.setCourse(in_course.getText());
            teacher.setPosition(in_position.getText());
            TeacherDao teacherDao = new TeacherDao();
            try {
                teacherDao.insertAll(teacher);//插入数据库
                JOptionPane.showMessageDialog(this,"添加成功","正确",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                WindowsMain windowsMain = new WindowsMain();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }

    }
}
