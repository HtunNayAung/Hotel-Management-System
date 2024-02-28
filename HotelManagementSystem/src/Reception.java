import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Reception extends JFrame implements ActionListener{
    JButton newCustomerButton, roomButton, departmentButton, employeesButton, customerButton, checkoutButton, updateStatusButton, roomStatusButton, pickupButton, searchRoomButton, logoutButton;
    public Reception(){
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

        JMenuItem drivers = new JMenuItem("Add Drivers");
        drivers.addActionListener(this);
        admin.add(drivers);

        JPanel panel = new JPanel();
        panel.setLocation(250, 100);
        panel.setSize(800, 530);
        panel.setLayout(null);

        newCustomerButton = new JButton("New Customer");
        newCustomerButton.setLocation(10,50);
        newCustomerButton.setSize(200,30);
        newCustomerButton.setOpaque(true);
        newCustomerButton.setBorderPainted(false);
        newCustomerButton.setBackground(Color.decode("#424554"));
        newCustomerButton.setForeground(Color.WHITE);
        newCustomerButton.addActionListener(this);
        panel.add(newCustomerButton);

        roomButton = new JButton("All Rooms");
        roomButton.setLocation(10,90);
        roomButton.setSize(200,30);
        roomButton.setOpaque(true);
        roomButton.setBorderPainted(false);
        roomButton.setBackground(Color.decode("#424554"));
        roomButton.setForeground(Color.WHITE);
        roomButton.addActionListener(this);
        panel.add(roomButton);

        // departmentButton = new JButton("Departments");
        // departmentButton.setLocation(10,110);
        // departmentButton.setSize(200,30);
        // departmentButton.setOpaque(true);
        // departmentButton.setBorderPainted(false);
        // departmentButton.setBackground(Color.decode("#424554"));
        // departmentButton.setForeground(Color.WHITE);
        // departmentButton.addActionListener(this);
        // panel.add(departmentButton);

        // employeesButton = new JButton("Employees");
        // employeesButton.setLocation(10,150);
        // employeesButton.setSize(200,30);
        // employeesButton.setOpaque(true);
        // employeesButton.setBorderPainted(false);
        // employeesButton.setBackground(Color.decode("#424554"));
        // employeesButton.setForeground(Color.WHITE);
        // employeesButton.addActionListener(this);
        // panel.add(employeesButton);

        customerButton = new JButton("Customer Info");
        customerButton.setLocation(10,130);
        customerButton.setSize(200,30);
        customerButton.setOpaque(true);
        customerButton.setBorderPainted(false);
        customerButton.setBackground(Color.decode("#424554"));
        customerButton.setForeground(Color.WHITE);
        customerButton.addActionListener(this);
        panel.add(customerButton);

        checkoutButton = new JButton("Checkout");
        checkoutButton.setLocation(10,170);
        checkoutButton.setSize(200,30);
        checkoutButton.setOpaque(true);
        checkoutButton.setBorderPainted(false);
        checkoutButton.setBackground(Color.decode("#424554"));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(this);
        panel.add(checkoutButton);

        updateStatusButton = new JButton("Update Availability");
        updateStatusButton.setLocation(10,210);
        updateStatusButton.setSize(200,30);
        updateStatusButton.setOpaque(true);
        updateStatusButton.setBorderPainted(false);
        updateStatusButton.setBackground(Color.decode("#424554"));
        updateStatusButton.setForeground(Color.WHITE);
        panel.add(updateStatusButton);

        roomStatusButton = new JButton("Update Room Status");
        roomStatusButton.setLocation(10,250);
        roomStatusButton.setSize(200,30);
        roomStatusButton.setOpaque(true);
        roomStatusButton.setBorderPainted(false);
        roomStatusButton.setBackground(Color.decode("#424554"));
        roomStatusButton.setForeground(Color.WHITE);
        panel.add(roomStatusButton);

        pickupButton = new JButton("Pickup Service");
        pickupButton.setLocation(10,290);
        pickupButton.setSize(200,30);
        pickupButton.setOpaque(true);
        pickupButton.setBorderPainted(false);
        pickupButton.setBackground(Color.decode("#424554"));
        pickupButton.setForeground(Color.WHITE);
        panel.add(pickupButton);

        searchRoomButton = new JButton("Search Room");
        searchRoomButton.setLocation(10,330);
        searchRoomButton.setSize(200,30);
        searchRoomButton.setOpaque(true);
        searchRoomButton.setBorderPainted(false);
        searchRoomButton.setBackground(Color.decode("#424554"));
        searchRoomButton.setForeground(Color.WHITE);
        searchRoomButton.addActionListener(this);
        panel.add(searchRoomButton);

        logoutButton =  new JButton("Log out");
        logoutButton.setLocation(10,450);
        logoutButton.setSize(200,30);
        logoutButton.setOpaque(true);
        logoutButton.setBorderPainted(false);
        logoutButton.setBackground(Color.decode("#424554"));
        logoutButton.setForeground(Color.WHITE);
        panel.add(logoutButton);


        ImageIcon receptionImage = new ImageIcon(ClassLoader.getSystemResource("images/reception.jpeg"));
        JLabel image = new JLabel(receptionImage);
        image.setLocation(250, 30);
        image.setSize(500, 470);
        panel.add(image);

        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // public static void main(String[] args) {
    //     new Reception();
    // }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Add Employees")){
            setVisible(false);
            new AddEmployee();
        } else if(e.getActionCommand().equals("Add Rooms")){
            setVisible(false);
            new AddRoom();
        }  else if(e.getActionCommand().equals("Add Drivers")){
            setVisible(false);
            new PickUpService();
        } else if(e.getActionCommand().equals("    Reception    ")){
            setVisible(false);
            new Reception();
        } else if(e.getSource() == newCustomerButton){
            setVisible(false);
            new AddCustomer();
        } else if(e.getSource() == roomButton){
            setVisible(false);
            new AllRooms();
        } else if(e.getSource() == customerButton){
            setVisible(false);
            new Customers();
        } else if(e.getSource() == checkoutButton){
            setVisible(false);
            new Checkout();
        } else if(e.getSource() == searchRoomButton){
            setVisible(false);
            new SearchRoom();
        }
    }
}
