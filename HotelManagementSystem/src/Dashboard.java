import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.Font;

public class Dashboard extends JFrame{
    public Dashboard(){
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/dashboard.jpeg"));
        JLabel background= new JLabel(backgroundImg);
        add(background);

        JLabel welcome = new JLabel("Welcome to Imperial");
        welcome.setLocation(420,70);
        welcome.setSize(500,100);
        welcome.setFont(new Font("serif", Font.BOLD, 50));
        welcome.setForeground(Color.decode("#32343d"));
        background.add(welcome);
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.setLocation(0, 0);
        menuBar.setSize(1280, 35);
        background.add(menuBar);

        JMenu hotelMng =  new JMenu("Hotel Management");
        hotelMng.setForeground(Color.BLACK);
        menuBar.add(hotelMng);

        JMenuItem reception = new JMenuItem("    Reception    ");
        hotelMng.add(reception);

        JMenu admin =  new JMenu("    Admin          ");
        admin.setForeground(Color.BLACK);
        menuBar.add(admin);

        JMenuItem employees = new JMenuItem("Employees");
        admin.add(employees);

        JMenuItem drivers = new JMenuItem("Drivers");
        admin.add(drivers);

        JMenuItem rooms = new JMenuItem("Rooms");
        admin.add(rooms);


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

    public static void main(String[] args) {
        new Dashboard();
    }
}
