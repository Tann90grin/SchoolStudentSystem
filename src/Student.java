import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student {
	private static int id;
	private static String name;
	private static String gender;
	private static int birthday;
	private static String city;
	private static String profession;
	private static int grade;
	private static String note;
	
	public static int getID() {
		return id;
	}
	public static void setID(int sqlid) {
		id = sqlid;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String sqlname) {
		name = sqlname;
	}
	public static String getGender() {
		return gender;
	}
	public static void setGender(String sqlgender) {
		gender = sqlgender;
	}
	public static int getBirthday() {
		return birthday;
	}
	public static void setBirthday(int sqlbirthday) {
		birthday = sqlbirthday;
	}
	public static String getCity() {
		return city;
	}
	public static void setCity(String sqlcity) {
		city = sqlcity;
	}
	public static String getProfession() {
		return profession;
	}
	public static void setProfession(String sqlprofession) {
		profession = sqlprofession;
	}
	public static int getGrade() {
		return grade;
	}
	public static void setGrade(int sqlgrade) {
		grade = sqlgrade;
	}
	public static String getNote() {
		return note;
	}
	public static void setNote(String sqlnote) {
		note = sqlnote;
	}
}
