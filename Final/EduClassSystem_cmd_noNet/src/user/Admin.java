package user;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import course.Course;
import course.CourseList;
import io.ReadData;
import io.WriteData;

public class Admin extends User implements ChangePassword, AdminFunction,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadData input = new ReadData();
	private WriteData output = new WriteData();

	public void changePassword() {
		String[] password = new String[2];
		output.print("New Password:");
		password[0] = input.getString();
		output.print("Repeat New Password:");
		password[1] = input.getString();
		if (password[0].equals(password[1])) {
			this.setPassword(password[0]);
			output.println("Succeed!");
		} else {
			output.println("Wrong Input");
		}
	}

	public void show(UserList userList, CourseList courseList) {
		output.println("teacherlist/studentlist/courselist/chooselist");
		output.print("list:");
		String list = input.getString();
		switch (list) {
		case "teacherlist":
			showUserList(userList.getTeacherList());
			break;
		case "studentlist":
			showUserList(userList.getStudentList());
			break;
		case "courselist":
			showCourseList(courseList.getCourseList());
			break;
		case "chooselist":
			showChooseList(courseList.getCourseList());
			break;
		default:
			break;
		}
	}

	public void delete(UserList userList, CourseList courseList) {
		output.println("teacherlist/studentlist/courselist/chooselist");
		output.print("list:");
		int number;
		String inputList = input.getString();
		switch (inputList) {
		case "teacherlist":
			showUserList(userList.getTeacherList());
			output.print("Number:");
			number = input.getInt();
			if (number > 0 && number <= userList.getTeacherList().size()) {
				userList.getTeacherList().remove(number - 1);
				output.println("Succeed!");
			} else {
				output.println("Wrong Input");
			}
			break;
		case "studentlist":
			showUserList(userList.getStudentList());
			output.print("Number:");
			number = input.getInt();
			if (number > 0 && number <= userList.getStudentList().size()) {
				userList.getStudentList().remove(number - 1);
				output.println("Succeed!");
			} else {
				output.println("Wrong Input");
			}
			break;
		case "courselist":
			showCourseList(courseList.getCourseList());
			output.print("Number:");
			number = input.getInt();
			if (number > 0 && number <= courseList.getCourseList().size()) {
				courseList.getCourseList().remove(number - 1);
				output.println("Succeed!");
			} else {
				output.println("Wrong Input");
			}
			break;
		case "chooselist":
			showChooseList(courseList.getCourseList());
			output.print("Number:");
			number = input.getInt();
			int count = 0;
			for (int i = 0; i < courseList.getCourseList().size(); i++) {
				if (!courseList.getCourseList().get(i).isCompulsory()) {
					count++;
					if (count == number) {
						courseList.getCourseList().remove(i);
						output.println("Succeed!");
						break;
					}
				}
			}
			if (count != number) {
				output.println("Wrong Input");
			}
			break;
		default:
			output.println("Wrong Input");
			break;
		}
	}
	
	private void showUserList(ArrayList<User> list) {

		output.println("No./Id./Name");
		DecimalFormat df = new DecimalFormat("0000");
		for (int i = 0; i < list.size(); i++) {
			output.println(df.format(i + 1) + " " + list.get(i).getId()
					+ " " + list.get(i).getName());
		}

	}

	private void showCourseList(ArrayList<Course> list) {
		output
				.println("No./Id./Name/Compulsory/Period/Score/Teacher/Time/Classroom/Assistants");
		DecimalFormat df = new DecimalFormat("0000");
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			count++;
			output.print(df.format(count) + " ");
			output.print(list.get(i).getCourseId() + " ");
			output.print(list.get(i).getCourseName() + " ");
			output.print(list.get(i).isCompulsory() + " ");
			output.print(list.get(i).getPeriod() + " ");
			output.print(list.get(i).getScore() + " ");
			output.print(list.get(i).getTeacherUser().getName() + " ");
			output.print(list.get(i).getTime() + " ");
			output.print(list.get(i).getClassroom() + " ");
			ArrayList<User> assistList = list.get(i).getAssistStudentList();
			for (int j = 0; j < assistList.size(); j++) {
				output.print(assistList.get(j).getName() + " ");
			}
			output.println("");
		}
	}

	private void showChooseList(ArrayList<Course> list) {
		output
				.println("No./Id./Name/Compulsory/Period/Score/Teacher/Time/Classroom/Assistants");
		DecimalFormat df = new DecimalFormat("0000");
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).isCompulsory()) {
				count++;
				output.print(df.format(count) + " ");
				output.print(list.get(i).getCourseId() + " ");
				output.print(list.get(i).getCourseName() + " ");
				output.print(list.get(i).isCompulsory() + " ");
				output.print(list.get(i).getPeriod() + " ");
				output.print(list.get(i).getScore() + " ");
				output.print(list.get(i).getTeacherUser().getName() + " ");
				output.print(list.get(i).getTime() + " ");
				output.print(list.get(i).getClassroom() + " ");
				ArrayList<User> assistList = list.get(i).getAssistStudentList();
				for (int j = 0; j < assistList.size(); j++) {
					output.print(assistList.get(j).getName() + " ");
				}
				output.println("");
			}
		}
	}
}
