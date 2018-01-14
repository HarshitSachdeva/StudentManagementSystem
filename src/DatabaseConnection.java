
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection con;
    
    public DatabaseConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","harshit","12345");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    int validateUser(String username, String password) {
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT COUNT(*) FROM teachers WHERE username='"+username+"' AND password='"+password+"' AND status=1");
            if(rs.next()) {
                return rs.getInt(1);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    int addUser(String username, String password) {
        try {
            PreparedStatement ps =con.prepareStatement("SELECT COUNT(*) FROM teachers WHERE username = ?");
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                if(rs.getInt(1) > 0) {
                    return 2;
                } else {
                    Statement stmt=con.createStatement();
                    rs=stmt.executeQuery("SELECT COUNT(*) FROM teachers");
                    int id =0;
                    while(rs.next()) {
                        id = rs.getInt(1)+1;
                    }
                    ps =con.prepareStatement("INSERT INTO teachers (id, username, password, status) VALUES (?,?,?,?)");
                    ps.setInt(1, id);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setInt(4, 1);

                    return ps.executeUpdate();
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     
    
    
    int addStudent(String name, String regd,String fname, String mname, String br, String add,String mob, Date sqldate) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM students WHERE regd_no = ?");
            ps.setString(1,regd);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                if(rs.getInt(1) > 0) {
                    return 2;
                } 
                else {
                    Statement stmt=con.createStatement();
                    rs=stmt.executeQuery("SELECT COUNT(*) FROM students");
                    int id =0;
                    while(rs.next()) {
                        id = rs.getInt(1)+1;
                    }
                    ps = con.prepareStatement("INSERT INTO students (id,name,regd_no,father_name,mother_name,branch,address,mobile,dob) VALUES (?,?,?,?,?,?,?,?,?)");
                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setString(3, regd);
                    ps.setString(4, fname);
                    ps.setString(5, mname);
                    ps.setString(6, br);
                    ps.setString(7, add);
                    ps.setString(8, mob);
                    ps.setDate(9, sqldate);
                    return ps.executeUpdate();
                }
            }
        }
        catch(SQLException e) {
           e.printStackTrace();
        }
      return 0;
    }
      
      
    int delStudent(String regd)  {
        try{
            Statement stmt=con.createStatement();
            return stmt.executeUpdate("delete from students where regd_no='"+regd+"'");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }   

    int checkValidStudent(String register) {
        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from students where regd_no='"+ register + "'");
            if(rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    ResultSet fetchUser(String register) {
        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from students where regd_no='"+ register + "'");
            return rs;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    int updateStudent(String name,String regd,String fname,String mname,String br, String add,String mob, Date sqldate)  {
        try {
            Statement stmt=con.createStatement();
            return stmt.executeUpdate("update students set name='"+name+"',father_name='"+fname+"',mother_name='"+mname+"',branch='"+br+"',address='"+add+"',mobile='"+mob+"',dob=DATE '"+sqldate+"' WHERE regd_no='"+regd+"'");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }   
}