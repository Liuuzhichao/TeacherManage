package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.AdminDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Cc on 2017/7/5.
 */
public class DeleteAdmin extends JFrame implements ActionListener {

    JTextField in_name;
    JPasswordField in_pwd;
    JButton enter;
    JMenuItem query_name, query_id;
    JMenuItem add_teacher, add_admin;
    JMenuItem del_teacher, del_admin;
    JMenuItem backUp;

    public DeleteAdmin(){
        init();
        setBounds(300,100,1000,700);
        setTitle("删除管理员");
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
         * 提示标签
         */
        JLabel jLabel = new JLabel("请输入该管理员的信息",JLabel.CENTER);
        jLabel.setBounds(90,50,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        JLabel name = new JLabel("用户名");
        name.setBounds(350,150,100,40);
        name.setFont(new Font("楷体",Font.BOLD,25));
        add(name);

        JLabel pwd = new JLabel("密 码");
        pwd.setBounds(350,210,100,40);
        pwd.setFont(new Font("楷体",Font.BOLD,25));
        add(pwd);

        /**
         * 文本框
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
         * 按钮
         */
        enter = new JButton("确定");
        enter.setBounds(410,310,150,50);
        enter.setFont(new Font("楷体",Font.BOLD,20));
        enter.addActionListener(this);
        add(enter);

    }

    /**
     * 菜单栏和按钮监听
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
            String name = in_name.getText();
            String passWord = new String(in_pwd.getPassword());
            String pwd = null;
            AdminDao adminDao = new AdminDao();
            try {
                pwd = adminDao.getPwd(name);//得到密码
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            //判断密码是否正确
            if ( passWord.equals(pwd) ) {
                try {
                    adminDao.deleteByName(name);//删除数据
                    JOptionPane.showMessageDialog(this,"删除成功","正确",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    WindowsMain windowsMain = new WindowsMain();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this,"您输入账号与密码不符","错误",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
