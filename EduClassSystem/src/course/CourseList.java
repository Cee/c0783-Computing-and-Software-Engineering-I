package course;

import io.ReadData;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Course> courseList;
	private ReadData input = new ReadData();

	public CourseList() {
		this.setCourseList(input.initialCourseList());
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

}
