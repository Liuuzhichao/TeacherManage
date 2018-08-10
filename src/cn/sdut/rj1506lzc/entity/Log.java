package cn.sdut.rj1506lzc.entity;

/**
 * Created by Cc on 2017/6/28.
 */
public class Log {

    private int id; //教师编号
    private String name; //教师姓名
    private String course; //所授课程
    private String startTime; //开始授课时间
    private String endTime; //结束授课时间
    private String time; //授课时长

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
