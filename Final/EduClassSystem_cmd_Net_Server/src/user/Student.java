package user;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import net.Server;
import course.Course;
import course.CourseList;
import course.ScoreList;

public class Student extends User implements StudentFunction,Serializable {

	private static final long serialVersionUID = 1L;

	public void showMyCourseList(ScoreList scoreList, CourseList courseList,Server server) {
		int i;
		for (i = 0; i < scoreList.getStudentScoreList().size(); i++) {
			if (scoreList.getStudentScoreList().get(i).getStudentId()
					.equals(this.getId())) {
				break;
			}
		}

		ArrayList<Course> list = new ArrayList<Course>();
		for (int j = 0; j < scoreList.getStudentScoreList().get(i)
				.getCourseNum(); j++) {
			for (int k = 0; k < courseList.getCourseList().size(); k++) {
				if (courseList
						.getCourseList()
						.get(k)
						.getCourseId()
						.equals(scoreList.getStudentScoreList().get(i)
								.getCourseList().get(j))) {
					list.add(courseList.getCourseList().get(k));
					break;
				}
			}
		}

		showCourseList(list,server);

	}

	private void showCourseList(ArrayList<Course> list,Server server) {
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

	public void selectCourse(ScoreList scoreList, CourseList courseList,Server server) {
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
			if (isExist) {
				int j;
				for (j = 0; j < scoreList.getStudentScoreList().size(); j++) {
					if (scoreList.getStudentScoreList().get(j).getStudentId()
							.equals(this.getId())) {
						break;
					}
				}
				scoreList.getStudentScoreList().get(j).setCourseNum(scoreList.getStudentScoreList().get(j).getCourseNum() + 1);
				scoreList.getStudentScoreList().get(j).getCourseList().add(courseList.getCourseList().get(i).getCourseId());
				scoreList.getStudentScoreList().get(j).getScore().add(0);
				
				
				for (j = 0; j < scoreList.getCourseScoreList().size(); j++) {
					if (scoreList.getCourseScoreList().get(j).getCourseId()
							.equals(newCourse.getCourseId())) {
						break;
					}
				}
				scoreList.getCourseScoreList().get(j).setStudentNum(scoreList.getCourseScoreList().get(j).getStudentNum() + 1);
				scoreList.getCourseScoreList().get(j).getStudentList().add(this.getId());
				scoreList.getCourseScoreList().get(j).getScore().add(0);

				server.sendMessage("Succeed!");
			} else {
				server.sendMessage("Not Exist");
			}
		}
	}

	public void quitCourse(ScoreList scoreList, CourseList courseList,Server server) {
		Course newCourse = new Course();
		server.sendMessage("CourseId:");
		newCourse.setCourseId(server.receiveMessage());
		if (newCourse.getCourseId().equals("")) {
			server.sendMessage("Wrong Input");
		} else {
			
			boolean isExist = false;
			
			int i,j;
			for (i = 0; i < scoreList.getStudentScoreList().size(); i++){
				if (scoreList.getStudentScoreList().get(i).getStudentId().equals(this.getId())){
					break;
				}
			}
			
			for (j = 0; j < scoreList.getCourseScoreList().size(); j++){
				if (scoreList.getCourseScoreList().get(j).getCourseId().equals(newCourse.getCourseId())){
					break;
				}
			}
			
			if ((i < scoreList.getStudentScoreList().size()) && (j < scoreList.getCourseScoreList().size())){
				isExist = true;
			}		
			
			if (isExist){
				int count;
				for (count = 0; count < scoreList.getCourseScoreList().get(j).getStudentNum(); count++){
					if (scoreList.getCourseScoreList().get(j).getStudentList().get(count).equals(this.getId())){
						scoreList.getCourseScoreList().get(j).getStudentList().remove(count);
						scoreList.getCourseScoreList().get(j).getScore().remove(count);
						scoreList.getCourseScoreList().get(j).setStudentNum(scoreList.getCourseScoreList().get(j).getStudentNum() - 1);
						break;
					}
					
				}
				for (count = 0; count < scoreList.getStudentScoreList().get(i).getCourseNum(); count++){
					if (scoreList.getStudentScoreList().get(i).getCourseList().get(count).equals(newCourse.getCourseId())){
						scoreList.getStudentScoreList().get(i).getCourseList().remove(count);
						scoreList.getStudentScoreList().get(i).getScore().remove(count);
						scoreList.getStudentScoreList().get(i).setCourseNum(scoreList.getStudentScoreList().get(i).getCourseNum() - 1);
						break;
					}
				}
				server.sendMessage("Succeed!");				
			}else{
				server.sendMessage("Not Exist");
			}
			
		}

	}

	public void showScore(ScoreList scoreList, CourseList courseList,Server server) {
		int i;
		for (i = 0; i < scoreList.getStudentScoreList().size(); i++) {
			if (scoreList.getStudentScoreList().get(i).getStudentId()
					.equals(this.getId())) {
				break;
			}
		}

		for (int j = 0; j < scoreList.getStudentScoreList().get(i)
				.getCourseNum(); j++) {
			String courseId = scoreList.getStudentScoreList().get(i)
					.getCourseList().get(j);
			for (int k = 0; k < courseList.getCourseList().size(); k++) {
				if (courseList.getCourseList().get(k).getCourseId()
						.equals(courseId)) {
					server.sendMessage(courseList.getCourseList().get(k)
							.getCourseName()
							+ ":");
					server.sendMessage(String.valueOf(scoreList.getStudentScoreList().get(i)
							.getScore().get(j)));
					break;
				}
			}

		}

	}

}
