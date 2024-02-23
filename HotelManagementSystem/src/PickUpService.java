import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PickUpService extends JFrame implements ActionListener{
    JTextField driverNameField, customerNameField, locationNameField;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PickUpService(){
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/dashboard.jpeg"));
        JLabel background= new JLabel(backgroundImg);
        add(background);
        
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

        JMenu admin =  new JMenu("          Admin          ");
        admin.setForeground(Color.BLACK);
        menuBar.add(admin);

        JMenuItem employees = new JMenuItem("Add Employees");
        employees.addActionListener(this);
        admin.add(employees);

        JMenuItem rooms = new JMenuItem("Add Rooms");
        rooms.addActionListener(this);
        admin.add(rooms);

        JMenuItem pickup = new JMenuItem("Pickup service");
        pickup.addActionListener(this);
        admin.add(pickup);

        JPanel panel = new JPanel();
        panel.setLocation(400, 100);
        panel.setSize(490, 450);
        panel.setLayout(null);

        //driver name
        JLabel driverNameLabel =  new JLabel("Driver name");
        driverNameLabel.setLocation(65, 30);
        driverNameLabel.setSize(120, 30);
        driverNameLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(driverNameLabel);

        driverNameField = new JTextField();
        driverNameField.setColumns(100);
        driverNameField.setLocation(200, 30);
        driverNameField.setSize(200, 30);
        panel.add(driverNameField);

        //customer name
        JLabel customerNameLabel =  new JLabel("Customer name");
        customerNameLabel.setLocation(65, 80);
        customerNameLabel.setSize(120, 30);
        customerNameLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(customerNameLabel);

        customerNameField = new JTextField();
        customerNameField.setColumns(100);
        customerNameField.setLocation(200, 80);
        customerNameField.setSize(200, 30);
        panel.add(customerNameField);

        //pickup location
        JLabel locationLabel =  new JLabel("Pickup Location");
        locationLabel.setLocation(65, 130);
        locationLabel.setSize(120, 30);
        locationLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(locationLabel);

        locationNameField = new JTextField();
        locationNameField.setColumns(100);
        locationNameField.setLocation(200, 130);
        locationNameField.setSize(200, 30);
        panel.add(locationNameField);

        //pickup date
        

        

        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Employees")){
            new AddEmployee();
        } else if(e.getActionCommand().equals("Add Rooms")){
            new AddRoom();
        } else if(e.getActionCommand().equals("Pickup service")){
            new PickUpService();
        }
    }
}
