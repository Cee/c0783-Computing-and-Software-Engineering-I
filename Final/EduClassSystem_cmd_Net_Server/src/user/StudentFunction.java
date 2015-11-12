package user;

import net.Server;
import course.CourseList;
import course.ScoreList;

public interface StudentFunction {

	abstract void showMyCourseList(ScoreList scoreList, CourseList courseList,Server server);

	abstract void selectCourse(ScoreList scoreList, CourseList courseList,Server server);

	abstract void quitCourse(ScoreList scoreList, CourseList courseList,Server server);

	abstract void showScore(ScoreList scoreList, CourseList courseList,Server server);

}
