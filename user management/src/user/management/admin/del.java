
package user.management.admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import javax.swing.*;
import java.util.*;
import user.management.mainclass;
public class del extends JFrame
{
    Connection conn;
    PreparedStatement pst;
    JLabel ldel;
    JTextField tdel;
    JButton delete,cancel;
    ResultSet rs;
    
    public del(int empid)
    {
         setTitle("Delete User");
        setBounds(460, 297, 470, 140);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                new admin(empid,0);
                dispose();
            }
        });
        setLayout(null);
           try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             System.out.println("loaded");
             conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
             System.out.println("Connected");
            
           ldel=new JLabel("Enter Employee ID");
           ldel.setFont(new Font("Arial", Font.ITALIC, 16));
           ldel.setBounds(20,20, 180, 25);
           add(ldel);
           tdel=new JTextField("eg. 1011");
           tdel.setForeground(new Color(180, 182, 186));
           tdel.setFont(new Font("Arial", Font.ITALIC, 16));
           tdel.setBounds(190, 20, 220, 25);
           add(tdel);
           delete=new JButton("Delete");
           delete.setFont(new Font("Arial", Font.ITALIC, 16));
           delete.setBounds(200, 55, 100, 25);
           add(delete);
           cancel=new JButton("Cancel");
           cancel.setFont(new Font("Arial", Font.ITALIC, 16));
           cancel.setBounds(309, 55, 100, 25);
           add(cancel);
                    
           cancel.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e)
                  {
                      setVisible(false);
                      new admin(empid,0);

                  }});
           delete.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e)
                  {
                      try{
                          String sql="delete from users where empid=?";
                          pst = conn.prepareStatement(sql);
                          pst.setInt(1,Integer.parseInt(tdel.getText()));
                          rs=pst.executeQuery();
                          
                          setVisible(false);
                          if(empid==Integer.parseInt(tdel.getText()))
                          {
                              JOptionPane.showMessageDialog(null, "Your Account Deleted");
                              new mainclass();
                          }
                          else
                          {
                              JOptionPane.showMessageDialog(null, "User Deleted");
                              new admin(empid,0);
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
        new del(155);
    }
    
}
