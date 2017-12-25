
package user.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import user.management.non_admin.user;
import user.management.admin.admin;
import javax.swing.*;


public class mainclass extends Frame 
{
 
    Label la1,la2,la3,la4;
    TextField t1,t2;
    Button b1,cancel;
    PreparedStatement pst;
    ResultSet rs;
    Connection conn;
    
    
    public mainclass()
    {
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
       
        setVisible(true);
        setSize(415,219);
        setLocation(440,230);
        setLayout(null);
        setTitle("AUTHENTICATION");
        setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
          });
        
        la1=new Label("User Login");
        la1.setBounds(125,53,150,33);
        la1.setVisible(true);
        la1.setFont(new Font("Segoe Print", Font.ITALIC, 28));
        la1.setForeground(Color.WHITE);
        add(la1);
        
        la2=new Label("User Name :");
        la2.setBounds(55,97,103,15);
        la2.setVisible(true);
        la2.setFont(new Font("Segoe Print", Font.ITALIC, 18));
        la2.setForeground(Color.WHITE);
        add(la2);
        t1=new TextField();
        t1.setBounds(170,97,160,17);
        t1.setVisible(true);
        add(t1);
        
        la3=new Label("Password :");
        la3.setBounds(55,124,100,20);
        la3.setFont(new Font("Segoe Print", Font.ITALIC, 18));
        la3.setVisible(true);
        la3.setForeground(Color.WHITE);
        add(la3);
        t2=new TextField();
        t2.setBounds(170,124,160,17);
        t2.setVisible(true);
        t2.setEchoChar('*');
        add(t2);
        
       b1=new Button("Login");
       b1.setBounds(160,153,80,22);
       b1.setFont(new Font("Segoe Print",Font.PLAIN,14));
       add(b1);
       
       cancel=new Button("Cancel");
       cancel.setBounds(250,153,80,22);
       cancel.setFont(new Font("Segoe Print",Font.PLAIN,14));
       add(cancel);
       Frame f=this;
       
         b1.addActionListener(new ActionListener() 
         {
               public void actionPerformed(ActionEvent e)
               {
                   try
                   {
                       String user = t1.getText();
                       String passwd = t2.getText();
                       String SQL = "select * from users where username=? and password=?";
                       pst = conn.prepareStatement(SQL);

                       pst.setString(1, user);
                       pst.setString(2, passwd);
                       rs = pst.executeQuery();
                       int empid;
                   if (rs.next())
                    {
                      String admin=rs.getString("admin");
                      empid=rs.getInt("empid");
                      if(admin.equals("yes"))
                      {
                          System.out.println("Admin");
                          new admin(empid,1);
                          setVisible(false);
                          
                      }
                      else
                      {
                          System.out.println("non admin");
                          new user(101,conn);
                          setVisible(false);
                      }
                        
                    }
                   else
                   {
                       la4=new Label("* wrong userid or password");
                       la4.setBounds(168,141,150,18);
                       la4.setFont(new Font("Segoe Print",Font.ITALIC,11));
                       la4.setForeground(Color.RED);
                       add(la4);
                       b1.setBounds(160,163,80,22);
                       cancel.setBounds(250,163,80,22);
                       t2.setText("");
                       
                       System.out.println("User don't exhist");
                   }
                       
                   }
                   catch(Exception e1)
                   {
                       System.out.println(e1);
                   }
                  
               }

         }
        );
         
          cancel.addActionListener(new ActionListener() 
         {
               public void actionPerformed(ActionEvent e)
               {
                   f.setVisible(false);
                  
               }

         }
        );
          
          
    }
    
    public static void main(String[] args) 
    {
        new mainclass();
    }
 
    
}
