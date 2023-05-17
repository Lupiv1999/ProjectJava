package quiz.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Login extends JFrame implements ActionListener {

    JButton rules, back, admin, back2;
     public JTextField tfname, reg;
    JPanel adm, box;
    JLabel t2[][] = new JLabel[100][3];
    JLabel name;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);

        JLabel heading = new JLabel("LPU NEST");
        heading.setBounds(750, 60, 500, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

         name = new JLabel("Enter your name");
        name.setBounds(735, 150, 300, 20);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        name.setForeground(new Color(30, 144, 254));
        add(name);
        
        {
            JLabel regs = new JLabel("Enter your Registrtion ID");
            regs.setBounds(735, 210, 300, 20);
            regs.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
            regs.setForeground(new Color(30, 144, 254));
            add(regs);

            tfname = new JTextField();
            tfname.setBounds(735, 180, 300, 25);
            tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
            add(tfname);

            reg = new JTextField();
            reg.setBounds(735, 240, 300, 25);
            reg.setFont(new Font("Times New Roman", Font.BOLD, 20));
            add(reg);

            rules = new JButton("Enter");
            rules.setBounds(735, 270, 120, 25);
            rules.setBackground(new Color(30, 144, 254));
            rules.setForeground(Color.WHITE);
            rules.addActionListener(this);
            add(rules);

            back = new JButton("Exit");
            back.setBounds(915, 270, 120, 25);
            back.setBackground(new Color(30, 144, 254));
            back.setForeground(Color.WHITE);
            back.addActionListener(this);
            add(back);
            

            admin = new JButton("Login as Admin");
            admin.setBounds(735, 310, 300, 25);
            admin.setBackground(new Color(190, 44, 54));
            admin.setForeground(Color.WHITE);
            admin.addActionListener(this);
            add(admin);

            setSize(1100, 500);
            setLocation(150, 150);
            setVisible(true);
        }

        // Admin panel...............................................................

        adm = new JPanel(null);
        box = new JPanel(null);
        adm.setBackground(new Color(30, 144, 254));
        adm.setBounds(0, 0, 1200, 500);
        box.setBounds(400, 50, 400, 400);
        box.setBackground(Color.white);
        adm.add(box);

        JLabel l = new JLabel("Name                -              Reg. ID                 -                Score");
        l.setBounds(25, 0, 400, 20);
        l.setBackground(Color.WHITE);
        l.setForeground(new Color(230, 44, 54));
        box.add(l);

        JLabel l0 = new JLabel("User Data");
        l0.setBounds(500, 10, 400, 40);
        // l.setBackground(Color.WHITE);
        l0.setForeground(Color.white);
        l0.setFont(new Font("Mongolian Baiti", Font.BOLD, 38));

        adm.add(l0);

        back2 = new JButton("Back");
        back2.setBounds(10, 10, 120, 25);
        back2.setBackground(Color.WHITE);
        back2.setForeground(new Color(30, 144, 254));
        back2.addActionListener(this);
        adm.add(back2);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {

            String name = tfname.getText();
            String id = reg.getText();
            setVisible(false);
            new Rules(name, id);

        } else if (ae.getSource() == back) {
            setVisible(false);
        }
        if (ae.getSource() == back2) {
            setVisible(false);
            new Login();
        }
        if (ae.getSource() == admin) {

            try {
                String a1 = tfname.getText();
                String a2 = reg.getText();
                String url = "jdbc:mysql://localhost:3306/quiz";

                String username = "root";
                String password = "";
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                Connection con = DriverManager.getConnection(url, username, password);
                Statement st = con.createStatement();
                String sq = "select * from admin";
                String sq2 = "select * from data";
                ResultSet rs = st.executeQuery(sq);

                while (rs.next()) {
                    String n = rs.getString(1);
                    String ps = rs.getString(2);

                    if (n.equals(a1) && ps.equals(a2)) {
                        ResultSet ds = st.executeQuery(sq2);
                        setContentPane(adm);
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
                                // t2[row][y].setFont(fn);
                                t2[row][y].setForeground(Color.black);

                                box.add(t2[row][y]);
                                xx = xx + 145;
                            }
                            yy = yy + 15;
                        }
                    } else {
                        JLabel info = new JLabel("Admin not found...");
                        Font tf = new Font(Font.DIALOG, Font.BOLD, 25);
                        info.setFont(tf);
                        info.setForeground(Color.red);
                        add(info);
                        info.setBounds(740, 335, 300, 30);
                        tfname.setText("");
                        reg.setText("");
                    }
                }
                con.close();
            }

            catch (Exception ex) {
                System.out.println(ex);
            }

        }

    }

    public static void main(String[] args) {

        {
            try {
                String url = "jdbc:mysql://localhost:3306/quiz";
                String username = "root";
                String password = "";
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, username, password);

                new Login();

                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}