package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.TeacherDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Cc on 2017/7/5.
 */
public class SelectById  extends JFrame implements ActionListener{

    JTextField in_id;
    JButton enter;
    JMenuItem query_name, query_id;
    JMenuItem add_teacher, add_admin;
    JMenuItem del_teacher, del_admin;
    JMenuItem backUp;
    JLabel infor_out;

    public SelectById(){
        init();
        setBounds(240,45,1000,700);
        setTitle("按编号查询教师信息");
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
         * 标签
         */
        JLabel jLabel = new JLabel("请输入教师的编号",JLabel.CENTER);
        jLabel.setBounds(90,50,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        JLabel id = new JLabel("编  号");
        id.setBounds(350,150,100,40);
        id.setFont(new Font("楷体",Font.BOLD,25));
        add(id);

        /**
         * 文本框
         */
        in_id = new JTextField(12);
        in_id.setBounds(460,150,160,40);
        in_id.setFont(new Font("楷体",Font.BOLD,25));
        in_id.addActionListener(this);
        add(in_id);

        /**
         * 按钮
         */
        enter = new JButton("确定");
        enter.setBounds(410,240,150,50);
        enter.setFont(new Font("楷体",Font.BOLD,20));
        enter.addActionListener(this);
        add(enter);

        /**
         * 日志信息文本框
         */
        infor_out = new JLabel();
        infor_out.setBounds(260,320,800,50);
        infor_out.setFont(new Font("楷体",Font.BOLD,20));
        add(infor_out);

    }

    /**
     * 菜单栏 按钮 监听
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

        if(e.getSource() == enter || e.getSource() == in_id ) {
            String id = in_id.getText();//获得输入的ID
            TeacherDao teacherDao = new TeacherDao();
            String st = null;
            try {
                st = teacherDao.selectById(id);//根据ID查找信息
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            if( st == null ) { //ID不存在
                JOptionPane.showMessageDialog(this,"编号不存在","错误",JOptionPane.ERROR_MESSAGE);
            } else {
                infor_out.setText(st);//日志信息
            }
        }
    }

}
