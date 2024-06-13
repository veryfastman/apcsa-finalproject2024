package FinalProject2024;

import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
@Setter
public class Student1 implements Comparable<Student1> {
	private @NonNull String lastName;
	private @NonNull String firstName;
	private @NonNull String id;
	private @NonNull String grade;
	
	public Student1(String[] fields) {
		this.lastName = fields[0];
		this.firstName = fields[1];
		this.id = fields[2];
		this.grade = fields[3];
	}

	public String[] getStudentInfo() {
		return new String[]{ this.lastName, this.firstName, this.id, this.grade };
	}
	
	@Override
	public int compareTo(Student1 other) {
		return Comparator.comparing(Student1::getLastName)
	              .thenComparing(Student1::getFirstName)
	              .thenComparing(Student1::getId)
	              .thenComparing(Student1::getGrade)
	              .compare(this, other);
	}
}