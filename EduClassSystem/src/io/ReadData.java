package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import course.Course;
import course.CourseScore;
import course.StudentScore;

import user.*;

public class ReadData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String line;
	transient BufferedReader br;
	transient Scanner scanner = new Scanner(System.in);

	public ArrayList<User> initialAdminList() {

		ArrayList<User> adminList = new ArrayList<User>();

		try {
			br = new BufferedReader(new FileReader("data/admin.txt"));
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Admin newAdmin = new Admin();
				newAdmin.setId(split[0]);
				newAdmin.setName(null);
				newAdmin.setPassword(split[1]);
				newAdmin.setType("admin");
				adminList.add(newAdmin);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return adminList;

	}

	public ArrayList<User> initialTeacherList() {

		ArrayList<User> teacherList = new ArrayList<User>();

		try {
			br = new BufferedReader(new FileReader("data/teacher.txt"));
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Teacher newTeacher = new Teacher();
				newTeacher.setId(split[0]);
				newTeacher.setName(split[1]);
				newTeacher.setPassword(split[2]);
				newTeacher.setType("teacher");
				teacherList.add(newTeacher);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return teacherList;

	}

	public ArrayList<User> initialStudentList() {

		ArrayList<User> studentList = new ArrayList<User>();

		try {
			br = new BufferedReader(new FileReader("data/student.txt"));
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Student newStudent = new Student();
				newStudent.setId(split[0]);
				newStudent.setName(split[1]);
				newStudent.setPassword(split[2]);
				newStudent.setType("student");
				studentList.add(newStudent);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return studentList;

	}

	public ArrayList<Course> initialCourseList() {

		ArrayList<Course> courseList = new ArrayList<Course>();

		try {
			br = new BufferedReader(new FileReader("data/course.txt"));
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Course newCourse = new Course();
				newCourse.setCourseId(split[0]);
				newCourse.setCourseName(split[1]);
				newCourse.setCompulsory(split[2].equals("true") ? true : false);
				newCourse.setPeriod(Integer.parseInt(split[3]));
				newCourse.setScore(Integer.parseInt(split[4]));

				User newTeacher = new Teacher();
				newTeacher.setId(split[5]);
				newTeacher.setType("teacher");
				newTeacher.setPassword(null);
				newCourse.setTeacherUser(newTeacher);

				newCourse.setTime(split[6]);
				newCourse.setClassroom(split[7]);

				int i = 8;
				ArrayList<User> list = new ArrayList<User>();
				while (i < split.length) {
					Student newStudent = new Student();
					newStudent.setId(split[i]);
					newStudent.setType("student");
					newStudent.setPassword(null);
					list.add(newStudent);
					i++;
				}
				newCourse.setAssistStudentList(list);
				courseList.add(newCourse);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return courseList;
	}

	public ArrayList<StudentScore> initialStudentScoreList() {

		ArrayList<StudentScore> studentScoreList = new ArrayList<StudentScore>();
		ArrayList<String> id = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader("data/student.txt"));
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				id.add(split[0]);
			}

			for (int i = 0; i < id.size(); i++) {
				StudentScore newScore = new StudentScore();
				newScore.setStudentId(id.get(i));
				br = new BufferedReader(new FileReader("data/student/"
						+ id.get(i) + ".txt"));
				line = br.readLine();
				newScore.setCourseNum(Integer.parseInt(line));

				ArrayList<String> courseList = new ArrayList<String>();
				ArrayList<Integer> score = new ArrayList<Integer>();

				while ((line = br.readLine()) != null) {
					String[] split = line.split(" ");
					courseList.add(split[0]);
					score.add(Integer.parseInt(split[1]));
				}

				newScore.setCourseList(courseList);
				newScore.setScore(score);
				studentScoreList.add(newScore);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return studentScoreList;
	}

	public ArrayList<CourseScore> initialCourseScoreList() {
		ArrayList<CourseScore> courseScoreList = new ArrayList<CourseScore>();
		ArrayList<String> id = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader("data/course.txt"));
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				id.add(split[0]);
			}

			for (int i = 0; i < id.size(); i++) {
				CourseScore newScore = new CourseScore();
				newScore.setCourseId(id.get(i));
				br = new BufferedReader(new FileReader("data/course/"
						+ id.get(i) + ".txt"));
				line = br.readLine();
				newScore.setStudentNum(Integer.parseInt(line));

				ArrayList<String> studentList = new ArrayList<String>();
				ArrayList<Integer> score = new ArrayList<Integer>();

				while ((line = br.readLine()) != null) {
					String[] split = line.split(" ");
					studentList.add(split[0]);
					score.add(Integer.parseInt(split[1]));
				}

				newScore.setStudentList(studentList);
				newScore.setScore(score);
				courseScoreList.add(newScore);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return courseScoreList;
	}

	public String getString() {
		String str = scanner.next();
		return str;
	}
	
	public String getLine(){
		String str = scanner.nextLine();
		return str;
	}

	public int getInt() {
		int num = scanner.nextInt();
		return num;
	}
}
