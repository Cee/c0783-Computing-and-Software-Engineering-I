package user;

import net.Server;
import course.CourseList;

public interface AdminFunction {

	abstract void show(UserList userList, CourseList courseList, Server server);

	abstract void delete(UserList userList, CourseList courseLis, Server server);

}
