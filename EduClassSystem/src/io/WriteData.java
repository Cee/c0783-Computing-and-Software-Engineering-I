package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

import java.io.FileWriter;
import java.io.IOException;

import course.Course;
import course.CourseList;
import course.ScoreList;

import user.UserList;

public class WriteData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void println(String s) {
		System.out.println(s);
	}

	public void print(String s) {
		System.out.print(s);
	}

	public void println(int i) {
		System.out.println(i);
	}

	public void print(int i) {
		System.out.print(i);
	}

	public void save(UserList userList, CourseList courseList,
			ScoreList scoreList) {
		try {
			File file;
			FileWriter fw;

			file = new File("data/admin.txt");
			fw = new FileWriter(file);
			fw.write("");
			fw.close();

			fw = new FileWriter("data/admin.txt");
			for (int i = 0; i < userList.getAdminList().size(); i++) {
				fw.write(userList.getAdminList().get(i).getId() + " "
						+ userList.getAdminList().get(i).getPassword() + "\n");
			}
			fw.close();

			file = new File("data/teacher.txt");
			fw = new FileWriter(file);
			fw.write("");
			fw.close();

			fw = new FileWriter("data/teacher.txt");
			for (int i = 0; i < userList.getTeacherList().size(); i++) {
				fw.write(userList.getTeacherList().get(i).getId() + " "
						+ userList.getTeacherList().get(i).getName() + " "
						+ userList.getAdminList().get(i).getPassword() + "\n");
			}
			fw.close();

			file = new File("data/student.txt");
			fw = new FileWriter(file);
			fw.write("");
			fw.close();

			fw = new FileWriter("data/student.txt");
			for (int i = 0; i < userList.getStudentList().size(); i++) {
				fw.write(userList.getStudentList().get(i).getId() + " "
						+ userList.getStudentList().get(i).getName() + " "
						+ userList.getStudentList().get(i).getPassword() + "\n");
			}
			fw.close();

			file = new File("data/course.txt");
			fw = new FileWriter(file);
			fw.write("");
			fw.close();

			fw = new FileWriter("data/course.txt");
			for (int i = 0; i < courseList.getCourseList().size(); i++) {
				Course newCourse = courseList.getCourseList().get(i);
				fw.write(newCourse.getCourseId() + " ");
				fw.write(newCourse.getCourseName() + " ");
				fw.write((newCourse.isCompulsory()) ? "true " : "false ");
				fw.write(newCourse.getPeriod() + " ");
				fw.write(newCourse.getScore() + " ");
				fw.write(newCourse.getTeacherUser().getId() + " ");
				fw.write(newCourse.getTime() + " ");
				fw.write(newCourse.getClassroom() + " ");
				for (int j = 0; j < newCourse.getAssistStudentList().size(); j++) {
					fw.write(newCourse.getAssistStudentList().get(j).getId()
							+ " ");
				}
				fw.write("\n");

			}
			fw.close();

			for (int i = 0; i < scoreList.getStudentScoreList().size(); i++) {

				file = new File("data/student/"
						+ scoreList.getStudentScoreList().get(i).getStudentId()
						+ ".txt");
				fw = new FileWriter(file);
				fw.write("");
				fw.close();

				fw = new FileWriter("data/student/"
						+ scoreList.getStudentScoreList().get(i).getStudentId()
						+ ".txt");
				fw.write(scoreList.getStudentScoreList().get(i).getCourseNum()+"\n");
				for (int j = 0; j < scoreList.getStudentScoreList().get(i)
						.getCourseNum(); j++) {
					fw.write(scoreList.getStudentScoreList().get(i)
							.getCourseList().get(j)
							+ " ");
					fw.write(scoreList.getStudentScoreList().get(i).getScore()
							.get(j));
					fw.write("\n");
				}
				fw.close();
			}

			for (int i = 0; i < scoreList.getCourseScoreList().size(); i++) {

				file = new File("data/course/"
						+ scoreList.getCourseScoreList().get(i).getCourseId()
						+ ".txt");
				fw = new FileWriter(file);
				fw.write("");
				fw.close();

				fw = new FileWriter("data/course/"
						+ scoreList.getCourseScoreList().get(i).getCourseId()
						+ ".txt");

				for (int j = 0; j < scoreList.getCourseScoreList().get(i)
						.getStudentNum(); j++) {
					fw.write(scoreList.getCourseScoreList().get(i)
							.getStudentList().get(j)
							+ " ");
					fw.write(scoreList.getCourseScoreList().get(i).getScore()
							.get(j));
					fw.write("\n");
				}
				fw.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
