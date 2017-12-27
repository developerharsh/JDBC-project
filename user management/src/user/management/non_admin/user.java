package user.management.non_admin;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.DateFormat;
import user.management.mainclass;

public class user extends JFrame {

    JButton img, logout, save,browse;
    JPanel jp;
    JLabel info,NAME, DOJ, DESIGNATION, DOB, EMAIL, PHONE, SALARY, EMPID;
    JTextField tNAME, tDOJ, tDESIGNATION, tDOB, tEMAIL, tPHONE, tSALARY, tEMPID;
    PreparedStatement pst;
    ResultSet rs;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    static Connection conn;

    user() { }

    public user(int empid,Connection conn) {
        try {
            String SQL = "select * from users where empid=?";
            pst = conn.prepareStatement(SQL);
            pst.setInt(1, empid);
            rs = pst.executeQuery();
            rs.next();

            setBounds(420, 150, 580, 370);
            setLayout(null);
            addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });
            
            jp=new JPanel();
            jp.setBounds(0, 0, 580, 370);
            jp.setBackground(new Color(153, 135, 126));
            jp.setLayout(null);
            add(jp);
            
            browse = new JButton("Browse");
            browse.setBounds(20, 208, 100, 25);
            jp.add(browse);
            JFrame m=this;
            browse.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e)
                  {
                      try{
                      FileDialog f=new FileDialog(m,"browse",0);
                      f.setVisible(true);
                      FileInputStream fis=new FileInputStream(f.getDirectory()+f.getFile());
                      String sql="update users set picture=? where empid=?";
                      pst=conn.prepareStatement(sql);
                      pst.setBinaryStream(1,fis,fis.available());
                      pst.setInt(2, empid);
                      pst.executeQuery();
                      ImageIcon i=new ImageIcon(f.getDirectory()+f.getFile());
                      Image img2 = i.getImage();
                      Image newimg = img2.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
                      i = new ImageIcon(newimg);
                      img.setIcon(i);            
                      }
                      catch(Exception e1)
                      {
                          System.out.println(e1);
                      }

                  }});
            info = new JLabel("Welcome user !!!");
            info.setBounds(30, 8, 380, 30);
            info.setForeground(new Color(255,255,255));
            info.setFont(new Font("Arial", Font.ITALIC, 20));
            jp.add(info);
            
            NAME = new JLabel("Name :");
            NAME.setBounds(140, 42, 80, 30);
            NAME.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(NAME);
            tNAME = new JTextField(rs.getString("username"));
            tNAME.setBounds(320, 42, 220, 30);
            tNAME.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tNAME);

            DOJ = new JLabel("Date Of Joining :");
            DOJ.setFont(new Font("Arial", Font.ITALIC, 18));
            DOJ.setBounds(140, 72, 200, 30);
            jp.add(DOJ);
            tDOJ = new JTextField(df.format(rs.getDate("DOJ")));
            tDOJ.setFont(new Font("Arial", Font.ITALIC, 18));
            tDOJ.setBounds(320, 72, 220, 30);
            jp.add(tDOJ);

            DESIGNATION = new JLabel("Designation :");
            DESIGNATION.setBounds(140, 102, 200, 30);
            DESIGNATION.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(DESIGNATION);
            tDESIGNATION = new JTextField(rs.getString("designation"));
            tDESIGNATION.setBounds(320, 102, 220, 30);
            tDESIGNATION.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tDESIGNATION);


            SALARY = new JLabel("Salary :");
            SALARY.setBounds(140, 222, 200, 30);
            SALARY.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(SALARY);
            tSALARY = new JTextField(rs.getString("salary"));
            tSALARY.setBounds(320, 222, 220, 30);
            tSALARY.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tSALARY);

            EMPID = new JLabel("Employee ID :");
            EMPID.setBounds(140, 252, 200, 30);
            EMPID.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(EMPID);
            tEMPID = new JTextField(rs.getString("empid"));
            tEMPID.setBounds(320, 252, 220, 30);
            tEMPID.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tEMPID);

            DOB = new JLabel("Date Of Birth :");
            DOB.setFont(new Font("Arial", Font.ITALIC, 18));
            DOB.setBounds(140, 132, 200, 30);
            jp.add(DOB);
            
            EMAIL = new JLabel("Email id :");
            EMAIL.setBounds(140, 162, 200, 30);
            EMAIL.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(EMAIL);
            PHONE = new JLabel("Contact number :");
            PHONE.setBounds(140, 192, 200, 30);
            PHONE.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(PHONE);
            
            tDOB = new JTextField(df.format(rs.getDate("DOB")));
            tDOB.setFont(new Font("Arial", Font.ITALIC, 18));
            tDOB.setBounds(320, 132, 220, 30);
            jp.add(tDOB);

            
            tEMAIL = new JTextField(rs.getString("email"));
            tEMAIL.setBounds(320, 162, 220, 30);
            tEMAIL.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tEMAIL);

            tPHONE = new JTextField(Long.toString(rs.getLong("phone")));
            tPHONE.setBounds(320, 192, 220, 30);
            tPHONE.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tPHONE);

            Blob b=rs.getBlob("picture");
            FileOutputStream fos=new FileOutputStream("sm1cpy.png");
            byte arr[]=b.getBytes(1, (int)b.length());
            fos.write(arr);
            fos.close();
            img = new JButton();
            img.setBounds(15, 87, 110, 110);
            jp.add(img);
            ImageIcon i=new ImageIcon("sm1cpy.png");
            Image img2 = i.getImage();
            Image newimg = img2.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
            i = new ImageIcon(newimg);
            img.setIcon(i);
            
        } 
        catch (NullPointerException e) {
            tDOB = new JTextField("");
            tDOB.setBounds(320, 132, 220, 30);
            tDOB.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tDOB);
            tEMAIL = new JTextField("");
            tEMAIL.setFont(new Font("Arial", Font.ITALIC, 18));
            tEMAIL.setBounds(320, 162, 220, 30);
            jp.add(tEMAIL);

            tPHONE = new JTextField("");
            tPHONE.setFont(new Font("Arial", Font.ITALIC, 18));
            tPHONE.setBounds(320, 192, 220, 30);
            jp.add(tPHONE);
            
            img = new JButton(new ImageIcon("no.png"));
            img.setBounds(15, 87, 100, 110);
            jp.add(img);

            System.out.println(e);
        }
        catch (Exception e1) {
            System.out.println(e1);
        }
        

        logout = new JButton("Exit");
        logout.setBounds(260, 295, 100, 25);
        jp.add(logout);
        
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                   setVisible(false);
            }
          }
        );

        
        save = new JButton("Save and Exit");
        save.setBounds(370, 295, 130, 25);
        jp.add(save);
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                   setVisible(false);
                   try
                   {
                       String SQL = "update users set name=?,dob=?, email=?, phone=? where empid=?";
                       pst = conn.prepareStatement(SQL);
                       pst.setString(1, tNAME.getText());
                       
                       String s1date=tDOB.getText();
                       SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
                       java.util.Date d3=sdf1.parse(s1date);
                       java.sql.Date d4 = new java.sql.Date(d3.getTime());
                       pst.setDate(2,d4);
                      
                       pst.setString(3, tEMAIL.getText());
                       pst.setLong(4,Long.parseLong(tPHONE.getText()));
                       pst.setInt(5,empid);
                       
                       
                       pst.executeUpdate();
                       
                   }
                   catch (Exception e1)
                   {
                        System.out.println(e1);
                   }
            }
          }
        );

        setVisible(true);
        

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
        
        new user(102,conn);
        //new user("neha",conn);
    }


}
