package user;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<User> adminList;
	private ArrayList<User> teacherList;
	private ArrayList<User> studentList;

	public ArrayList<User> getAdminList() {
		return adminList;
	}

	public ArrayList<User> getTeacherList() {
		return teacherList;
	}

	public ArrayList<User> getStudentList() {
		return studentList;
	}

	public void setAdminList(ArrayList<User> adminList) {
		this.adminList = adminList;
	}

	public void setTeacherList(ArrayList<User> teacherList) {
		this.teacherList = teacherList;
	}

	public void setStudentList(ArrayList<User> studentList) {
		this.studentList = studentList;
	}

}
