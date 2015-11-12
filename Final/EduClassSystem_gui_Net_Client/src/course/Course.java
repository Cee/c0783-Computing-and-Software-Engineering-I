package course;

import java.io.Serializable;
import java.util.ArrayList;

import user.*;

public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseId;// �γ�id
	private String courseName;// �γ���
	private boolean isCompulsory;// �Ƿ����
	private int period;// ѧʱ
	private int score;// ѧ��
	private User teacherUser;// ��ʦ
	private String time;
	private String classroom;
	private ArrayList<User> assistStudentList;

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public boolean isCompulsory() {
		return isCompulsory;
	}

	public void setCompulsory(boolean isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(User newTeacher) {
		this.teacherUser = newTeacher;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public ArrayList<User> getAssistStudentList() {
		return assistStudentList;
	}

	public void setAssistStudentList(ArrayList<User> assistStudentList) {
		this.assistStudentList = assistStudentList;
	}

}
