package quiz.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;

public class PvScore extends JFrame implements ActionListener {
    JButton Check,Exit;
    JPanel box ;
    public JTextField tfname, reg;
    PreparedStatement pstmt;
   
    JLabel t2[][] = new JLabel[100][3];
    PvScore(){
        setBounds(200, 150, 1100, 550);
        getContentPane().setBackground(new Color(	144, 238, 144));
        setLayout(null);
        setVisible(true);
         box = new JPanel(null);
         tfname = new JTextField();
         tfname.setBounds(10, 150, 300, 25);
         tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
         add(tfname);
         JLabel name = new JLabel("Enter your name");
         name.setBounds(10, 130, 300, 20);
         name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
         name.setForeground(Color.RED);
         add(name);
         JLabel name1 = new JLabel("Enter your Registration id");
         name1.setBounds(10, 180, 300, 20);
         name1.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
         name1.setForeground(Color.RED);
         add(name1);


         reg = new JTextField();
         reg.setBounds(10,200, 300, 25);
         reg.setFont(new Font("Times New Roman", Font.BOLD, 20));
         add(reg);

      
        box.setBounds(340, 50, 400, 400);
        box.setBackground(Color.white);
        add(box);
        JLabel l = new JLabel("Name                -              Reg. ID                 -                Score");
        l.setBounds(25, 0, 400, 20);
        l.setBackground(Color.WHITE);
        l.setForeground(new Color(230, 44, 54));
        box.add(l);
        Check = new JButton("Check", null);
        Check.setBounds(50,250,100,50);
        Check.setBackground(Color.blue);
        Check.setForeground(Color.WHITE);
        Check.addActionListener(this);
        Exit = new JButton("Exit", null);
        Exit.setBounds(160,250,100,50);
        Exit.setBackground(Color.RED);
        Exit.setForeground(Color.WHITE);
        Exit.addActionListener(this);
        add(Exit);
        

       add(Check);

    }
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == Check){
        String a1 = tfname.getText();
                String a2 = reg.getText();
       try{ String url = "jdbc:mysql://localhost:3306/quiz";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
                Statement st = con.createStatement();
                
                String sq2 = "select * from data";
            
                pstmt=con.prepareStatement("select * from data where name = ? and id = ?");
                pstmt.setString(1,a1);
                pstmt.setString(2,a2);

                ResultSet rs = st.executeQuery(sq2);
                while (rs.next()) {
                    String n = rs.getString(1);
                    String ps = rs.getString(2);
                    if (n.equals(a1) && ps.equals(a2))
                     {
                       ResultSet ds= pstmt.executeQuery();
                        
                         repaint();
                        revalidate();
                   
                    
                    int row = 0;
                    int yy = 20;
                    while (ds.next()) {
                        int xx = 25;
                        for (int y = 0; y < 3; y++) {
                            String sd = ds.getString(y + 1);

                            t2[row][y] = new JLabel(sd);
                            t2[row][y].setBounds(xx, yy, 145, 20);
                           
                            t2[row][y].setForeground(Color.black);

                            box.add(t2[row][y]);
                            xx = xx + 145;
                        }
                        yy = yy + 15;
                    }
               }
                
                //  else
                //  {
                //     JLabel info = new JLabel("User not found...");
                //     Font tf = new Font(Font.DIALOG, Font.BOLD, 25);
                //     info.setFont(tf);
                //     info.setForeground(Color.red);
                //     add(info);
                //     info.setBounds(10, 180, 300, 30);
                //     tfname.setText("");
                //     reg.setText("");
                // }
            }
        }
    catch (Exception ex) {
        System.out.println(ex);
    }}
    if(ae.getSource() == Exit){
        setVisible(false);
    }

        
            
    }
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/quiz";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            new PvScore();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }}