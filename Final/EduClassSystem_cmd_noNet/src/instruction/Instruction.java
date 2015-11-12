package instruction;

import java.util.ArrayList;

import course.*;
import user.*;
import io.*;

public class Instruction {

	WriteData output = new WriteData();
	ReadData input = new ReadData();
	String line;

	private void admin(User admin, UserList userList, CourseList courseList,ScoreList scoreList) {
		while (true) {
			output.println("Logout/ChangePassword/Show/Delete");	
			line = input.getString();
			if (line.equals("Logout")) {
				break;
			}			
			Admin newAdmin = (Admin) (admin);
			switch (line) {
			case "ChangePassword":
				newAdmin.changePassword();
				admin = newAdmin;
				break;
			case "Show":
				newAdmin.show(userList, courseList);
				break;
			case "Delete":
				newAdmin.delete(userList, courseList);
				break;
			default:
				output.println("Wrong Input");
				break;
			}
			output.save(userList, courseList, scoreList);
		}
		
		output.println("----Logout----");

	}


	private void student(User student,UserList userList ,ScoreList scoreList,
			CourseList courseList) {
		while (true) {
			output.println("Logout/ShowMycourseList/SelectCourse/QuitCourse/ShowScore");
			line = input.getLine();
			if (line.equals("Logout"))
				break;
			Student newStudent = (Student) (student);
			switch (line) {
			case "ShowMycourseList":
				newStudent.showMyCourseList(scoreList, courseList);
				break;
			case "SelectCourse":
				newStudent.selectCourse(scoreList, courseList);
				break;
			case "QuitCourse":
				newStudent.quitCourse(scoreList, courseList);
				break;
			case "ShowScore":
				newStudent.showScore(scoreList, courseList);
				break;
			default:
				output.println("Wrong Input");
				break;
			}
			output.save(userList, courseList, scoreList);
		}
	}

	private void teacher(User teacher, UserList userList,
			CourseList courseList, ScoreList scoreList) {
		while (true) {
			output.println("Logout/Publish/ShowCourse/UpdateCourse/ShowStudent/RecordScore");
			line = input.getLine();
			if (line.equals("Logout"))
				break;
			Teacher newTeacher = (Teacher) (teacher);
			switch (line) {
			case "Publish":
				newTeacher.publish(courseList,scoreList);
				refresh(courseList.getCourseList(), userList);
				break;
			case ("ShowCourse"):
				newTeacher.showCourse(courseList);
				break;
			case ("UpdateCourse"):
				newTeacher.updateCourse(courseList);
				break;
			case ("ShowStudent"):
				newTeacher.showStudent(scoreList);
				break;
			case ("RecordScore"):
				newTeacher.recordScore(scoreList);
				break;
			default:
				output.println("Wrong Input");
				break;
			}
			output.save(userList, courseList, scoreList);
		}
	}
	
	public void logIn(User user, UserList userList, CourseList courseList,
			ScoreList scoreList) {

		int count;
		switch (user.getType()) {
		case "admin":
			Admin newAdmin = new Admin();
			newAdmin.setId(user.getId());
			newAdmin.setPassword(user.getPassword());
			newAdmin.setType(user.getType());
			count = canLogin(newAdmin, userList.getAdminList());
			if (count >= 0) {
				admin(userList.getAdminList().get(count), userList, courseList, scoreList);
			} else {
				output.println("Wrong Id or Password");
			}
			break;
		case "student":
			Student newStudent = new Student();
			newStudent.setId(user.getId());
			newStudent.setPassword(user.getPassword());
			newStudent.setType(user.getType());
			count = canLogin(newStudent, userList.getStudentList());
			if (count >= 0) {
				student(userList.getStudentList().get(count), userList, scoreList,
						courseList);
			} else {
				output.println("Wrong Id or Password");
			}
			break;
		case "teacher":
			Teacher newTeacher = new Teacher();
			newTeacher.setId(user.getId());
			newTeacher.setPassword(user.getPassword());
			newTeacher.setType(user.getType());
			count = canLogin(newTeacher, userList.getTeacherList());
			if (count >= 0) {
				teacher(userList.getTeacherList().get(count), userList,
						courseList, scoreList);
			} else {
				output.println("Wrong Id or Password");
			}
			break;
		default:
			output.println("Wrong Input");
			break;
		}

	}
	
	public void register(User user, UserList userList, ScoreList scoreList, CourseList courseList) {

		int count;
		switch (user.getType()) {
		case "student":
			Student newStudent = new Student();
			newStudent.setId(user.getId());
			newStudent.setPassword(user.getPassword());
			newStudent.setType(user.getType());
			if (!(newStudent.getId().equals("") || newStudent.getPassword()
					.equals(""))) {
				count = isExist(newStudent, userList.getStudentList());
				if (count < 0) {
					userList.getStudentList().add(newStudent);
			
					StudentScore studentScore = new StudentScore();
					studentScore.setCourseNum(0);
					studentScore.setStudentId(newStudent.getId());
					studentScore.setScore(new ArrayList<Integer>());
					studentScore.setCourseList(new ArrayList<String>());
					scoreList.getStudentScoreList().add(studentScore);
					
					output.save(userList, courseList, scoreList);

					output.println("Succeed!");
					break;
				} else {
					output.println("The Id Exists");
					break;
				}
			} else {
				output.println("A blank Id or Password");
			}
			break;
		case "teacher":
			Teacher newTeacher = new Teacher();
			newTeacher.setId(user.getId());
			newTeacher.setPassword(user.getPassword());
			newTeacher.setType(user.getType());
			if (!(newTeacher.getId().equals("") || newTeacher.getPassword()
					.equals(""))) {
				count = isExist(newTeacher, userList.getTeacherList());
				if (count < 0) {
					userList.getTeacherList().add(newTeacher);
					output.println("Succeed!");
					break;
				} else {
					output.println("The Id Exists");
					break;
				}
			} else {
				output.println("A blank Id or Password");
			}
			break;
		default:
			output.println("Wrong Input");
			break;
		}

	}

	private int isExist(User user, ArrayList<User> list) {

		User newUser;
		int i;
		for (i = list.size() - 1; i >= 0; i--) {
			newUser = list.get(i);
			if (newUser.getId().equals(user.getId())) {
				break;
			}

		}
		return i;

	}
	
	private int canLogin(User user, ArrayList<User> list) {

		User newUser;
		int i;
		for (i = list.size() - 1; i >= 0; i--) {
			newUser = list.get(i);
			if (newUser.getId().equals(user.getId())
					&& newUser.getPassword().equals(user.getPassword())) {
				break;
			}

		}
		return i;

	}

	public void refresh(ArrayList<Course> courseList, UserList userList) {

		ArrayList<User> teacherList = userList.getTeacherList();
		ArrayList<User> studentList = userList.getStudentList();

		for (int i = 0; i < courseList.size(); i++) {
			for (int j = 0; j < teacherList.size(); j++) {
				if (courseList.get(i).getTeacherUser().getId()
						.equals(teacherList.get(j).getId())) {
					courseList.get(i).setTeacherUser((teacherList.get(j)));
					break;
				}
			}

			for (int j = 0; j < courseList.get(i).getAssistStudentList().size(); j++) {
				for (int k = 0; k < studentList.size(); k++) {
					if (courseList.get(i).getAssistStudentList().get(j).getId()
							.equals(studentList.get(k).getId())) {
						courseList.get(i).getAssistStudentList()
								.set(j, studentList.get(k));
					}
				}
			}
		}

	}
}
