
package user.management.admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import javax.swing.*;
import java.util.*;
public class add extends JFrame
{
    Connection conn;
    PreparedStatement pst,pst1;
    JLabel info,lempid,lusrnm,ldesig,lsalary,ladmin,lname,lpasswd,war;
    JTextField tempid,tusrnm,tdesig,tsalary,tadmin,tname,tpasswd;
    JButton clear,create,cancel;
    java.util.Date d;
    ResultSet rs;
    
    public add(int empid)
    {
        setTitle("Add User");
        setBounds(440, 175, 520, 310);
        setLayout(null);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                new admin(empid,0);
                dispose();
            }
        });
           try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             System.out.println("loaded");
             conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
             System.out.println("Connected");
           
           info=new JLabel("Enter Employee Information:");
           info.setFont(new Font("Arial", Font.BOLD, 19));
           info.setBounds(20, 15, 530, 29);
           add(info);
            
           lusrnm=new JLabel("Enter User Name");
           lusrnm.setFont(new Font("Arial", Font.ITALIC, 16));
           lusrnm.setBounds(20, 55, 150, 25);
           add(lusrnm);
           tusrnm=new JTextField("eg. sahil_inbox");
           tusrnm.setForeground(new Color(180, 182, 186));
           tusrnm.setFont(new Font("Arial", Font.ITALIC, 16));
           tusrnm.setBounds(180, 55, 220, 25);
           add(tusrnm);
           
           lpasswd=new JLabel("Enter Password");
           lpasswd.setFont(new Font("Arial", Font.ITALIC, 16));
           lpasswd.setBounds(20, 81, 150, 25);
           add(lpasswd);
           tpasswd=new JTextField("eg. sahil@1998");
           tpasswd.setFont(new Font("Arial", Font.ITALIC, 16));
           tpasswd.setForeground(new Color(180, 182, 186));
           tpasswd.setBounds(180, 81, 220, 25);
           add(tpasswd);
           
           lname=new JLabel("Enter Name");
           lname.setFont(new Font("Arial", Font.ITALIC, 16));
           lname.setBounds(20, 107, 180, 25);
           add(lname);
           tname=new JTextField("eg. sahil mishra");
           tname.setFont(new Font("Arial", Font.ITALIC, 16));
           tname.setForeground(new Color(180, 182, 186));
           tname.setBounds(180, 107, 220, 25);
           add(tname);
      
           ladmin=new JLabel("Admin (yes/no)");
           ladmin.setFont(new Font("Arial", Font.ITALIC, 16));
           ladmin.setBounds(20, 133, 150, 25);
           add(ladmin);
           tadmin=new JTextField("yes/no");
           tadmin.setFont(new Font("Arial", Font.ITALIC, 16));
           tadmin.setForeground(new Color(180, 182, 186));
           tadmin.setBounds(180, 133, 220, 25);
           add(tadmin);
           
           lempid=new JLabel("Employee ID");
           lempid.setFont(new Font("Arial", Font.ITALIC, 16));
           lempid.setBounds(20, 159, 150, 25);
           add(lempid);
           tempid=new JTextField("eg. 2842,1953");
           tempid.setFont(new Font("Arial", Font.ITALIC, 16));
           tempid.setForeground(new Color(180, 182, 186));
           tempid.setBounds(180, 159, 220, 25);
           add(tempid);
           
           ldesig=new JLabel("Designation");
           ldesig.setFont(new Font("Arial", Font.ITALIC, 16));
           ldesig.setBounds(20, 185, 150, 25);
           add(ldesig);
           tdesig=new JTextField("eg. manager");
           tdesig.setFont(new Font("Arial", Font.ITALIC, 16));
           tdesig.setForeground(new Color(180, 182, 186));
           tdesig.setBounds(180, 185, 220, 25);
           add(tdesig);
           
           lsalary=new JLabel("Salary");
           lsalary.setFont(new Font("Arial", Font.ITALIC, 16));
           lsalary.setBounds(20, 211, 220, 25);
           add(lsalary);
           tsalary=new JTextField("eg. 20500");
           tsalary.setFont(new Font("Arial", Font.ITALIC, 16));
           tsalary.setForeground(new Color(180, 182, 186));
           tsalary.setBounds(180, 211, 220, 25);
           add(tsalary);
      
           cancel=new JButton("Cancel");
           cancel.setFont(new Font("Arial", Font.ITALIC, 16));
           cancel.setBounds(308, 242, 90, 22);
           add(cancel);
           
           clear=new JButton("Clear All");
           clear.setFont(new Font("Arial", Font.ITALIC, 16));
           clear.setBounds(200, 242, 103, 22);
           add(clear); 
           
           create=new JButton("Create");
           create.setFont(new Font("Arial", Font.ITALIC, 16));
           create.setBounds(105, 242, 90, 22);
           add(create);
           
           cancel.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e)
                  {
                      setVisible(false);
                      new admin(empid,0);

                  }});
           clear.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e)
                  {
                     tempid.setText("");
                     tpasswd.setText("");
                     tusrnm.setText("");
                     tdesig.setText("");
                     tsalary.setText("");
                     tadmin.setText("");
                     tname.setText(""); 

                  }});
           create.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e)
                  {
                      try{
                          String sql="select * from users";
                          pst1 = conn.prepareStatement(sql);
                          rs=pst1.executeQuery();
                          int empid1=Integer.parseInt(tempid.getText());
                          int flag=0;
                          while(rs.next())
                          {
                              if(empid1==rs.getInt("empid"))
                              {
                                  flag=1;
                                  break;
                              }
                          }
                    if(flag==0)
                        {
                    String SQL="insert into users values(?,?,?,?,?,?,null,null,null,?,?,null)";
                    pst = conn.prepareStatement(SQL);
                    pst.setString(1,tusrnm.getText());
                    pst.setString(2,tpasswd.getText());
                    pst.setString(3,tadmin.getText());
                    pst.setString(4,tname.getText());
                    
                    pst.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                    pst.setString(6,tdesig.getText());
                    pst.setString(7,tsalary.getText());
                    pst.setString(8,tempid.getText());
                    pst.executeQuery();
                    JOptionPane.showMessageDialog(null, "User Created");
                    setVisible(false);
                    new admin(empid,0);
                     }
                    else
                    {
                        
                        tempid.setText("*ID must be unique");
                       
                        tempid.setForeground(Color.red);
                        
                    }
                 }
                      catch(Exception e1)
                      {
                          System.out.println(e1);
                      }
                    
                  }});
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
         setVisible(true);   
   }
    
    public static void main(String[] args) {
        new add(101);
    }
}