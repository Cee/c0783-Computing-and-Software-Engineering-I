package user;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import course.Course;
import course.CourseList;
import course.CourseScore;
import course.ScoreList;

public class Teacher extends User implements TeacherFunction,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void publish(String message,CourseList courseList,ScoreList scoreList) {
		
		String[] split = message.split("/");
		
		Course newCourse = new Course();		
		newCourse.setCourseId(split[0]);
		if (newCourse.getCourseId().equals("")) {
			JOptionPane.showMessageDialog(null,"Wrong Input");
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
				JOptionPane.showMessageDialog(null,"The course ID has been used.");
			} else {
				newCourse.setCourseName(split[1]);
				newCourse.setCompulsory(split[2].equals("true") ? true
						: false);
				newCourse.setPeriod(Integer.parseInt(split[3]));
				newCourse.setScore(Integer.parseInt(split[4]));
				
				User newTeacher = new Teacher();
				newTeacher.setId(this.getId());
				newTeacher.setType("teacher");
				newTeacher.setPassword(null);
				newCourse.setTeacherUser(newTeacher);

				newCourse.setTime(split[5]);
				newCourse.setClassroom(split[6]);

				ArrayList<User> list = new ArrayList<User>();
				int count = split.length - 7;
				while (count > 0) {
					Student newStudent = new Student();
					newStudent.setId(split[6 + count]);
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
				
				JOptionPane.showMessageDialog(null,"Succeed");
			}
		}
	}


	public void updateCourse(String message,CourseList courseList) {
		
		String[] split = message.split("/");
		Course newCourse = new Course();
		newCourse.setCourseId(split[0]);
		if (newCourse.getCourseId().equals("")) {
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
			} else {
				newCourse = courseList.getCourseList().get(i);
				if (newCourse.getTeacherUser().getId().equals(this.getId())) {

					newCourse.setCourseName(split[1]);
					newCourse.setCompulsory(split[2].equals("true") ? true
									: false);
					newCourse.setPeriod(Integer.parseInt(split[3]));
					newCourse.setScore(Integer.parseInt(split[4]));

					User newTeacher = new Teacher();
					newTeacher.setId(this.getId());
					newTeacher.setType("teacher");
					newTeacher.setPassword(null);
					newCourse.setTeacherUser(newTeacher);

					newCourse.setTime(split[5]);
					newCourse.setClassroom(split[6]);
					
					ArrayList<User> list = new ArrayList<User>();
					int count = split.length - 7;
					while (count > 0) {
						Student newStudent = new Student();
						newStudent.setId(split[6 + count]);
						newStudent.setType("student");
						newStudent.setPassword(null);
						list.add(newStudent);
						count--;
					}
					newCourse.setAssistStudentList(list);
					courseList.getCourseList().remove(i);
					courseList.getCourseList().add(newCourse);
					
					JOptionPane.showMessageDialog(null,"Succeed");
				} else {
					JOptionPane.showMessageDialog(null,"Not your Class");
				}
			}
		}
	}

	public void recordScore(String message, ScoreList scoreList) {
		Course newCourse = new Course();
		newCourse.setCourseId(message);
		if (newCourse.getCourseId().equals("")) {
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
				for(int j = 0 ; j<scoreList.getCourseScoreList().get(i).getStudentNum();j++){
					String scoreString = JOptionPane.showInputDialog(scoreList.getCourseScoreList().get(i).getStudentList().get(j)+":");
					score.add(Integer.parseInt(scoreString));
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
				JOptionPane.showMessageDialog(null,"Succeed");
			} else {
				JOptionPane.showMessageDialog(null,"Not Exist");
			}
			
		}
	}

}
