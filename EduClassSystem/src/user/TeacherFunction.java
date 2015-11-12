package user;

import course.CourseList;
import course.ScoreList;

public interface TeacherFunction {

	abstract void publish(CourseList courseList);

	abstract void showCourse(CourseList courseList);

	abstract void updateCourse(CourseList courseList);

	abstract void showStudent(ScoreList scoreList);

	abstract void recordScore(ScoreList scoreList);

}
