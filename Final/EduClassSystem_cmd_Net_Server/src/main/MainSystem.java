package main;

import net.Server;
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
	Server server;

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
		server = new Server();
		while (true) {
			instruction.refresh(getCourseList().getCourseList(), getUserList());			
			cmd = server.receiveMessage("Login/Register");
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
			server.sendMessage("Wrong Input");
			break;
		}
	}

	private void login() {

		User newUser = new User();

		server.sendMessage("----Login----");

		server.sendMessage("Group(admin/teacher/student):");
		newUser.setType(server.receiveMessage());

		server.sendMessage("Id:");
		newUser.setId(server.receiveMessage());

		server.sendMessage("Password:");
		newUser.setPassword(server.receiveMessage());

		instruction.logIn(newUser, getUserList(), getCourseList(), getScoreList(),server);

	}

	private void register() {

		User newUser = new User();

		server.sendMessage("----Register----");

		server.sendMessage("Group(teacher/student):");
		newUser.setType(server.receiveMessage());

		server.sendMessage("Id:");
		newUser.setId(server.receiveMessage());
		
		server.sendMessage("Name:");
		newUser.setName(server.receiveMessage());

		server.sendMessage("Password:");
		newUser.setPassword(server.receiveMessage());

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
