package main;

import course.Course;
import course.CourseList;
import course.CourseScore;
import course.ScoreList;
import user.Admin;
import user.UserList;
import junit.framework.TestCase;

public class MainSystemTest extends TestCase {

	public void testGetUserList() {
		MainSystem system = new MainSystem();
		UserList userList = system.getUserList();
		Admin newAdmin = new Admin();
		newAdmin.setName("123");
		newAdmin.setId("12");
		userList.getAdminList().add(newAdmin);
		assertTrue(system.getUserList() == userList);
	}

	public void testGetCourseList() {
		MainSystem system = new MainSystem();
		CourseList courseList = system.getCourseList();
		Course newCourse = new Course();
		newCourse.setClassroom("123");
		courseList.getCourseList().add(newCourse);
		assertTrue(system.getCourseList() == courseList);
	}

	public void testGetScoreList() {
		MainSystem system = new MainSystem();
		ScoreList scoreList = system.getScoreList();
		CourseScore courseScore = new CourseScore();
		courseScore.setStudentNum(100);
		scoreList.getCourseScoreList().add(courseScore);
		assertTrue(system.getScoreList() == scoreList);
	}

}
