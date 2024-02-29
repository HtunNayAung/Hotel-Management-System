import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
    JTextField nameField;
    JPasswordField pwdField;
    JButton loginButton, cancelButton;
    boolean isAdmin = false;
    public Login(){
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

        nameField =  new JTextField();
        nameField.setLocation(220,40);
        nameField.setSize(150,30);
        panel.add(nameField);

        //for password
        JLabel pwdLabel = new JLabel("Password");
        pwdLabel.setLocation(120,90);
        pwdLabel.setSize(100,30);
        pwdLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(pwdLabel);

        pwdField =  new JPasswordField();
        pwdField.setLocation(220,90);
        pwdField.setSize(150,30);
        panel.add(pwdField);

        //for login button
        loginButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Login</span></html>");
        loginButton.setLocation(100,170);
        loginButton.setSize(120,30);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(Color.decode("#424554"));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        //for cancel button
        cancelButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Cancel</span></html>");
        cancelButton.setLocation(250,170);
        cancelButton.setSize(120,30);
        cancelButton.setOpaque(true);
        cancelButton.setBorderPainted(false);
        cancelButton.setBackground(Color.decode("#424554"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);


        background.add(panel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton ){
            String username = nameField.getText();
            @SuppressWarnings("deprecation")
            String password = pwdField.getText();
            Dbconnect dbconnect = new Dbconnect();
            String query = "select * from login where username = '" + username + "' and password = '" + password + "';";
            try {
                Statement stmt = dbconnect.con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                if(resultSet.next()){
                    if(username.equals("admin")){
                        isAdmin = true;
                    }
                    dispose();
                    new Dashboard(isAdmin);
                } else{
                    JOptionPane.showMessageDialog(null, "Invalid password or username");
                    dispose();
                    new Login();
                }
                dbconnect.con.close();
                
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if(e.getSource() == cancelButton){
            dispose();
            new HotelManagementSystem();
        }
    }
}