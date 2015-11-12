package user;

import course.CourseList;

public interface AdminFunction {

	abstract void show(UserList userList, CourseList courseList);

	abstract void delete(UserList userList, CourseList courseList);

}
