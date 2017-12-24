
package user.management;
import java.sql.*;
import java.io.*;

public class dbtable 
{
    static Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    FileInputStream fis1,fis2,fis3,fis4,fis5,fis6;
    String SQL;
    public dbtable(Connection conn)
    {
        
        
            try{
            SQL="create table users(username varchar2(20),password varchar2(20),admin varchar2(5),name varchar2(20),doj date,designation varchar2(13),dob date,email varchar(25),phone number,salary number,empid number,picture BLOB)";
            pst = conn.prepareStatement(SQL);
            pst.executeQuery();
                System.out.println("table created");
            
            SQL="insert into users values('admin','harsh','yes','Harsh Chauhan','1-Feb-2017','manager','18-Oct-1999','harsh880@gmail.com',9264513802,50000,101,?)";
            pst = conn.prepareStatement(SQL);
            fis1=new FileInputStream("pic1.png");
            pst.setBinaryStream(1, fis1, fis1.available());
            pst.executeQuery();
            
            SQL="insert into users values('neha','neha@it','yes','Neha Sharma','20-Jun-2016','receptionist','28-Mar-1999','nehago43@gmail.com',9389023712,20000,102,?)";
            pst = conn.prepareStatement(SQL);
            fis2=new FileInputStream("pic4.jpg");
            pst.setBinaryStream(1, fis2, fis2.available());
            pst.executeQuery();
            
            SQL="insert into users values('amit','123amit','no','Amit Bhardwaj','1-Jan-2017','developer','8-Oct-1996','amitbhard@gmail.com',9645113802,25000,103,?)";
            pst = conn.prepareStatement(SQL);
            fis3=new FileInputStream("pic3.jpg");
            pst.setBinaryStream(1, fis3, fis3.available());
            pst.executeQuery();
            
            SQL="insert into users values('Shrey','shrey123','no','shrey mishra','8-Sep-2016','developer','15-dec-1999','mishra323@gmail.com',9634213502,30000,104,?)";
            pst = conn.prepareStatement(SQL);
            fis4=new FileInputStream("pic2.jpg");
            pst.setBinaryStream(1, fis4, fis4.available());
            pst.executeQuery();
            
            SQL="insert into users values('Arya','aryalock','no','Arya Gupta','06-Sep-2016','secretary','18-Aug-1999','arya1999@gmail.com',9634232141,20000,105,?)";
            pst = conn.prepareStatement(SQL);
            fis5=new FileInputStream("pic6.jpg");
            pst.setBinaryStream(1, fis5, fis5.available());
            pst.executeQuery();
            
            SQL="insert into users values('praveen','sangwa123','no','Praveen Sangwa','24-Nov-2016','developer','21-Apr-1999','sangwa99@gmail.com',8804232141,30000,106,?)";
            pst = conn.prepareStatement(SQL);
            fis6=new FileInputStream("pic5.jpg");
            pst.setBinaryStream(1, fis6, fis6.available());
            pst.executeQuery();
            }
            
            catch(Exception e)
            {
                System.out.println(e);
            }
        
         System.out.println("lol");

    }
    public static void main(String[] args) {
         try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             System.out.println("loaded");
             conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
             System.out.println("Connected");
            
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
         new dbtable(conn);
    

    }
    
}
