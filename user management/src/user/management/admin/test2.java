
package user.management.admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import user.management.mainclass;

public class test2 extends JFrame implements ActionListener
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
    ImageIcon i1,i2;
    int i=1;
    static Connection conn;
    int empid;
   public test2(int empid)
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

            setBounds(160, 140, 1160, 385);
            setLayout(null);
            
            jp=new JPanel();
            jp.setBounds(580, 0, 1160, 370);
            jp.setBackground(new Color(129, 214, 12));
            jp.setLayout(null);
            add(jp);
            jp2=new JPanel();
            jp2.setBounds(0, 0, 580, 370);
            jp2.setBackground(new Color(255, 181, 0));
            jp2.setLayout(null);
           
            
          //*************right panel ***************
          
          
            browse = new JButton("Browse");
            browse.setBounds(20, 210, 100, 22);
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
            
        save = new JButton("Modify");
        save.setBounds(18, 240,104, 25);
        jp.add(save);
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                  
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
                       JOptionPane.showMessageDialog(null, "Record modified");
                  
                   }
                   catch (Exception e1)
                   {
                        System.out.println(e1);
                   }
            }
          }
        );
        
            tfind=new JTextField("Enter Employee ID");
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
            prev.addActionListener(this);
            
            next=new JButton("Next");
            next.setBounds(300,310,90,25);
            jp.add(next);
            next.addActionListener(this);
            
            last=new JButton("Last");
            last.setBounds(400,310,90,25);
            jp.add(last);
            last.addActionListener(this);
            
            int s=35;
            
            
            NAME = new JLabel("Name :");
            NAME.setBounds(135, 15+s, 80, 30);
            NAME.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(NAME);
            tNAME = new JTextField(rs1.getString("name"));
            tNAME.setBounds(320, 15+s, 230, 30);
            tNAME.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(tNAME);

            DOJ = new JLabel("Date Of Joining :");
            DOJ.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            DOJ.setBounds(135, 45+s, 200, 30);
            jp.add(DOJ);
            tDOJ = new JTextField(df.format(rs1.getDate("DOJ")));
            tDOJ.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            tDOJ.setBounds(320, 45+s, 230, 30);
            jp.add(tDOJ);

            DESIGNATION = new JLabel("Designation :");
            DESIGNATION.setBounds(135, 75+s, 200, 30);
            DESIGNATION.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(DESIGNATION);
            tDESIGNATION = new JTextField(rs1.getString("designation"));
            tDESIGNATION.setBounds(320, 75+s, 230, 30);
            tDESIGNATION.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(tDESIGNATION);
            
            
            SALARY = new JLabel("Salary :");
            SALARY.setBounds(135, 195+s, 200, 30);
            SALARY.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(SALARY);
            tSALARY = new JTextField(rs1.getString("salary"));
            tSALARY.setBounds(320, 195+s, 230, 30);
            tSALARY.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(tSALARY);

            EMPID = new JLabel("Employee ID :");
            EMPID.setBounds(135, 225+s, 200, 30);
            EMPID.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(EMPID);
            tEMPID = new JTextField(rs1.getString("empid"));
            tEMPID.setBounds(320, 225+s, 230, 30);
            tEMPID.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(tEMPID);

            DOB = new JLabel("Date Of Birth :");
            DOB.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            DOB.setBounds(135, 105+s, 200, 30);
            jp.add(DOB);
            
            EMAIL = new JLabel("Email id :");
            EMAIL.setBounds(135, 135+s, 200, 30);
            EMAIL.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(EMAIL);
            
           PHONE = new JLabel("Contact number :");
           PHONE.setBounds(135, 165 + s, 200, 30);
           PHONE.setFont(new Font("Segoe Print", Font.ITALIC, 18));
           jp.add(PHONE);
            
            tDOB = new JTextField(df.format(rs1.getDate("DOB")));
            tDOB.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            tDOB.setBounds(320, 105+s, 230, 30);
            jp.add(tDOB);

            tEMAIL = new JTextField(rs1.getString("email"));
            tEMAIL.setBounds(320, 135+s, 230, 30);
            tEMAIL.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(tEMAIL);


            tPHONE = new JTextField(Long.toString(rs1.getLong("phone")));
            tPHONE.setBounds(320, 165+s, 230, 30);
            tPHONE.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(tPHONE);

            
            b=rs1.getBlob("picture");
            FileOutputStream fos=new FileOutputStream("sm1cpy.png");
            byte arr[]=b.getBytes(1, (int)b.length());
            fos.write(arr);
            fos.close();
            
            i1=new ImageIcon("sm1cpy.png");
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
            tDOB.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            jp.add(tDOB);
            tEMAIL = new JTextField("");
            tEMAIL.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            tEMAIL.setBounds(320, 170, 230, 30);
            jp.add(tEMAIL);

            tPHONE = new JTextField("");
            tPHONE.setFont(new Font("Segoe Print", Font.ITALIC, 18));
            tPHONE.setBounds(320, 200, 230, 30);
            jp.add(tPHONE);
            
            img = new JButton(new ImageIcon("no.png"));
            img.setBounds(15, 87, 108, 110);
            jp.add(img);

            System.out.println(e1);
        }

       //*************left panel*************
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
                   
                   browse.setVisible(false);
                   save.setVisible(false);
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
                       
                   }
                   if (rs2.getInt("empid") == empid) {
                       browse.setVisible(true);
                       save.setVisible(true);
                   }
               }
               catch(Exception e1)
               {
                   System.err.println(e1);
               }
           }
       });
       
              
           JScrollPane scrollPane = new JScrollPane(jp2);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, 300, 500);
        add(scrollPane); 
       
       setVisible(true);
   }
    public void actionPerformed(ActionEvent e)
       {
           JButton btn=(JButton)e.getSource();
           
          
           try
           {
                  String sql="select * from users order by empid asc";    
                    pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    rs3 = pst.executeQuery();
                    
           browse.setVisible(false);
           save.setVisible(false);
           
           while(rs3.next())
           {
               String str1=rs3.getString("name");
               String str2=tNAME.getText();
               if(str1.equals(str2))
               {
                   System.out.println("found");
                   break;
                   
               }
           }
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
           
           if(rs3.getInt("empid")== empid)
           {
               browse.setVisible(true);
               save.setVisible(true);
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
        
        new test2(101);
    }
}
