package user;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import course.Course;
import course.CourseList;
import course.ScoreList;
import io.ReadData;
import io.WriteData;

public class Student extends User implements StudentFunction,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadData input = new ReadData();
	private WriteData output = new WriteData();

	public void showMyCourseList(ScoreList scoreList, CourseList courseList) {
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

		showCourseList(list);

	}

	private void showCourseList(ArrayList<Course> list) {
		output.println("No./Id./Name/Compulsory/Period/Score/Teacher/Time/Classroom/Assistants");
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

	public void selectCourse(ScoreList scoreList, CourseList courseList) {
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
			if (isExist) {
				int j;
				for (j = 0; j < scoreList.getStudentScoreList().size(); j++) {
					if (scoreList.getStudentScoreList().get(j).getStudentId()
							.equals(this.getId())) {
						break;
					}
				}
				scoreList
						.getStudentScoreList()
						.get(j)
						.setCourseNum(
								scoreList.getStudentScoreList().get(j)
										.getCourseNum() + 1);
				scoreList.getStudentScoreList().get(j).getCourseList()
						.add(courseList.getCourseList().get(i).getCourseId());
				scoreList.getStudentScoreList().get(j).getScore().add(0);
				output.println("Succeed!");
			} else {
				output.println("Not Exist");
			}
		}
	}

	public void quitCourse(ScoreList scoreList, CourseList courseList) {
		Course newCourse = new Course();
		output.print("CourseId:");
		newCourse.setCourseId(input.getLine());
		if (newCourse.getCourseId().equals("")) {
			output.println("Wrong Input");
		} else {
			boolean isExist = false;
			int i, count;
			for (count = 0; count < scoreList.getStudentScoreList().size(); count++) {
				if (scoreList.getStudentScoreList().get(count).getStudentId()
						.equals(this.getId())) {
					break;
				}
			}
			for (i = 0; i < scoreList.getStudentScoreList().get(i)
					.getCourseList().size(); i++) {
				if (scoreList.getStudentScoreList().get(count).getCourseList()
						.get(i).equals(newCourse.getCourseId())) {
					isExist = true;
					break;
				}
			}
			if (isExist) {
				scoreList.getStudentScoreList().get(count).getCourseList()
						.remove(i);
				scoreList.getStudentScoreList().get(count).getScore().remove(i);
				output.println("Succeed!");
			} else {
				output.println("Not Exist");
			}
		}

	}

	public void showScore(ScoreList scoreList, CourseList courseList) {
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
					output.print(courseList.getCourseList().get(k)
							.getCourseName()
							+ ":");
					output.println(scoreList.getStudentScoreList().get(i)
							.getScore().get(j));
					break;
				}
			}

		}

	}

}
