package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.AdminDao;
import cn.sdut.rj1506lzc.dao.TeacherDao;
import cn.sdut.rj1506lzc.entity.Log;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Cc on 2017/6/27.
 */
public class LogIn extends JFrame implements ActionListener {

    private JButton jButton;
    private JTextField jTextField;
    private JPasswordField jPasswordField;
    private JRadioButton jRadioButton1, jRadioButton2;

    public LogIn() {
        init();
        setBounds(240,45,1000,700);
        setTitle("教师管理系统");
        setVisible(true);
        setResizable(false); //窗口大小不可调整
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {

        setLayout(null);

        /**
         * 用户名和用户密码标签设置
         */
        JLabel userName = new JLabel("用户名");
        userName.setBounds(380,200,100,40);
        userName.setFont(new Font("楷体",Font.BOLD,20));
        add(userName);

        JLabel userPw = new JLabel("用户密码");
        userPw.setBounds(380,300,100,40);
        userPw.setFont(new Font("楷体",Font.BOLD,20));
        add(userPw);

        /**
         * 用户名和用户密码的输入框设置
         */
        jTextField = new JTextField(12);
        jTextField.setBounds(480,200,100,40);
        jTextField.addActionListener(this);
        add(jTextField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(480,300,100,40);
        jPasswordField.addActionListener(this);
        add(jPasswordField);

        /**
         * 单选按钮的设置
         */
        ButtonGroup buttonGroup = new ButtonGroup();
        jRadioButton1 = new JRadioButton("管理员");
        jRadioButton2 = new JRadioButton("教师");
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        add(jRadioButton1);
        add(jRadioButton2);
        jRadioButton1.setBounds(390,370,100,40);
        jRadioButton2.setBounds(490,370,100,40);

        /**
         * 登录按钮设置
         */
        jButton = new JButton("登录");
        jButton.setBounds(435,450,100,60);
        jButton.setFont(new Font("楷体",Font.BOLD,20));
        jButton.addActionListener(this);
        add(jButton);
    }

    /**
     * 对单选按钮以及登录按钮进行监听
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == jButton ) {
            //得到用户名和密码
            String userName = jTextField.getText();
            String userPw = new String(jPasswordField.getPassword());
            String pwd = "";
            if ( jRadioButton1.isSelected() ) {
                AdminDao adminDao = new AdminDao();
                try {
                    pwd = adminDao.getPwd(userName);//从数据库获得密码
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                if ( userPw.equals(pwd) ) {//密码匹配
                    JOptionPane.showMessageDialog(this,"登录成功",
                            "正确",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    try {
                        WindowsMain windowsMain = new WindowsMain();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"您输入账号与密码不符",
                            "错误",JOptionPane.ERROR_MESSAGE);
                }
            } else if ( jRadioButton2.isSelected() ) {
                Log log = new Log();
                log.setName(userName);//将用户名传入下一页面,用于打卡操作
                TeacherDao teacherDao = new TeacherDao();
                try {
                    pwd = teacherDao.getPwd(userName);//得到密码
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                if ( userPw.equals(pwd) ) {//密码匹配
                    JOptionPane.showMessageDialog(this,"登录成功","正确",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    try {
                        WindowsLayout windowsLayout = new WindowsLayout(log);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"您输入账号与密码不符，请重新输入！","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
}
