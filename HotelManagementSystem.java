import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HotelManagementSystem extends JFrame implements ActionListener{
    public HotelManagementSystem(){
        //for main window
        setSize(1280, 720);
        setLocation(100,100);
        // setLayout(null);

        //add background image
        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/background.jpeg"));
        JLabel background= new JLabel(backgroundImg);
        add(background);

        //App label
        JLabel appLabel = new JLabel("HOTEL MANAGEMENT SYSTEM");
        appLabel.setSize(1000,500);
        appLabel.setLocation(50, 370);;
        appLabel.setForeground(Color.BLACK);
        appLabel.setFont(new Font("serif", Font.ROMAN_BASELINE, 40));
        background.add(appLabel);

        //next Button
        JButton nextButton = new JButton("To Login");
        nextButton.setSize(150,70);
        nextButton.setLocation(1050, 585);
        nextButton.setForeground(Color.BLACK);
        nextButton.setFont(new Font("serif", Font.PLAIN, 25));
        nextButton.setOpaque(true);
        nextButton.setBorderPainted(false);
        nextButton.setBackground(Color.decode("#B8A0A3"));
        nextButton.addActionListener(this);
        background.add(nextButton);
    

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Login();
    }

}