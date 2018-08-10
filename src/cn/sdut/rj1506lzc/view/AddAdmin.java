package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.AdminDao;
import cn.sdut.rj1506lzc.entity.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Cc on 2017/7/5.
 */
public class AddAdmin extends JFrame implements ActionListener{

    JTextField in_name;
    JPasswordField in_pwd;
    JButton enter;
    JMenuItem query_name, query_id;
    JMenuItem add_teacher, add_admin;
    JMenuItem del_teacher, del_admin;
    JMenuItem backUp;

    public AddAdmin(){
        init();
        setBounds(240,45,1000,700);
        setTitle("添加管理员");
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
        JLabel jLabel = new JLabel("请添加管理员的信息",JLabel.CENTER);
        jLabel.setBounds(90,50,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        /**
         * 用户名密码的标签
         */
        JLabel name = new JLabel("用户名");
        name.setBounds(350,150,100,40);
        name.setFont(new Font("楷体",Font.BOLD,25));
        add(name);

        JLabel pwd = new JLabel("密 码");
        pwd.setBounds(350,210,100,40);
        pwd.setFont(new Font("楷体",Font.BOLD,25));
        add(pwd);

        /**
         * 用户名密码的文本框
         */
        in_name = new JTextField(12);
        in_name.setBounds(460,150,160,40);
        in_name.setFont(new Font("楷体",Font.BOLD,25));
        in_name.addActionListener(this);
        add(in_name);

        in_pwd = new JPasswordField(12);
        in_pwd.setBounds(460,210,160,40);
        in_pwd.setFont(new Font("",Font.BOLD,25));
        in_pwd.addActionListener(this);
        add(in_pwd);

        /**
         * 确认按钮
         */
        enter = new JButton("确定");
        enter.setBounds(410,310,150,50);
        enter.setFont(new Font("楷体",Font.BOLD,20));
        enter.addActionListener(this);
        add(enter);

    }

    /**
     * 对菜单栏以及按钮进行监听
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

        if ( e.getSource() == enter || e.getSource() == in_pwd ) {
            Admin admin = new Admin();
            admin.setName(in_name.getText());
            admin.setPwd(new String(in_pwd.getPassword()));
            AdminDao adminDao = new AdminDao();
            try {
                adminDao.insertAll(admin);//插入数据库
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
