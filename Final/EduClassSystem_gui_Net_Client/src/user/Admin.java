package user;

import java.io.Serializable;

import javax.swing.JOptionPane;

import course.CourseList;

public class Admin extends User implements ChangePassword, AdminFunction,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void changePassword(String password1,String password2,UserList userList) {
		String[] password = new String[2];
		password[0] = password1;
		password[1] = password2;
		if (password[0].equals(password[1])) {
			userList.getAdminList().get(0).setPassword(password[0]);
			JOptionPane.showMessageDialog(null,"Succeed");
		} else {
			JOptionPane.showMessageDialog(null,"Wrong Input");
		}
	}

	public void delete(String list,Integer num,UserList userList, CourseList courseList) {
		int number = num;
		String inputList = list;
		switch (inputList) {
		case "teacherlist":
			userList.getTeacherList().remove(number);
			JOptionPane.showMessageDialog(null,"Succeed");
			break;
		case "studentlist":
			userList.getStudentList().remove(number);
			JOptionPane.showMessageDialog(null,"Succeed");
			break;
		case "courselist":
			courseList.getCourseList().remove(number);
			JOptionPane.showMessageDialog(null,"Succeed");
			break;
		case "chooselist":
			int count = -1;
			for (int i = 0; i < courseList.getCourseList().size(); i++) {
				if (!courseList.getCourseList().get(i).isCompulsory()) {
					count++;
					if (count == number) {
						courseList.getCourseList().remove(i);
						break;
					}
				}
			}
			JOptionPane.showMessageDialog(null,"Succeed");
			break;
		default:
			break;
		}
	}
	
}
