package cn.sdut.rj1506lzc.view;

import cn.sdut.rj1506lzc.dao.LogDao;
import cn.sdut.rj1506lzc.dao.TeacherDao;
import cn.sdut.rj1506lzc.entity.Log;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cc on 2017/7/5.
 */
public class SelectByName extends JFrame implements ActionListener{

    JTextField in_name;
    JButton enter;
    JMenuItem query_name, query_id;
    JMenuItem add_teacher, add_admin;
    JMenuItem del_teacher, del_admin;
    JMenuItem backUp;
    JLabel infor_out;
    JLabel []log_out = new JLabel[4];

    public SelectByName(){
        init();
        setBounds(240,45,1000,700);
        setTitle("按姓名查询教师信息");
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
        JLabel jLabel = new JLabel("请输入教师的姓名",JLabel.CENTER);
        jLabel.setBounds(90,50,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        JLabel name = new JLabel("姓  名");
        name.setBounds(350,150,100,40);
        name.setFont(new Font("楷体",Font.BOLD,25));
        add(name);

        /**
         * 文本框
         */
        in_name = new JTextField(12);
        in_name.setBounds(460,150,160,40);
        in_name.setFont(new Font("楷体",Font.BOLD,25));
        in_name.addActionListener(this);
        add(in_name);

        /**
         * 按钮
         */
        enter = new JButton("确定");
        enter.setBounds(410,240,150,50);
        enter.setFont(new Font("楷体",Font.BOLD,20));
        enter.addActionListener(this);
        add(enter);

        /**
         * 日志标签组
         */
        infor_out = new JLabel();
        infor_out.setBounds(260,320,800,50);
        infor_out.setFont(new Font("楷体",Font.BOLD,20));
        add(infor_out);

        int y = 380;
        for ( int i = 0; i < 4; i++ ) {
            log_out[i] = new JLabel();
            log_out[i].setBounds(60,y,900,50);
            log_out[i].setFont(new Font("楷体",Font.BOLD,20));
            add(log_out[i]);
            y += 50;
        }

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

        if(e.getSource() == enter || e.getSource() == in_name ) {
            String name = in_name.getText();
            TeacherDao teacherDao = new TeacherDao();
            LogDao logDao = new LogDao();
            ArrayList<Log> list = new ArrayList<Log>();
            String st = null;
            try {
                st = teacherDao.selectByName(name);//教师信息
                list = logDao.selectByName(name);//日志信息
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            if( st == null ) {//教师是否存在
                JOptionPane.showMessageDialog(this,
                        "编号不存在","错误",JOptionPane.ERROR_MESSAGE);
            } else {
                infor_out.setText(st);//教师信息
                for ( int i = 0; i < list.size(); i++ ) {
                    Log log = new Log();
                    log = list.get(i);
                    String ss = "开始时间:"+log.getStartTime()+"  结束时间:"+log.getEndTime()
                            +"  时长:"+log.getTime();
                    log_out[i].setText(ss);//日志信息
                }
            }
        }
    }

}
