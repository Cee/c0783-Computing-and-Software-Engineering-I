package instruction;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.MainSystem;
import course.*;
import user.*;
import io.*;

public class Instruction {

	WriteData output = new WriteData();
	ReadData input = new ReadData();
	String line;
	MainSystem eduSystem;

	public String logIn(User user, UserList userList, CourseList courseList,
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
				return "admin";
			} else {
				JOptionPane.showMessageDialog(null,"Wrong Id or Password");
			}
			return "null";
		case "student":
			Student newStudent = new Student();
			newStudent.setId(user.getId());
			newStudent.setPassword(user.getPassword());
			newStudent.setType(user.getType());
			count = canLogin(newStudent, userList.getStudentList());
			if (count >= 0) {
				return "student";
			} else {
				JOptionPane.showMessageDialog(null,"Wrong Id or Password");
			}
			return "null";
		case "teacher":
			Teacher newTeacher = new Teacher();
			newTeacher.setId(user.getId());
			newTeacher.setPassword(user.getPassword());
			newTeacher.setType(user.getType());
			count = canLogin(newTeacher, userList.getTeacherList());
			if (count >= 0) {
				return "teacher";
			} else {
				JOptionPane.showMessageDialog(null,"Wrong Id or Password");
			}
			return "null";
		default:
			JOptionPane.showMessageDialog(null,"Wrong Input");
			return "null";
		}

	}
	
	public String register(User user, UserList userList, ScoreList scoreList, CourseList courseList) {

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

					JOptionPane.showMessageDialog(null,"Succeed");
					return "student";
				} else {
					JOptionPane.showMessageDialog(null,"The Id Exists");
					return "null";
				}
			} else {
				JOptionPane.showMessageDialog(null,"A blank Id or Password");
				return "null";
			}
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
					JOptionPane.showMessageDialog(null,"Succeed");
					return "teacher";
				} else {
					JOptionPane.showMessageDialog(null,"The Id Exists");
					return "null";
				}
			} else {
				JOptionPane.showMessageDialog(null,"A blank Id or Password");
				return "null";
			}
		default:
			JOptionPane.showMessageDialog(null,"Wrong Input");
			
		}
		output.save(userList, courseList, scoreList);
		return "null";
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
