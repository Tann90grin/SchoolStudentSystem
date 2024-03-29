import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Utility {
	private static Connection con = null;
	private static Utility ut;
    //连接mysql数据库//
	public static Connection GetConnection() {
    if(con != null) {
    	return con;
    }
    else{
    	try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
   	 	con = DriverManager.getConnection(" ", " ", " ");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return con;
    	}
    }
   //检测用户是否存在//
    public static boolean userExist(String name, String password) {
    	PreparedStatement st = null; 
    	ResultSet rs = null;
    	try {
    	GetConnection();
    	st = con.prepareStatement("select * from users where Name = ? and Password = ?");
    	st.setString(1, name);
    	st.setString(2, password);
    	rs = st.executeQuery();
    	if(rs!=null) {
    		rs.last();
    		if(rs.getRow() == 1) {
    			return true;
    		}
    	}
     }catch(SQLException e) {
    	 e.printStackTrace();
     	}
     return false;
    }
    //获取用户信息//
    public static void GetUser(String a) {
    	PreparedStatement st = null; 
    	ResultSet rs = null;
    	try {
    		GetConnection();
        	st = con.prepareStatement("select * from schoolnumber where Name = ?");
        	st.setString(1, a);
        	rs = st.executeQuery();
        	while(rs.next()) {
        		Student.setID(rs.getInt("ID"));
        		Student.setName(rs.getString("Name"));
        		Student.setBirthday(rs.getString("Birthday"));
        		Student.setGender(rs.getString("Gender"));
        		Student.setCity(rs.getString("City"));
        		Student.setProfession(rs.getString("Profession"));
        		Student.setGrade(rs.getInt("Grade"));
        		Student.setNote(rs.getString("Note"));
             	}
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
    }
    public static void UpdateUser(String a) {
    	PreparedStatement st = null; 
    	try {
    		GetConnection();
        	st = con.prepareStatement("update schoolnumber set ID = ?, Name = ?, Birthday = ?, Gender = ?, City = ?, Profession = ?, Note = ?, Grade = ? where Name = ? ");
        	st.setInt(1, Student.getID());
        	st.setString(2, Student.getName());
        	st.setString(3, Student.getBirthday());
        	st.setString(4, Student.getGender());
        	st.setString(5, Student.getCity());
        	st.setString(6, Student.getProfession());
        	st.setString(7, Student.getNote());
        	st.setInt(8, Student.getGrade());
        	st.setString(9, a);
        	st.executeUpdate();
        	System.out.println(st.executeUpdate());
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
    }
    public static void AddUser() {
    	PreparedStatement st = null; 
    	try {
    		GetConnection();
        	st = con.prepareStatement("insert into schoolnumber values(?,?,?,?,?,?,?,?)");
        	st.setInt(1, Student.getID());
        	st.setString(2, Student.getName());
        	st.setString(3, Student.getBirthday());
        	st.setString(4, Student.getGender());
        	st.setString(5, Student.getCity());
        	st.setString(6, Student.getProfession());
        	st.setString(7, Student.getNote());
        	st.setInt(8, Student.getGrade());
        	st.executeUpdate();
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
    }
   //搜索姓名//
    public static boolean searchUser(String name) {
    	PreparedStatement st = null; 
    	ResultSet rs = null;
    	try {
    		GetConnection();
        	st = con.prepareStatement("select * from schoolnumber where Name = ?");
        	st.setString(1, name);
        	rs = st.executeQuery();
        	System.out.println(name);
        	if(rs != null) {
        		rs.last();
        		System.out.println(rs.getRow());
        		if(rs.getRow()==1) {
        			return true;
        		}
        	}
    }catch(SQLException e) {
   	 e.printStackTrace();
    	}
    return false;
   }
    public static boolean detectUser(String a) {
    	PreparedStatement st = null; 
    	ResultSet rs = null;
    	try {
    		GetConnection();
    		st = con.prepareStatement("select * from schoolnumber where name = ?");
    		st.setString(1, a);
    		rs = st.executeQuery();
    		if(rs != null) {
        		rs.last();
        		System.out.println(rs.getRow());
        		if(rs.getRow()==1) {
        			return true;
        		}
        	}
    }catch(SQLException e) {
   	 e.printStackTrace();
    	}
    return false;
   }
    //删除信息//
    public static void deleteUser(String a) {
    	PreparedStatement st = null; 
    	try {
    		GetConnection();
    		st = con.prepareStatement("delete from schoolnumber where Name = ?");
    		st.setString(1, a);
    		st.execute();
    	}catch(SQLException e) {
      		 e.printStackTrace();
      	 }
    }
}
