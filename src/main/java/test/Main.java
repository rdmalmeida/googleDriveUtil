package test;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

	public static void main(String[] args) {
		User u = new User("rapha");
		u.setAge(39);
		
		log.error(u.toString());

	}

}
