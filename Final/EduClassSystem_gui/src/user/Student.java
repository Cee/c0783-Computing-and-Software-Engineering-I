package user;

import java.io.Serializable;

import javax.swing.JOptionPane;

import course.Course;
import course.CourseList;
import course.ScoreList;

public class Student extends User implements StudentFunction,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void selectCourse(String message,ScoreList scoreList, CourseList courseList) {
		
		Course newCourse = new Course();
		newCourse.setCourseId(message);
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

				JOptionPane.showMessageDialog(null,"Succeed");
			} else {
				JOptionPane.showMessageDialog(null,"Not Exist");
			}
		}
	}

	public void quitCourse(String message,ScoreList scoreList, CourseList courseList) {
		Course newCourse = new Course();
		newCourse.setCourseId(message);
		if (newCourse.getCourseId().equals("")) {
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
				JOptionPane.showMessageDialog(null,"Succeed");
			} else {
				JOptionPane.showMessageDialog(null,"Not Exist");
			}
			
		}

	}
	
}
