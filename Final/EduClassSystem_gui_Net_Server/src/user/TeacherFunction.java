package user;

import course.CourseList;
import course.ScoreList;

public interface TeacherFunction {

	abstract void publish(String message,CourseList courseList,ScoreList scoreList);

	abstract void updateCourse(String message,CourseList courseList);

	abstract void recordScore(String message,ScoreList scoreList);

}
