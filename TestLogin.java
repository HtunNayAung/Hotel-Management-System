import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestLogin extends JFrame{
    public TestLogin(){
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/background.jpeg"));
        JLabel background= new JLabel(backgroundImg);
        add(background);

        JPanel panel = new JPanel();
        panel.setLocation(380,150);
        panel.setSize(500,250);
        panel.setLayout(null);


        //for username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setLocation(120,40);
        usernameLabel.setSize(100,30);
        usernameLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(usernameLabel);

        JTextField nameField =  new JTextField();
        nameField.setLocation(220,40);
        nameField.setSize(150,30);
        panel.add(nameField);

        //for password
        JLabel pwdLabel = new JLabel("Password");
        pwdLabel.setLocation(120,90);
        pwdLabel.setSize(100,30);
        pwdLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(pwdLabel);

        JTextField pwdField =  new JTextField();
        pwdField.setLocation(220,90);
        pwdField.setSize(150,30);
        panel.add(pwdField);

        //for login button
        JButton loginButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Login</span></html>");
        loginButton.setLocation(100,170);
        loginButton.setSize(120,30);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(Color.decode("#424554"));
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);

        //for cancel button
        JButton cancelButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Cancel</span></html>");
        cancelButton.setLocation(250,170);
        cancelButton.setSize(120,30);
        cancelButton.setOpaque(true);
        cancelButton.setBorderPainted(false);
        cancelButton.setBackground(Color.decode("#424554"));
        cancelButton.setForeground(Color.WHITE);
        panel.add(cancelButton);


        background.add(panel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestLogin();
    }
}
