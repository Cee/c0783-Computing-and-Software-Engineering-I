package course;

import io.ReadData;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CourseScore> courseScoreList = new ArrayList<CourseScore>();
	private ArrayList<StudentScore> studentScoreList = new ArrayList<StudentScore>();
	private ReadData input = new ReadData();

	public ScoreList() {
		this.setStudentScoreList(input.initialStudentScoreList());
		this.setCourseScoreList(input.initialCourseScoreList());
	}

	public ArrayList<CourseScore> getCourseScoreList() {
		return courseScoreList;
	}

	public void setCourseScoreList(ArrayList<CourseScore> courseScoreList) {
		this.courseScoreList = courseScoreList;
	}

	public ArrayList<StudentScore> getStudentScoreList() {
		return studentScoreList;
	}

	public void setStudentScoreList(ArrayList<StudentScore> studentScoreList) {
		this.studentScoreList = studentScoreList;
	}
}
