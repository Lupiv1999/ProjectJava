package quiz.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Score extends JFrame implements ActionListener {
    public JButton submit,PvScore;
    Score(String name, int score, String id) {

        String n = name;
        String i = id;
        int m = score;

        try {
            String url = "jdbc:mysql://localhost:3306/quiz";
           
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            String in = "insert into  data values(?,?,?)";

            PreparedStatement ps = con.prepareStatement(in);

            String sc = ("" + m);
            ps.setString(1, n);
            ps.setString(2, i);
            ps.setString(3, sc);

            ps.execute();

            con.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }

        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 200, 300, 250);
        add(image);

        JLabel heading = new JLabel("Thankyou " + name + " for using Self Evaluation System");
        heading.setBounds(45, 30, 800, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(heading);

        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(lblscore);

         submit = new JButton("Play Again");
        submit.setBounds(350, 250, 120, 30);
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

         PvScore = new JButton("Check previous scores");
        PvScore.setBounds(490, 250, 200, 30);
        PvScore.setBackground(Color.red);
        PvScore.setForeground(Color.WHITE);
        PvScore.addActionListener(this);
        add(PvScore);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit){
        setVisible(false);
        new Login();}
        if(ae.getSource() == PvScore){
            setVisible(false);
        new PvScore();}
        }
       
        public static void main(String[] args) {
            new Score("User", 0, "id");
        }
    }
    

    

    




   