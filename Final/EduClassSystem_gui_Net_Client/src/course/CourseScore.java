package course;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseScore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseId;
	private int studentNum;
	private ArrayList<String> studentList = new ArrayList<String>();
	private ArrayList<Integer> score = new ArrayList<Integer>();

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public ArrayList<Integer> getScore() {
		return score;
	}

	public void setScore(ArrayList<Integer> score) {
		this.score = score;
	}

	public ArrayList<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<String> studentList) {
		this.studentList = studentList;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

}
