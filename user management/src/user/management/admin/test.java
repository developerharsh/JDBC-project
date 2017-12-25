import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;


public class test {
    
    static JPanel jp,jp2;
    static JButton img,img1, logout, save,find,next,prev,first,last,add,delete,browse;
    static JLabel NAME, DOJ, DESIGNATION, DOB, EMAIL, PHONE, SALARY, EMPID,heading;
    static JLabel[][] list;
    static JTextField tNAME, tDOJ, tDESIGNATION, tDOB, tEMAIL, tPHONE, tSALARY, tEMPID,tfind;
    static PreparedStatement pst;
  static ResultSet rs1,rs2,rs3;
  static Connection conn;

    public static void main(String... args) {
        JFrame f = new JFrame();
        JPanel jp2 = new JPanel();
        jp2.setBounds(0, 0, 500, 510);
       
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
        try{
            
       String SQL = "select * from users order by empid asc";
       pst = conn.prepareStatement(SQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
       rs2 = pst.executeQuery();
       list=new JLabel[15][5];
       
             add=new JButton("Add");
            add.setBounds(290,310,80,23);
            jp2.add(add);
            delete=new JButton("Delete");
            delete.setBounds(380,310,90,23);
            jp2.add(delete);
            logout=new JButton("Logout");
            logout.setBounds(480,310,90,23);
            jp2.add(logout);
        int i=1;                
        heading=new JLabel("Employee Table");
           heading.setFont(new Font("Segoe Print", Font.BOLD, 19));
           heading.setBounds(180, 15, 230, 29);
           jp2.add(heading);
           int c = 10;
           list[0][0] = new JLabel("Employee ID");
           list[0][0].setBounds(10, 40 + c, 130, 23);
           list[0][0].setFont(new Font("Segoe Print", Font.ITALIC, 17));
           jp2.add(list[0][0]);

           list[0][1] = new JLabel("Name");
           list[0][1].setBounds(142, 40 + c, 100, 23);
           list[0][1].setFont(new Font("Segoe Print", Font.ITALIC, 17));
           jp2.add(list[0][1]);

           list[0][2] = new JLabel("Designation");
           list[0][2].setBounds(225, 40 + c, 100, 23);
           list[0][2].setFont(new Font("Segoe Print", Font.ITALIC, 17));
           jp2.add(list[0][2]);

           list[0][3] = new JLabel("Salary");
           list[0][3].setBounds(346, 40 + c, 80, 23);
           list[0][3].setFont(new Font("Segoe Print", Font.ITALIC, 17));
           jp2.add(list[0][3]);

           list[0][4] = new JLabel("Email ID");
           list[0][4].setBounds(435, 40 + c, 100, 23);
           list[0][4].setFont(new Font("Segoe Print", Font.ITALIC, 17));
           jp2.add(list[0][4]);

           while (rs2.next()) {
               list[i][0] = new JLabel(Integer.toString(rs2.getInt("empid")));
               list[i][0].setBounds(58, 40 + (i * 25) + c, 50, 20);
               jp2.add(list[i][0]);

               list[i][1] = new JLabel(rs2.getString("name"));
               list[i][1].setBounds(127, 40 + (i * 25) + c, 100, 20);
               jp2.add(list[i][1]);

               list[i][2] = new JLabel(rs2.getString("designation"));
               list[i][2].setBounds(247, 40 + (i * 25) + c, 100, 20);
               jp2.add(list[i][2]);

               list[i][3] = new JLabel(Integer.toString(rs2.getInt("salary")));
               list[i][3].setBounds(352, 40 + (i * 25) + c, 120, 20);
               jp2.add(list[i][3]);

               list[i][4] = new JLabel(rs2.getString("email"));
               list[i][4].setBounds(420, 40 + (i * 25) + c, 150, 20);
               jp2.add(list[i][4]);

               System.out.println("Student id is " + rs2.getInt("empid"));
               System.out.println("Student name is " + rs2.getString("name"));

               i++;
           }
       } catch (Exception e1) {
           System.out.println(e1);
       }
        JScrollPane scrollPane = new JScrollPane(jp2);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 30, 300, 150);
        f.add(scrollPane);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}