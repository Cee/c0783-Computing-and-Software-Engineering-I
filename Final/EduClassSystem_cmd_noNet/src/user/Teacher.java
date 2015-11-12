package user;

import io.ReadData;
import io.WriteData;

import java.io.Serializable;
import java.util.ArrayList;

import course.Course;
import course.CourseList;
import course.CourseScore;
import course.ScoreList;

public class Teacher extends User implements TeacherFunction,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadData input = new ReadData();
	private WriteData output = new WriteData();

	public void publish(CourseList courseList,ScoreList scoreList) {
		Course newCourse = new Course();
		output.print("CourseId:");
		newCourse.setCourseId(input.getLine());
		if (newCourse.getCourseId().equals("")) {
			output.println("Wrong Input");
		} else {
			boolean isExist = false;
			for (int i = 0; i < courseList.getCourseList().size(); i++) {
				if (newCourse.getCourseId().equals(
						courseList.getCourseList().get(i).getCourseId())) {
					isExist = true;
					break;
				}
			}
			if (isExist) {
				output.println("The CourseId has been used.");
			} else {
				output.print("CourseName:");
				newCourse.setCourseName(input.getLine());
				output.print("Compulsory(true/false):");
				newCourse.setCompulsory(input.getLine().equals("true") ? true
						: false);
				output.print("Period:");
				newCourse.setPeriod(Integer.parseInt(input.getLine()));
				output.print("Score:");
				newCourse.setScore(Integer.parseInt(input.getLine()));
				output.print("Teacher Id:");
				
				User newTeacher = new Teacher();
				newTeacher.setId(this.getId());
				newTeacher.setType("teacher");
				newTeacher.setPassword(null);
				newCourse.setTeacherUser(newTeacher);

				output.print("Time:");
				newCourse.setTime(input.getLine());
				output.print("Classroom:");
				newCourse.setClassroom(input.getLine());

				output.print("Assistants number:");
				ArrayList<User> list = new ArrayList<User>();
				int count = Integer.parseInt(input.getLine());
				while (count > 0) {
					output.print("Id:");
					Student newStudent = new Student();
					newStudent.setId(input.getLine());
					newStudent.setType("student");
					newStudent.setPassword(null);
					list.add(newStudent);
					count--;
				}
				newCourse.setAssistStudentList(list);
				courseList.getCourseList().add(newCourse);
				
				CourseScore courseScore = new CourseScore();
				courseScore.setCourseId(newCourse.getCourseId());
				courseScore.setStudentList(new ArrayList<String>());
				courseScore.setScore(new ArrayList<Integer>());
				courseScore.setStudentNum(0);
				scoreList.getCourseScoreList().add(courseScore);
				
				output.println("Succeed!");
			}
		}
	}

	public void showCourse(CourseList courseList) {
		Course newCourse = new Course();
		output.print("CourseId:");
		newCourse.setCourseId(input.getLine());
		if (newCourse.getCourseId().equals("")) {
			output.println("Wrong Input");
		} else {
			boolean isExist = false;
			int i;
			for (i = 0; i < courseList.getCourseList().size(); i++) {
				if (newCourse.getCourseId().equals(
						courseList.getCourseList().get(i).getCourseId())) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				output.println("Not Exist");
			} else {
				newCourse = courseList.getCourseList().get(i);
				output.println("CourseName:" + newCourse.getCourseName());
				output.println("Compulsory:" + newCourse.isCompulsory());
				output.println("Period:" + newCourse.getPeriod());
				output.println("Score:" + newCourse.getScore());
				output.println("Teacher Id:"
						+ newCourse.getTeacherUser().getName());
				output.println("Time:" + newCourse.getTime());
				output.println("Classroom:" + newCourse.getClassroom());
				output.print("Assistants:");
				int count = newCourse.getAssistStudentList().size();
				while (count > 0) {
					output.print(newCourse.getAssistStudentList()
							.get(count - 1).getName()
							+ " ");
					count--;
				}
				output.println(" ");

			}

		}
	}

	public void updateCourse(CourseList courseList) {
		Course newCourse = new Course();
		output.print("CourseId:");
		newCourse.setCourseId(input.getLine());
		if (newCourse.getCourseId().equals("")) {
			output.println("Wrong Input");
		} else {
			boolean isExist = false;
			int i;
			for (i = 0; i < courseList.getCourseList().size(); i++) {
				if (newCourse.getCourseId().equals(
						courseList.getCourseList().get(i).getCourseId())) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				output.println("Not Exist");
			} else {
				newCourse = courseList.getCourseList().get(i);
				if (newCourse.getTeacherUser().getId().equals(this.getId())) {

					output.println("CourseName:" + newCourse.getCourseName());
					output.println("Compulsory:" + newCourse.isCompulsory());
					output.println("Period:" + newCourse.getPeriod());
					output.println("Score:" + newCourse.getScore());
					output.println("Teacher Id:"
							+ newCourse.getTeacherUser().getName());
					output.println("Time:" + newCourse.getTime());
					output.println("Classroom:" + newCourse.getClassroom());
					output.print("Assistants:");
					int count = newCourse.getAssistStudentList().size();
					while (count > 0) {
						output.print(newCourse.getAssistStudentList()
								.get(count - 1).getName()
								+ " ");
						count--;
					}
					output.println(" ");
					output.println("Update");
					output.print("CourseName:");
					newCourse.setCourseName(input.getLine());
					output.print("Compulsory(true/false):");
					newCourse
							.setCompulsory(input.getLine().equals("true") ? true
									: false);
					output.print("Period:");
					newCourse.setPeriod(Integer.parseInt(input.getLine()));
					output.print("Score:");
					newCourse.setScore(Integer.parseInt(input.getLine()));

					User newTeacher = new Teacher();
					newTeacher.setId(this.getId());
					newTeacher.setType("teacher");
					newTeacher.setPassword(null);
					newCourse.setTeacherUser(newTeacher);

					output.print("Time:");
					newCourse.setTime(input.getLine());
					output.print("Classroom:");
					newCourse.setClassroom(input.getLine());

					output.print("Assistants number:");
					ArrayList<User> list = new ArrayList<User>();
					count = Integer.parseInt(input.getLine());
					while (count > 0) {
						output.print("Id:");
						Student newStudent = new Student();
						newStudent.setId(input.getLine());
						newStudent.setType("student");
						newStudent.setPassword(null);
						list.add(newStudent);
						count--;
					}
					newCourse.setAssistStudentList(list);
					courseList.getCourseList().remove(i);
					courseList.getCourseList().add(newCourse);
				} else {
					output.println("Not your class");
				}
			}
		}
	}

	public void showStudent(ScoreList scoreList) {
		Course newCourse = new Course();
		output.print("CourseId:");
		newCourse.setCourseId(input.getLine());
		if (newCourse.getCourseId().equals("")) {
			output.println("Wrong Input");
		} else {
			boolean isExist = false;
			int i;
			for (i = 0; i < scoreList.getCourseScoreList().size(); i++) {
				if (newCourse.getCourseId().equals(
						scoreList.getCourseScoreList().get(i).getCourseId())) {
					isExist = true;
					break;
				}
			}
			if (isExist) {
				output.println("Student:");
				for(int j = 0 ; j<scoreList.getCourseScoreList().get(i).getStudentNum();j++){
					output.println(scoreList.getCourseScoreList().get(i).getStudentList().get(j));
				}
			} else {
				output.println("Not Exist");
			}
			
		}
	}

	public void recordScore(ScoreList scoreList) {
		Course newCourse = new Course();
		output.print("CourseId:");
		newCourse.setCourseId(input.getLine());
		if (newCourse.getCourseId().equals("")) {
			output.println("Wrong Input");
		} else {
			boolean isExist = false;
			int i;
			for (i = 0; i < scoreList.getCourseScoreList().size(); i++) {
				if (newCourse.getCourseId().equals(
						scoreList.getCourseScoreList().get(i).getCourseId())) {
					isExist = true;
					break;
				}
			}
			if (isExist) {
				ArrayList<Integer> score = new ArrayList<Integer>();
				output.println("Student:");				
				for(int j = 0 ; j<scoreList.getCourseScoreList().get(i).getStudentNum();j++){
					output.print(scoreList.getCourseScoreList().get(i).getStudentList().get(j)+":");
					score.add(input.getInt());
				}
				scoreList.getCourseScoreList().get(i).setScore(score);
				for (int j = 0; j < scoreList.getCourseScoreList().get(i).getStudentNum();j++){
					String idString = scoreList.getCourseScoreList().get(i).getStudentList().get(j);
					for (int count = 0; count < scoreList.getStudentScoreList().size(); count++){
						if (scoreList.getStudentScoreList().get(count).getStudentId().equals(idString)){
							for (int k = 0; k < scoreList.getStudentScoreList().get(count).getCourseNum(); k++){
								if (scoreList.getStudentScoreList().get(count).getCourseList().get(k).equals(newCourse.getCourseId())){
									scoreList.getStudentScoreList().get(count).getCourseList().remove(k);
									scoreList.getStudentScoreList().get(count).getScore().remove(k);
									scoreList.getStudentScoreList().get(count).getCourseList().add(newCourse.getCourseId());
									scoreList.getStudentScoreList().get(count).getScore().add(scoreList.getCourseScoreList().get(i).getScore().get(j));
								}
							}
						}
					}
				}
				
			} else {
				output.println("Not Exist");
			}
			
		}
	}

}
