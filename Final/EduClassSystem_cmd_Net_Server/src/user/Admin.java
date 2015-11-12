package user;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import net.Server;
import course.Course;
import course.CourseList;

public class Admin extends User implements ChangePassword, AdminFunction,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void changePassword(Server server) {
		String[] password = new String[2];
		server.sendMessage("New Password:");
		password[0] = server.receiveMessage();
		server.sendMessage("Repeat New Password:");
		password[1] = server.receiveMessage();
		if (password[0].equals(password[1])) {
			this.setPassword(password[0]);
			server.sendMessage("Succeed!");
		} else {
			server.sendMessage("Wrong Input");
		}
	}

	public void show(UserList userList, CourseList courseList, Server server) {
		server.sendMessage("teacherlist/studentlist/courselist/chooselist");
		server.sendMessage("list:");
		String list = server.receiveMessage();
		switch (list) {
		case "teacherlist":
			showUserList(userList.getTeacherList(), server);
			break;
		case "studentlist":
			showUserList(userList.getStudentList(), server);
			break;
		case "courselist":
			showCourseList(courseList.getCourseList(), server);
			break;
		case "chooselist":
			showChooseList(courseList.getCourseList(), server);
			break;
		default:
			break;
		}
	}

	public void delete(UserList userList, CourseList courseList,Server server) {
		server.sendMessage("teacherlist/studentlist/courselist/chooselist");
		server.sendMessage("list:");
		int number;
		String inputList = server.receiveMessage();
		switch (inputList) {
		case "teacherlist":
			showUserList(userList.getTeacherList(), server);
			server.sendMessage("Number:");
			number = Integer.parseInt(server.receiveMessage());
			if (number > 0 && number <= userList.getTeacherList().size()) {
				userList.getTeacherList().remove(number - 1);
				server.sendMessage("Succeed!");
			} else {
				server.sendMessage("Wrong Input");
			}
			break;
		case "studentlist":
			showUserList(userList.getStudentList(), server);
			server.sendMessage("Number:");
			number = Integer.parseInt(server.receiveMessage());
			if (number > 0 && number <= userList.getStudentList().size()) {
				userList.getStudentList().remove(number - 1);
				server.sendMessage("Succeed!");
			} else {
				server.sendMessage("Wrong Input");
			}
			break;
		case "courselist":
			showCourseList(courseList.getCourseList(), server);
			server.sendMessage("Number:");
			number = Integer.parseInt(server.receiveMessage());
			if (number > 0 && number <= courseList.getCourseList().size()) {
				courseList.getCourseList().remove(number - 1);
				server.sendMessage("Succeed!");
			} else {
				server.sendMessage("Wrong Input");
			}
			break;
		case "chooselist":
			showChooseList(courseList.getCourseList(), server);
			server.sendMessage("Number:");
			number = Integer.parseInt(server.receiveMessage());
			int count = 0;
			for (int i = 0; i < courseList.getCourseList().size(); i++) {
				if (!courseList.getCourseList().get(i).isCompulsory()) {
					count++;
					if (count == number) {
						courseList.getCourseList().remove(i);
						server.sendMessage("Succeed!");
						break;
					}
				}
			}
			if (count != number) {
				server.sendMessage("Wrong Input");
			}
			break;
		default:
			server.sendMessage("Wrong Input");
			break;
		}
	}
	
	private void showUserList(ArrayList<User> list, Server server) {

		server.sendMessage("No./Id./Name");
		DecimalFormat df = new DecimalFormat("0000");
		for (int i = 0; i < list.size(); i++) {
			server.sendMessage(df.format(i + 1) + " " + list.get(i).getId()
					+ " " + list.get(i).getName());
		}

	}

	private void showCourseList(ArrayList<Course> list, Server server) {
		server.sendMessage("No./Id./Name/Compulsory/Period/Score/Teacher/Time/Classroom/Assistants");
		DecimalFormat df = new DecimalFormat("0000");
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			count++;
			String string = null;
			string = (String)(df.format(count)) + " ";
			string = string + list.get(i).getCourseId() + " ";
			string = string + list.get(i).getCourseName() + " ";
			string = string + list.get(i).isCompulsory() + " ";
			string = string + list.get(i).getPeriod() + " ";
			string = string + list.get(i).getScore() + " ";
			string = string + list.get(i).getTeacherUser().getName() + " ";
			string = string + list.get(i).getTime() + " ";
			string = string + list.get(i).getClassroom() + " ";
			ArrayList<User> assistList = list.get(i).getAssistStudentList();
			for (int j = 0; j < assistList.size(); j++) {
				string = string + assistList.get(j).getName() + " ";
			}
			server.sendMessage(string);
		}
	}

	private void showChooseList(ArrayList<Course> list, Server server) {
		server.sendMessage("No./Id./Name/Compulsory/Period/Score/Teacher/Time/Classroom/Assistants");
		DecimalFormat df = new DecimalFormat("0000");
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).isCompulsory()) {
				count++;
				String string = null;
				string = (String)(df.format(count)) + " ";
				string = string + list.get(i).getCourseId() + " ";
				string = string + list.get(i).getCourseName() + " ";
				string = string + list.get(i).isCompulsory() + " ";
				string = string + list.get(i).getPeriod() + " ";
				string = string + list.get(i).getScore() + " ";
				string = string + list.get(i).getTeacherUser().getName() + " ";
				string = string + list.get(i).getTime() + " ";
				string = string + list.get(i).getClassroom() + " ";
				ArrayList<User> assistList = list.get(i).getAssistStudentList();
				for (int j = 0; j < assistList.size(); j++) {
					string = string + assistList.get(j).getName() + " ";
				}
				server.sendMessage(string);
			}
		}
	}
}
