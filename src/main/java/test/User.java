package test;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
	
	@NonNull
	private String name;
	
	private int age;

}
