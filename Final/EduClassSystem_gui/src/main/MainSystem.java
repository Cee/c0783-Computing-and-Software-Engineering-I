package main;

import course.CourseList;
import course.ScoreList;
import io.*;
import user.*;
import instruction.*;

public class MainSystem {

	ReadData input;
	public WriteData output;
	Instruction instruction;
	private UserList userList;
	private CourseList courseList;
	private ScoreList scoreList;
	
	Login loginFrame;

	public MainSystem() {

		this.setUserList(new UserList());
		this.setCourseList(new CourseList());
		this.setScoreList(new ScoreList());

		this.input = new ReadData();
		this.output = new WriteData();
		this.instruction = new Instruction();
		instruction.refresh(getCourseList().getCourseList(), getUserList());
		
		loginFrame = new Login(this,this.instruction);
		loginFrame.setVisible(true);

	}


	public static void main(String[] args) {
		new MainSystem();
	}


	public UserList getUserList() {
		return userList;
	}

	public CourseList getCourseList() {
		return courseList;
	}

	public ScoreList getScoreList() {
		return scoreList;
	}

	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	public void setCourseList(CourseList courseList) {
		this.courseList = courseList;
	}

	public void setScoreList(ScoreList scoreList) {
		this.scoreList = scoreList;
	}
	
}
