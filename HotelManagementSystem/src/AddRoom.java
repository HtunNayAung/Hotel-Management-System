import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

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
import javax.swing.JTextField;

public class AddRoom extends JFrame implements ActionListener{
    JTextField roomNumField, priceField;
    JComboBox availCBox, cleanCBox, roomTypeCBox;
    JButton addRoomButton, cancelButton;
    boolean isAdmin = false;
    public AddRoom(boolean isAdmin){
        this.isAdmin = isAdmin;
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/dashboard1.png"));
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

        JPanel panel = new JPanel();
        panel.setLocation(400, 100);
        panel.setSize(490, 400);
        panel.setLayout(null);


        //room number
        JLabel roomNumLabel = new JLabel("Room Number");
        roomNumLabel.setLocation(65, 30);
        roomNumLabel.setSize(120, 30);
        roomNumLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(roomNumLabel);

        roomNumField = new JTextField();
        roomNumField.setColumns(100);
        roomNumField.setLocation(200, 30);
        roomNumField.setSize(200, 30);
        panel.add(roomNumField);

        //availability
        JLabel availLabel = new JLabel("Availablity");
        availLabel.setLocation(65, 80);
        availLabel.setSize(120, 30);
        availLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(availLabel);

        String[] availablityOptions = {"Available", "Occupied"};
        availCBox = new JComboBox<String>(availablityOptions);
        availCBox.setLocation(200, 80);
        availCBox.setSize(180, 30);
        panel.add(availCBox);

        //clean
        JLabel cleanLabel = new JLabel("Cleaning");
        cleanLabel.setLocation(65, 130);
        cleanLabel.setSize(120, 30);
        cleanLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(cleanLabel);

        String[] cleanOptions = {"Clean", "Dirty"};
        cleanCBox = new JComboBox<String>(cleanOptions);
        cleanCBox.setLocation(200, 130);
        cleanCBox.setSize(180, 30);
        panel.add(cleanCBox);

        //price
        JLabel priceLabel = new JLabel("Price");
        priceLabel.setLocation(65, 180);
        priceLabel.setSize(120, 30);
        priceLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(priceLabel);

        priceField = new JTextField();
        priceField.setColumns(100);
        priceField.setLocation(200, 180);
        priceField.setSize(200, 30);
        panel.add(priceField);

        //bed type
        JLabel roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setLocation(65, 230);
        roomTypeLabel.setSize(120, 30);
        roomTypeLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(roomTypeLabel);

        String[] roomOptions = {"Single", "Double", "Deluxe", "Twin", "Suite"};
        roomTypeCBox = new JComboBox<String>(roomOptions);
        roomTypeCBox.setLocation(200, 230);
        roomTypeCBox.setSize(180, 30);
        panel.add(roomTypeCBox);

        //for add room button
        addRoomButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Add room</span></html>");
        addRoomButton.setLocation(100,290);
        addRoomButton.setSize(120,30);
        addRoomButton.setOpaque(true);
        addRoomButton.setBorderPainted(false);
        addRoomButton.setBackground(Color.decode("#424554"));
        addRoomButton.setForeground(Color.WHITE);
        addRoomButton.addActionListener(this);
        panel.add(addRoomButton);

        //for cancel button
        cancelButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Cancel</span></html>");
        cancelButton.setLocation(250,290);
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
        if(e.getActionCommand().equals("Add Employees")){
            dispose();
            new AddEmployee(isAdmin);
        } else if(e.getActionCommand().equals("Add Rooms")){
            dispose();
            new AddRoom(isAdmin);
        } else if(e.getActionCommand().equals("Employees")){
            dispose();
            new Employees(isAdmin);
        } else if (e.getActionCommand().equals("Departments")){
            dispose();
            new Departments(isAdmin);
        }  else if(e.getActionCommand().equals("    Reception    ")){
            dispose();
            new Reception(isAdmin);
        } else if(e.getSource() == addRoomButton){
            String roomNum = roomNumField.getText();
            String availability = (String)availCBox.getSelectedItem();
            String cleaning = (String)cleanCBox.getSelectedItem();
            String type = (String)roomTypeCBox.getSelectedItem();

            double price = 0;
            if(!priceField.getText().equals("")){
            try {
                price = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid salary value: " + priceField.getText());
            }

            if(roomNum != "" && price != 0 ){
                String query = "insert into room values ('" + roomNum + "','" + availability + "','" + cleaning + "','" + price + "','" + type + "');";
                Dbconnect dbconnect = new Dbconnect();
                try{
                    Statement stmt = dbconnect.con.createStatement();
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Room added successfully");
                    new AddRoom(isAdmin);
                    dbconnect.con.close();
                } catch (SQLIntegrityConstraintViolationException err){
                    JOptionPane.showMessageDialog(null, "Duplicate entry for room number");
                }  catch(Exception err){
                    System.err.println(err);
                }
                
            }
        }

        } else if(e.getSource() == cancelButton){
            new Dashboard(isAdmin);
        }

        
    }
}   
