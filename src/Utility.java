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
    public static Connection GetConnection() {
    if(con != null) {
    	return con;
    }
    else{
    	try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
   	 	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "henry7086");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return con;
    	}
    }
    
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
     }finally {
    	 try {
    		 if(rs!=null) {
    			 rs.close();
    		 }
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
    	 try {
    		 if(st!=null) {
    			 st.close();
    		 }
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     return false;
    }
    
    public static ResultSet GetUser() {
    	PreparedStatement st = null; 
    	ResultSet rs = null;
    	ArrayList<String> arrcolname = new ArrayList<String>();
    	ArrayList<String> arrcolout = new ArrayList<String>();
    	try {
    		GetConnection();
        	st = con.prepareStatement("select * from schoolnumber");
        	rs = st.executeQuery();
        	while(rs.next()) {
        		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        		 int icolnum = rsmd.getColumnCount();
                 for (int i = 1; i <= icolnum; i++) {
                     arrcolname.add(rsmd.getColumnName(i).toString());
                     arrcolout.add(rs.getString(i));
                 	}
             	}
        	rs.close();
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
    	return rs;
    }
   
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
    }finally {
   	 try {
   		 if(rs!=null) {
   			 rs.close();
   		 }
   	 }catch(SQLException e) {
   		 e.printStackTrace();
   	 }
   	 try {
   		 if(st!=null) {
   			 st.close();
   		 }
   	 }catch(SQLException e) {
   		 e.printStackTrace();
   	 }
    }
    return false;
   }
    public static void deleteUser(String a) {
    	PreparedStatement st = null; 
    	ResultSet rs = null;
    	try {
    		GetConnection();
    		String sql = "delete from schoolnumber where Name = '"+a+"'";
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate(sql);
    		con.close();
    	}catch(SQLException e) {
      		 e.printStackTrace();
      	 }
    }
}
