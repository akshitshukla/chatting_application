package com.akshit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.DataInputStream;
import java.net.*;
import java.io.*;


public class Server extends JFrame implements ActionListener {
    JPanel p1;
    JTextField tf1;
    JButton b1;
    static JTextArea ta1;

    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

//    Timer timer;
//    Boolean typing = false;

    Server() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 140, 110));
        p1.setBounds(0, 0, 350, 70);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/akshit/icon/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 25, 25, 25);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("com/akshit/icon/paa.png"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40, 5, 60, 60);
        p1.add(l2);

        JLabel l3 = new JLabel("Akshita");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 90, 18);
        p1.add(l3);

        JLabel l4 = new JLabel("online");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 38, 100, 14);
        p1.add(l4);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("com/akshit/icon/video.png"));
        Image i8 = i7.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(230,19,25,25);
        p1.add(l5);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("com/akshit/icon/R (4).png"));
        Image i11 = i10.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l6 = new JLabel(i12);
        //l6.setForeground(Color.WHITE);
        l6.setBounds(270,19,25,25);
        p1.add(l6);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("com/akshit/icon/R (8).png"));
        Image i14 = i13.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel l7 = new JLabel(i15);
        l7.setBounds(300,12,45,45);
        p1.add(l7);

        ta1 = new JTextArea();
        ta1.setBounds(5, 75, 340, 470);
        ta1.setBackground(Color.WHITE);
        ta1.setFont(new Font("SAN_SARIF", Font.PLAIN, 14));
        ta1.setEditable(false);
        ta1.setLineWrap(true);
        ta1.setWrapStyleWord(true);
        add(ta1);

        tf1 = new JTextField();
        tf1.setBounds(5, 550, 250, 40);
        tf1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(tf1);

//        timer = new Timer(1, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(!typing){
//                    l4.setText("online");
//                }
//            }
//        });
//        timer.setInitialDelay(1500);
//
//
//        tf1.setBounds(5, 550, 250, 40);
//        tf1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
//        add(tf1);
//
//        tf1.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                l4.setText("typing...");
//                timer.start();
//                typing=true;
//            }
//            public void keyReleased(KeyEvent e){
//                typing = false;
//
//                if(!timer.isRunning())
//                {
//                    timer.restart();
//                }
//            }
//        });

        b1 = new JButton("send");
        b1.setBounds(257, 550, 88, 40);
        b1.setBackground(new Color(7, 140, 110));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        add(b1);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(350, 600);
        setLocation(200, 100);
        setUndecorated(true);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String out = tf1.getText();
            ta1.setText(ta1.getText() + "\n\t\t\t" + out);
            dout.writeUTF(out);
            tf1.setText("");
        } catch (Exception ae) {
            System.out.println("wrong on server");
        }
    }

    public static void main(String args[]) {
        new Server().setVisible(true);

        String msginput = "";
        try {
            while (true) {
                skt = new ServerSocket(6001);
                s = skt.accept();
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                while(true) {
                    msginput = din.readUTF();
                    ta1.setText(ta1.getText() + "\n" + msginput);
                    skt.close();
                }
            }
        } catch (Exception e) {

        }

    }


}