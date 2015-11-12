package user;

import java.io.Serializable;
import java.util.ArrayList;

import net.Server;
import course.Course;
import course.CourseList;
import course.CourseScore;
import course.ScoreList;

public class Teacher extends User implements TeacherFunction,Serializable {

	private static final long serialVersionUID = 1L;

	public void publish(CourseList courseList,ScoreList scoreList,Server server) {
		Course newCourse = new Course();
		server.sendMessage("CourseId:");
		newCourse.setCourseId(server.receiveMessage());
		if (newCourse.getCourseId().equals("")) {
			server.sendMessage("Wrong Input");
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
				server.sendMessage("The CourseId has been used.");
			} else {
				server.sendMessage("CourseName:");
				newCourse.setCourseName(server.receiveMessage());
				server.sendMessage("Compulsory(true/false):");
				newCourse.setCompulsory(server.receiveMessage().equals("true") ? true
						: false);
				server.sendMessage("Period:");
				newCourse.setPeriod(Integer.parseInt(server.receiveMessage()));
				server.sendMessage("Score:");
				newCourse.setScore(Integer.parseInt(server.receiveMessage()));
				
				User newTeacher = new Teacher();
				newTeacher.setId(this.getId());
				newTeacher.setType("teacher");
				newTeacher.setPassword(null);
				newCourse.setTeacherUser(newTeacher);

				server.sendMessage("Time:");
				newCourse.setTime(server.receiveMessage());
				server.sendMessage("Classroom:");
				newCourse.setClassroom(server.receiveMessage());

				server.sendMessage("Assistants number:");
				ArrayList<User> list = new ArrayList<User>();
				int count = Integer.parseInt(server.receiveMessage());
				while (count > 0) {
					server.sendMessage("Id:");
					Student newStudent = new Student();
					newStudent.setId(server.receiveMessage());
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
				
				server.sendMessage("Succeed!");
			}
		}
	}

	public void showCourse(CourseList courseList,Server server) {
		Course newCourse = new Course();
		server.sendMessage("CourseId:");
		newCourse.setCourseId(server.receiveMessage());
		if (newCourse.getCourseId().equals("")) {
			server.sendMessage("Wrong Input");
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
				server.sendMessage("Not Exist");
			} else {
				newCourse = courseList.getCourseList().get(i);
				server.sendMessage("CourseName:" + newCourse.getCourseName());
				server.sendMessage("Compulsory:" + newCourse.isCompulsory());
				server.sendMessage("Period:" + newCourse.getPeriod());
				server.sendMessage("Score:" + newCourse.getScore());
				server.sendMessage("Teacher Id:"
						+ newCourse.getTeacherUser().getName());
				server.sendMessage("Time:" + newCourse.getTime());
				server.sendMessage("Classroom:" + newCourse.getClassroom());
				server.sendMessage("Assistants:");
				int count = newCourse.getAssistStudentList().size();
				while (count > 0) {
					server.sendMessage(newCourse.getAssistStudentList()
							.get(count - 1).getName()
							+ " ");
					count--;
				}

			}

		}
	}

	public void updateCourse(CourseList courseList,Server server) {
		Course newCourse = new Course();
		server.sendMessage("CourseId:");
		newCourse.setCourseId(server.receiveMessage());
		if (newCourse.getCourseId().equals("")) {
			server.sendMessage("Wrong Input");
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
				server.sendMessage("Not Exist");
			} else {
				newCourse = courseList.getCourseList().get(i);
				if (newCourse.getTeacherUser().getId().equals(this.getId())) {

					server.sendMessage("CourseName:" + newCourse.getCourseName());
					server.sendMessage("Compulsory:" + newCourse.isCompulsory());
					server.sendMessage("Period:" + newCourse.getPeriod());
					server.sendMessage("Score:" + newCourse.getScore());
					server.sendMessage("Teacher Id:"
							+ newCourse.getTeacherUser().getName());
					server.sendMessage("Time:" + newCourse.getTime());
					server.sendMessage("Classroom:" + newCourse.getClassroom());
					server.sendMessage("Assistants:");
					int count = newCourse.getAssistStudentList().size();
					while (count > 0) {
						server.sendMessage(newCourse.getAssistStudentList()
								.get(count - 1).getName()
								+ " ");
						count--;
					}
					server.sendMessage("Update");
					server.sendMessage("CourseName:");
					newCourse.setCourseName(server.receiveMessage());
					server.sendMessage("Compulsory(true/false):");
					newCourse
							.setCompulsory(server.receiveMessage().equals("true") ? true
									: false);
					server.sendMessage("Period:");
					newCourse.setPeriod(Integer.parseInt(server.receiveMessage()));
					server.sendMessage("Score:");
					newCourse.setScore(Integer.parseInt(server.receiveMessage()));

					User newTeacher = new Teacher();
					newTeacher.setId(this.getId());
					newTeacher.setType("teacher");
					newTeacher.setPassword(null);
					newCourse.setTeacherUser(newTeacher);

					server.sendMessage("Time:");
					newCourse.setTime(server.receiveMessage());
					server.sendMessage("Classroom:");
					newCourse.setClassroom(server.receiveMessage());

					server.sendMessage("Assistants number:");
					ArrayList<User> list = new ArrayList<User>();
					count = Integer.parseInt(server.receiveMessage());
					while (count > 0) {
						server.sendMessage("Id:");
						Student newStudent = new Student();
						newStudent.setId(server.receiveMessage());
						newStudent.setType("student");
						newStudent.setPassword(null);
						list.add(newStudent);
						count--;
					}
					newCourse.setAssistStudentList(list);
					courseList.getCourseList().remove(i);
					courseList.getCourseList().add(newCourse);
				} else {
					server.sendMessage("Not your class");
				}
			}
		}
	}

	public void showStudent(ScoreList scoreList,Server server) {
		Course newCourse = new Course();
		server.sendMessage("CourseId:");
		newCourse.setCourseId(server.receiveMessage());
		if (newCourse.getCourseId().equals("")) {
			server.sendMessage("Wrong Input");
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
				server.sendMessage("Student:");
				for(int j = 0 ; j<scoreList.getCourseScoreList().get(i).getStudentNum();j++){
					server.sendMessage(scoreList.getCourseScoreList().get(i).getStudentList().get(j));
				}
			} else {
				server.sendMessage("Not Exist");
			}
			
		}
	}

	public void recordScore(ScoreList scoreList,Server server) {
		Course newCourse = new Course();
		server.sendMessage("CourseId:");
		newCourse.setCourseId(server.receiveMessage());
		if (newCourse.getCourseId().equals("")) {
			server.sendMessage("Wrong Input");
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
				server.sendMessage("Student:");				
				for(int j = 0 ; j<scoreList.getCourseScoreList().get(i).getStudentNum();j++){
					server.sendMessage(scoreList.getCourseScoreList().get(i).getStudentList().get(j)+":");
					score.add(Integer.parseInt(server.receiveMessage()));
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
				server.sendMessage("Not Exist");
			}
			
		}
	}

}
