package user;

import course.CourseList;
import course.ScoreList;

public interface StudentFunction {

	abstract void selectCourse(String string,ScoreList scoreList, CourseList courseList);

	abstract void quitCourse(String string,ScoreList scoreList, CourseList courseList);

}