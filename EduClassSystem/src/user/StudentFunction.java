package user;

import course.CourseList;
import course.ScoreList;

public interface StudentFunction {

	abstract void showMyCourseList(ScoreList scoreList, CourseList courseList);

	abstract void selectCourse(ScoreList scoreList, CourseList courseList);

	abstract void quitCourse(ScoreList scoreList, CourseList courseList);

	abstract void showScore(ScoreList scoreList, CourseList courseList);

}
