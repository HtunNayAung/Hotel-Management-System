import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard extends JFrame implements ActionListener{
    boolean isAdmin = false;
        public Dashboard(boolean isAdmin){
        this.isAdmin = isAdmin;
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/dashboard1.png"));
        JLabel background= new JLabel(backgroundImg);
        add(background);

        JLabel welcome = new JLabel("Welcome to Imperial");
        welcome.setLocation(420,70);
        welcome.setSize(500,100);
        welcome.setFont(new Font("serif", Font.BOLD, 50));
        welcome.setForeground(Color.decode("#32343d"));
        background.add(welcome);

        JLabel clock = new JLabel();
        clock.setLocation(930,530);
        clock.setSize(500,100);
        clock.setFont(new Font("serif", Font.BOLD, 50));
        clock.setForeground(Color.decode("#000000"));
        background.add(clock);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the current time
                Date currentTime = new Date();
                
                // Format the time as HH:mm:ss
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
                String formattedTime = dateFormat.format(currentTime);
                
                // Update the JLabel with the formatted time
                clock.setText(formattedTime);
            }
        });
        timer.start();

        JLabel date = new JLabel();
        date.setLocation(960,600);
        date.setSize(500,100);
        date.setFont(new Font("serif", Font.BOLD, 50));
        date.setForeground(Color.decode("#00000"));
        background.add(date);

        Timer timer2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the current time
                Date currentTime = new Date();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String formattedTime = dateFormat.format(currentTime);
                
                // Update the JLabel with the formatted time
                date.setText(formattedTime);
            }
        });
        timer2.start();
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.setLocation(0, 0);
        menuBar.setSize(1280, 35);
        background.add(menuBar);

        JMenu hotelMng =  new JMenu("Hotel Management");
        hotelMng.setForeground(Color.BLACK);
        menuBar.add(hotelMng);

        JMenuItem reception = new JMenuItem("    Reception    ");
        reception.addActionListener(this);
        hotelMng.add(reception);

        JMenuItem logout = new JMenuItem("    Log out");
        logout.addActionListener(this);
        hotelMng.add(logout);
        if(isAdmin){
            JMenu admin =  new JMenu("          Admin          ");
            admin.setForeground(Color.BLACK);
            menuBar.add(admin);

            JMenuItem employees = new JMenuItem("Add Employees");
            employees.addActionListener(this);
            admin.add(employees);

            JMenuItem rooms = new JMenuItem("Add Rooms");
            rooms.addActionListener(this);
            admin.add(rooms);

            JMenuItem showemployees = new JMenuItem("Employees");
            showemployees.addActionListener(this);
            admin.add(showemployees);

            JMenuItem showDepts = new JMenuItem("Departments");
            showDepts.addActionListener(this);
            admin.add(showDepts);
        }

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Employees")){
            dispose();
            new AddEmployee(isAdmin);
        } else if(e.getActionCommand().equals("Add Rooms")){
            dispose();
            new AddRoom(isAdmin);
        }  else if(e.getActionCommand().equals("Employees")){
            dispose();
            new Employees(isAdmin);
        } else if(e.getActionCommand().equals("    Reception    ")){
            dispose();
            new Reception(isAdmin);
        } else if(e.getActionCommand().equals("    Log out")){
            dispose();
            new Login();
        }
    }
}
