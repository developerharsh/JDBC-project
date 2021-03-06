
package user.management.admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import user.management.mainclass;
import user.management.non_admin.user;

public class admin extends JFrame implements ActionListener,ItemListener
{
    JPanel jp,jp2;
    JButton img,img1, logout, save,find,next,prev,first,last,add,delete,browse;
    JLabel NAME, DOJ, DESIGNATION, DOB, EMAIL, PHONE, SALARY, EMPID,heading;
    JLabel[][] list;
    JTextField tNAME, tDOJ, tDESIGNATION, tDOB, tEMAIL, tPHONE, tSALARY, tEMPID,tfind;
    PreparedStatement pst;
    ResultSet rs1,rs2,rs3;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Blob b;
    List l;
    ImageIcon i1,i2;
    
    int i=1;
    static Connection conn;
    int empid;
   public admin(final int empid,final int count)
   {
       
       this.empid=empid;
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
       
       try {
            String SQL = "select * from users where empid=?";
            pst = conn.prepareStatement(SQL);
            pst.setInt(1, empid);
            rs1 = pst.executeQuery();
            rs1.next();

            setBounds(100, 140, 1160, 385);
            setLayout(null);
            addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                new mainclass();
                dispose();
            }
        });
            
            jp=new JPanel();
            jp.setBounds(580, 0, 1160, 370);
            jp.setBackground(new Color(129, 214, 12));
            jp.setLayout(null);
            add(jp);
            jp2=new JPanel();
            jp2.setBounds(0, 0, 580, 370);
            jp2.setBackground(new Color(255, 181, 0));
            jp2.setLayout(null);
            add(jp2);
            
          //*************right panel ***************
        
            tfind=new JTextField("Enter Employee ID");
            tfind.setForeground(new Color(180, 182, 186));
            tfind.setBounds(240, 10, 200, 23);
            jp.add(tfind);
            
            find=new JButton("SEARCH");
            find.setBounds(460,10,90,25);
            jp.add(find);
            
            first=new JButton("First");
            first.setBounds(100,310,90,25);
            jp.add(first);
            first.addActionListener(this);
            
            prev=new JButton("Previous");
            prev.setBounds(200,310,90,25);
            jp.add(prev);
            prev.setEnabled(false);
            prev.addActionListener(this);
            
            next=new JButton("Next");
            next.setBounds(300,310,90,25);
            jp.add(next);
            next.setEnabled(false);
            next.addActionListener(this);
            
            last=new JButton("Last");
            last.setBounds(400,310,90,25);
            jp.add(last);
            last.addActionListener(this);
            
            int s=35;
            
            
            NAME = new JLabel("Name :");
            NAME.setBounds(135, 15+s, 80, 30);
            NAME.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(NAME);
            tNAME = new JTextField("");
            tNAME.setBounds(320, 15+s, 230, 30);
            tNAME.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tNAME);

            DOJ = new JLabel("Date Of Joining :");
            DOJ.setFont(new Font("Arial", Font.ITALIC, 18));
            DOJ.setBounds(135, 45+s, 200, 30);
            jp.add(DOJ);
            tDOJ = new JTextField("");
            tDOJ.setFont(new Font("Arial", Font.ITALIC, 18));
            tDOJ.setBounds(320, 45+s, 230, 30);
            jp.add(tDOJ);

            DESIGNATION = new JLabel("Designation :");
            DESIGNATION.setBounds(135, 75+s, 200, 30);
            DESIGNATION.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(DESIGNATION);
            tDESIGNATION = new JTextField("");
            tDESIGNATION.setBounds(320, 75+s, 230, 30);
            tDESIGNATION.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tDESIGNATION);
            
            
            SALARY = new JLabel("Salary :");
            SALARY.setBounds(135, 195+s, 200, 30);
            SALARY.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(SALARY);
            tSALARY = new JTextField("");
            tSALARY.setBounds(320, 195+s, 230, 30);
            tSALARY.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tSALARY);

            EMPID = new JLabel("Employee ID :");
            EMPID.setBounds(135, 225+s, 200, 30);
            EMPID.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(EMPID);
            tEMPID = new JTextField("");
            tEMPID.setBounds(320, 225+s, 230, 30);
            tEMPID.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tEMPID);

            DOB = new JLabel("Date Of Birth :");
            DOB.setFont(new Font("Arial", Font.ITALIC, 18));
            DOB.setBounds(135, 105+s, 200, 30);
            jp.add(DOB);
            
            EMAIL = new JLabel("Email id :");
            EMAIL.setBounds(135, 135+s, 200, 30);
            EMAIL.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(EMAIL);
            
           PHONE = new JLabel("Contact number :");
           PHONE.setBounds(135, 165 + s, 200, 30);
           PHONE.setFont(new Font("Arial", Font.ITALIC, 18));
           jp.add(PHONE);
            
            tDOB = new JTextField("");
            tDOB.setFont(new Font("Arial", Font.ITALIC, 18));
            tDOB.setBounds(320, 105+s, 230, 30);
            jp.add(tDOB);

            tEMAIL = new JTextField("");
            tEMAIL.setBounds(320, 135+s, 230, 30);
            tEMAIL.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tEMAIL);


            tPHONE = new JTextField("");
            tPHONE.setBounds(320, 165+s, 230, 30);
            tPHONE.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tPHONE);
  
            i1=new ImageIcon("no.png");
            img = new JButton();
            img.setIcon(i1);
            img.setBounds(15, 95, 110, 110);
            jp.add(img);
           Image img2 = i1.getImage();
           Image newimg = img2.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
           i1 = new ImageIcon(newimg);
           img.setIcon(i1);
        } 
        catch (Exception e1) {
            tDOB = new JTextField("");
            tDOB.setBounds(320, 140, 230, 30);
            tDOB.setFont(new Font("Arial", Font.ITALIC, 18));
            jp.add(tDOB);
            tEMAIL = new JTextField("");
            tEMAIL.setFont(new Font("Arial", Font.ITALIC, 18));
            tEMAIL.setBounds(320, 170, 230, 30);
            jp.add(tEMAIL);

            tPHONE = new JTextField("");
            tPHONE.setFont(new Font("Arial", Font.ITALIC, 18));
            tPHONE.setBounds(320, 200, 230, 30);
            jp.add(tPHONE);
            
            img = new JButton(new ImageIcon("no.png"));
            img.setBounds(15, 87, 108, 110);
            jp.add(img);

            System.out.println(e1);
        }

       //*************left panel*************
       try{
           
            l=new List();
            l.add("Employee ID");
              l.add("Name");
              l.add("Salary");
              l.add("Date Of Joining");
              l.add("Designation");
              l.addItemListener(this);
              l.setFont(new Font("Arial", Font.BOLD, 13));
              l.setBounds(420, 40, 150, 22);
              jp2.add(l);
                      
              JLabel sort=new JLabel("Sort by :");
              sort.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
              sort.setBounds(360, 40, 150, 22);
              jp2.add(sort);
              
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
            JFrame f=this;
             add.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                    new add(empid);
                    f.setVisible(false);
                }
                 }
            );
             delete.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent e)
                {
                    new del(empid);
                    f.setVisible(false);
                }
                 }
            );
                        
        heading=new JLabel("Employee Table");
           heading.setFont(new Font("Arial", Font.BOLD, 19));
           heading.setBounds(180, 25, 230, 29);
           jp2.add(heading);
           int c =30;
           list[0][0] = new JLabel("Employee ID");
           list[0][0].setBounds(10, 40 + c, 130, 23);
           list[0][0].setFont(new Font("Arial", Font.ITALIC, 17));
           jp2.add(list[0][0]);

           list[0][1] = new JLabel("Name");
           list[0][1].setBounds(142, 40 + c, 100, 23);
           list[0][1].setFont(new Font("Arial", Font.ITALIC, 17));
           jp2.add(list[0][1]);

           list[0][2] = new JLabel("Designation");
           list[0][2].setBounds(232, 40 + c, 100, 23);
           list[0][2].setFont(new Font("Arial", Font.ITALIC, 17));
           jp2.add(list[0][2]);

           list[0][3] = new JLabel("Salary");
           list[0][3].setBounds(346, 40 + c, 80, 23);
           list[0][3].setFont(new Font("Arial", Font.ITALIC, 17));
           jp2.add(list[0][3]);

           list[0][4] = new JLabel("Email ID");
           list[0][4].setBounds(435, 40 + c, 100, 23);
           list[0][4].setFont(new Font("Arial", Font.ITALIC, 17));
           jp2.add(list[0][4]);

           while (rs2.next()) {
               list[i][0] = new JLabel(Integer.toString(rs2.getInt("empid")));
               list[i][0].setBounds(58, 40 + (i * 25) + c, 50, 20);
               jp2.add(list[i][0]);

               list[i][1] = new JLabel(rs2.getString("name"));
               list[i][1].setBounds(125, 40 + (i * 25) + c, 100, 20);
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
        
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                   setVisible(false);
                   new mainclass();
            }
          }
        );
        
       find.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   
                   String SQL = "select * from users where empid=?";
                   pst = conn.prepareStatement(SQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                   pst.setString(1, tfind.getText());
                   rs2 = pst.executeQuery();

                   if (rs2.next()) {
                       tNAME.setText(rs2.getString("name"));
                       tDOJ.setText(df.format(rs2.getDate("DOJ")));
                       tDESIGNATION.setText(rs2.getString("designation"));
                       tDOB.setText(df.format(rs2.getDate("DOB")));
                       tEMAIL.setText(rs2.getString("email"));
                       tPHONE.setText(rs2.getString("phone"));
                       tSALARY.setText(rs2.getString("salary"));
                       tEMPID.setText(Integer.toString(rs2.getInt("empid")));
                       b = rs2.getBlob("picture");
                       String pic = "hey.jpg";
                       FileOutputStream fos = new FileOutputStream(pic);
                       byte arr[] = b.getBytes(1, (int) b.length());
                       fos.write(arr);
                       fos.close();
                       i1=new ImageIcon(pic);
                       Image img2 = i1.getImage();
                       Image newimg = img2.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
                       i1 = new ImageIcon(newimg);
                       img.setIcon(i1);
                       i1.getImage().flush();
                       next.setEnabled(true);
                       prev.setEnabled(true);
                       first.setEnabled(true);
                       last.setEnabled(true);
                              
                       
                   }
                   
               }
               catch(Exception e1)
               {
                   System.err.println(e1);
               }
           }
       });
                  try
                  {
                    String sql="select * from users order by empid asc";    
                    pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    rs3 = pst.executeQuery();
                  }
                  catch(Exception e1)
                  {
                      System.out.println(e1);
                  }
      
       setVisible(true);
       if(count==1)
       {
          new user(empid,conn);
      
       }
   }
    public void actionPerformed(ActionEvent e)
       {
           JButton btn=(JButton)e.getSource();
           
          
           try
           {
               
           if(btn==first)
           {
               rs3.first();
               prev.setEnabled(false);
               first.setEnabled(false);
               next.setEnabled(true);
               last.setEnabled(true);
              
              System.out.println("first");
               
           }
           else if(btn==prev)
           {
               rs3.previous();
               if(rs3.isFirst())
               {
                   prev.setEnabled(false);
                   first.setEnabled(false);
               }
               next.setEnabled(true);
               last.setEnabled(true);
           }
           else if(btn==next)
           {
               System.out.println("next");
               rs3.next();
               prev.setEnabled(true);
               first.setEnabled(true);
               if(rs3.isLast())
               {
                   next.setEnabled(false);
                   last.setEnabled(false);
               }
               
           }
           else if(btn==last)
           {
               
               rs3.last();
               prev.setEnabled(true);
               first.setEnabled(true);
               next.setEnabled(false);
               last.setEnabled(false);
               
           }
          
                
          tNAME.setText(rs3.getString("name")); 
           tDESIGNATION.setText(rs3.getString("designation"));
           tEMPID.setText(rs3.getString("empid"));
           tDOJ.setText(df.format(rs3.getDate("DOJ")));
           tSALARY.setText(rs3.getString("salary"));
           tDOB.setText(df.format(rs3.getDate("DOB")));  
           tEMAIL.setText(rs3.getString("email"));
           tPHONE.setText(rs3.getString("phone")); 
           
            b=rs3.getBlob("picture");
            String pic="hey.jpg";
            FileOutputStream fos=new FileOutputStream(pic);
            byte arr[]=b.getBytes(1, (int)b.length());
            fos.write(arr);
            fos.close();
          
            i2 = new ImageIcon(pic);
            Image img2 = i2.getImage();
            Image newimg = img2.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
            i2 = new ImageIcon(newimg);
            img.setIcon(i2);
            i2.getImage().flush();

            
           }
           
           catch(NullPointerException e1)
           {
               tDOB.setText("");
               tEMAIL.setText("");
               tPHONE.setText("");
               i2 = new ImageIcon("no.png");
               Image img2 = i2.getImage();
               Image newimg = img2.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
               i2 = new ImageIcon(newimg);
               img.setIcon(i2);
               i2.getImage().flush();
           }
           catch(Exception e2)
           {
               System.out.println(e2);
           }
           
       }
   public static void main(String[] args) {
        
        new admin(101,1);
    }

    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        int index=l.getSelectedIndex();
        switch(index)
        {
            
            case 0:
                try
                  {
                    String SQL = "select * from users order by empid asc";
                    pst = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs2 = pst.executeQuery();
                  }
                catch(Exception e1)
                  {
                      System.err.println(e1);
                  }
                  break;
                  
                case 1:
                try
                  {
                    String SQL = "select * from users order by name asc";
                    pst = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs2 = pst.executeQuery();
                  }
                catch(Exception e1)
                  {
                      System.err.println(e1);
                  }
                  break;
                  
                case 2:
                try
                  {
                    String SQL = "select * from users order by salary asc";
                    pst = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs2 = pst.executeQuery();
                  }
                catch(Exception e1)
                  {
                      System.err.println(e1);
                  }
                  break;
                  
                case 3:
                try
                  {
                    String SQL = "select * from users order by DOJ asc";
                    pst = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs2 = pst.executeQuery();
                  }
                catch(Exception e1)
                  {
                      System.err.println(e1);
                  }
                  break;
                  
                case 4:
                try
                  {
                    String SQL = "select * from users order by designation asc";
                    pst = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs2 = pst.executeQuery();
                  }
                catch(Exception e1)
                  {
                      System.err.println(e1);
                  }
                
          }
        try{
            i=1;
            int c=10;
         while (rs2.next()) {
               
               list[i][0].setText(Integer.toString(rs2.getInt("empid")));
       
               list[i][1].setText(rs2.getString("name"));
             
               list[i][2].setText(rs2.getString("designation"));
            
               list[i][3].setText(Integer.toString(rs2.getInt("salary")));
            
               list[i][4].setText(rs2.getString("email"));
            
               i++;
            }
        }
        catch(Exception e1)
        {
            System.err.println(e1);
        }
    }    
        
}
