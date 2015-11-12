package main;

import course.CourseList;
import course.ScoreList;
import io.*;
import user.*;
import instruction.*;

public class MainSystem {

	ReadData input;
	WriteData output;
	Instruction instruction;
	private UserList userList;
	private CourseList courseList;
	private ScoreList scoreList;

	public MainSystem() {

		this.setUserList(new UserList());
		this.setCourseList(new CourseList());
		this.setScoreList(new ScoreList());

		this.input = new ReadData();
		this.output = new WriteData();
		this.instruction = new Instruction();
		instruction.refresh(getCourseList().getCourseList(), getUserList());

	}


	public static void main(String[] args) {

		MainSystem eduSystem = new MainSystem();
		eduSystem.goCommand();

	}

	public void goCommand() {
		String cmd;
		while (true) {
			instruction.refresh(getCourseList().getCourseList(), getUserList());
			output.println("Login/Register");
			cmd = input.getString();
			checkCmd(cmd);
		}
	}

	private void checkCmd(String command) {
		switch (command) {
		case "Login":
			login();
			break;
		case "Register":
			register();
			break;
		default:
			output.println("Wrong Input");
			break;
		}
	}

	private void login() {

		User newUser = new User();

		output.println("----Login----");

		output.print("Group(admin/teacher/student):");
		newUser.setType(input.getString());

		output.print("Id:");
		newUser.setId(input.getString());

		output.print("Password:");
		newUser.setPassword(input.getString());

		instruction.logIn(newUser, getUserList(), getCourseList(), getScoreList());

	}

	private void register() {

		User newUser = new User();

		output.println("----Register----");

		output.print("Group(teacher/student):");
		newUser.setType(input.getString());

		output.print("Id:");
		newUser.setId(input.getString());
		
		output.print("Name:");
		newUser.setName(input.getString());

		output.print("Password:");
		newUser.setPassword(input.getString());

		instruction.register(newUser, getUserList(), getScoreList(), getCourseList());
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
