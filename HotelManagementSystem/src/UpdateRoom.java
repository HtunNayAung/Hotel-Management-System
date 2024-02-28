import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UpdateRoom extends JFrame implements ActionListener, ItemListener {
    JPanel panel;
    JLabel roomNumberLabel,availLabel, cleanLabel;
    Choice roomNumberBox;
    JCheckBox availabilityCBox, cleaningCBox;
    JComboBox availCBox, cleanCBox;
    JButton updateButton, backButton;
    boolean availChecked = false;
    boolean cleanChecked = false;
    public UpdateRoom(){
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/dashboard.jpeg"));
        JLabel background = new JLabel(backgroundImg);
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

        panel = new JPanel();
        panel.setLocation(400, 100);
        panel.setSize(490, 480);
        panel.setLayout(null);

        roomNumberLabel = new JLabel("Room Number");
        roomNumberLabel.setLocation(75, 30);
        roomNumberLabel.setSize(120, 30);
        roomNumberLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(roomNumberLabel);

        roomNumberBox = new Choice();
        try {   
            Dbconnect dbconnect = new Dbconnect();
            Statement stmt = dbconnect.con.createStatement();
            String query = "select * from room;";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                roomNumberBox.add(resultSet.getString("roomNumber"));
            }
            dbconnect.con.close();
        } catch (Exception e) {
            
        }
        roomNumberBox.setLocation(200, 30);
        roomNumberBox.setSize(200, 30);
        panel.add(roomNumberBox);

        //for checkout button
        updateButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Update</span></html>");
        updateButton.setLocation(100,420);
        updateButton.setSize(120,30);
        updateButton.setOpaque(true);
        updateButton.setBorderPainted(false);
        updateButton.setBackground(Color.decode("#424554"));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        panel.add(updateButton);

        //for back button
        backButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Back</span></html>");
        backButton.setLocation(250,420);
        backButton.setSize(120,30);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.decode("#424554"));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        panel.add(backButton);

        //availability
        availLabel = new JLabel("Availablity");
        availLabel.setLocation(75, 130);
        availLabel.setSize(120, 30);
        availLabel.setFont(new Font("serif", Font.PLAIN, 19));

        String[] availablityOptions = {"Available", "Occupied"};
        availCBox = new JComboBox<String>(availablityOptions);
        availCBox.setLocation(200, 130);
        availCBox.setSize(200, 30);


        //clean label
        cleanLabel = new JLabel("Cleaning status");
        cleanLabel.setSize(120, 30);
        cleanLabel.setFont(new Font("serif", Font.PLAIN, 19));

        //clean options
        String[] cleaningOptions = {"Clean", "Dirty"};
        cleanCBox = new JComboBox<String>(cleaningOptions);
        cleanCBox.setSize(180, 30);



        availabilityCBox = new JCheckBox("change availablity");
        availabilityCBox.setFont(new Font("serif", Font.PLAIN, 19));
        availabilityCBox.setLocation(55, 80);
        availabilityCBox.setSize(180, 30);
        availabilityCBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    availChecked = true;
                    if(availChecked && cleanChecked){
                        System.out.println("hi");
                        cleanLabel.setLocation(75, 180);
                        cleanCBox.setLocation(200, 180);
                        panel.revalidate();
                        panel.repaint();
                    }
                    panel.add(availLabel);
                    panel.add(availCBox);
                } else{
                    availChecked = false;
                    if(!availChecked && cleanChecked){
                        cleanLabel.setLocation(75, 130);
                        cleanCBox.setLocation(200, 130);
                        panel.revalidate();
                        panel.repaint();
                    }
                    panel.remove(availLabel);
                    panel.remove(availCBox);
                }
                

                panel.revalidate();
                panel.repaint();
            }
            
        });
        panel.add(availabilityCBox);


        cleaningCBox = new JCheckBox("change cleaning status");
        cleaningCBox.setFont(new Font("serif", Font.PLAIN, 19));
        cleaningCBox.setLocation(230, 80);
        cleaningCBox.setSize(220, 30);
        cleaningCBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    cleanChecked = true;
                
                    if(!availChecked && cleanChecked){
                        cleanLabel.setLocation(75, 130);
                        cleanCBox.setLocation(200, 130);
                    } else if(availChecked && cleanChecked){
                        System.out.println("hi");
                        cleanLabel.setLocation(75, 180);
                        cleanCBox.setLocation(200, 180);
                        panel.revalidate();
                        panel.repaint();
                    }

                    panel.add(cleanLabel);
                    panel.add(cleanCBox);
                } else {
                    cleanChecked = false;
                    panel.remove(cleanLabel);
                    panel.remove(cleanCBox);
                }
                
                
                panel.revalidate();
                panel.repaint();
            }
            
        });
        panel.add(cleaningCBox);
        
        panel.revalidate();
        panel.repaint();
        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
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
        } else if(e.getSource() == backButton){
            setVisible(false);
            new Reception();
        } else if(e.getSource() == updateButton){
            try {   
                Dbconnect dbconnect = new Dbconnect();
                Statement stmt = dbconnect.con.createStatement();
                String query = "";
                if(!availChecked && cleanChecked){
                    query = "update room set cleaningStatus = '" + cleanCBox.getSelectedItem() + "' where roomNumber = '" + roomNumberBox.getSelectedItem() + "';";
                } else if(availChecked && !cleanChecked){
                    query = "update room set availability = '" + availCBox.getSelectedItem() + "' where roomNumber = '" + roomNumberBox.getSelectedItem() + "';";
                } else if(availChecked && cleanChecked){
                    query = "update room set availability = '" + availCBox.getSelectedItem() + "', cleaningStatus = '" + cleanCBox.getSelectedItem() + "' where roomNumber = '" + roomNumberBox.getSelectedItem() + "';";
                }
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully updated");
                setVisible(false);
                new UpdateRoom();
                dbconnect.con.close();
            } catch (Exception err) {
                
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }
    
}
