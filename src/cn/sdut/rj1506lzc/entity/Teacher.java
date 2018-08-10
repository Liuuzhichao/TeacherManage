package cn.sdut.rj1506lzc.entity;

/**
 * Created by Cc on 2017/6/27.
 */
public class Teacher {

    private int id; //教师编号
    private String name; //教师姓名
    private String course; //所授课程
    private String position; //职位

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
