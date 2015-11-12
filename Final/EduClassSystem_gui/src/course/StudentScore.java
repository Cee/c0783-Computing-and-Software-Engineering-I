package course;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentScore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentId;
	private int courseNum;
	private ArrayList<String> courseList = new ArrayList<String>();
	private ArrayList<Integer> score = new ArrayList<Integer>();
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public ArrayList<String> getCourseList() {
		return courseList;
	}
	public void setCourseList(ArrayList<String> courseList) {
		this.courseList = courseList;
	}
	public ArrayList<Integer> getScore() {
		return score;
	}
	public void setScore(ArrayList<Integer> score) {
		this.score = score;
	}
	public int getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
}
