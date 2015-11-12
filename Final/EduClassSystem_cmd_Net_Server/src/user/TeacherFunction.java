package user;

import net.Server;
import course.CourseList;
import course.ScoreList;

public interface TeacherFunction {

	abstract void publish(CourseList courseList,ScoreList scoreList,Server server);

	abstract void showCourse(CourseList courseList,Server server);

	abstract void updateCourse(CourseList courseList,Server server);

	abstract void showStudent(ScoreList scoreList,Server server);

	abstract void recordScore(ScoreList scoreList,Server server);

}
