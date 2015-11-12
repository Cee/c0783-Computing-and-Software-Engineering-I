package console;

import user.*;

class Instructions {
	
	public static void go(String group){
		switch (group){
		case("admin"):
			Admin.go();
			break;
		case("teacher"):
			Teacher.go();
			break;
		case("student"):
			Student.go();
			break;
		}
	}

}
