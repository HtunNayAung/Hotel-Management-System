import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchRoom extends JFrame implements ActionListener{
    JTable table;
    JButton backButton, searchButton;
    JComboBox roomTypeCBox;
    JCheckBox availabilityBox;
    JTextField floorField;
    JPanel panel;
    public SearchRoom(){
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

        panel = new JPanel();
        panel.setLocation(240, 100);
        panel.setSize(800, 500);
        panel.setLayout(null);

        //bed type
        JLabel roomTypeLabel = new JLabel("By Room Type");
        roomTypeLabel.setLocation(65, 30);
        roomTypeLabel.setSize(150, 30);
        roomTypeLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(roomTypeLabel);

        String[] roomOptions = {"Single", "Double", "Deluxe", "Twin", "Suite"};
        roomTypeCBox = new JComboBox<String>(roomOptions);
        roomTypeCBox.setLocation(200, 30);
        roomTypeCBox.setSize(180, 30);
        panel.add(roomTypeCBox);

        availabilityBox = new JCheckBox("Display only available");
        availabilityBox.setLocation(450, 30);
        availabilityBox.setSize(300, 30);
        availabilityBox.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(availabilityBox);

        
        JLabel floorLabel = new JLabel("By Floor");
        floorLabel.setLocation(65, 80);
        floorLabel.setSize(120, 30);
        floorLabel.setFont(new Font("serif", Font.PLAIN, 19));
        panel.add(floorLabel);

        floorField = new JTextField();
        floorField.setColumns(100);
        floorField.setLocation(200, 80);
        floorField.setSize(200, 30);
        panel.add(floorField);

        //for search button
        searchButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Search</span></html>");
        searchButton.setLocation(450,80);
        searchButton.setSize(120,30);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setBackground(Color.decode("#424554"));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this);
        panel.add(searchButton);


        



        //for cancel button
        backButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Back</span></html>");
        backButton.setLocation(340,460);
        backButton.setSize(120,30);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.decode("#424554"));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        panel.add(backButton);

        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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
        } else if(e.getSource() == searchButton){
            String query = "";
            //create table model with column names
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("<html><h2>Room Number</h2></html>");
            model.addColumn("<html><h2>Availability</h2></html>");
            model.addColumn("<html><h2>Cleaning Status</h2></html>");
            model.addColumn("<html><h2>Price</h2></html>");
            model.addColumn("<html><h2>Room Type</h2></html>");
    
            try{
                if(!floorField.getText().equals("")){
                    query = "select * from room where roomType = '" + (String)roomTypeCBox.getSelectedItem() + "' and SUBSTRING(roomNumber, 1, 1) = '" + floorField.getText() + "'";
                    // JOptionPane.showMessageDialog(null, query);
                } else if(floorField.getText().equals("")){
                    query = "select * from room where roomType = '" + (String)roomTypeCBox.getSelectedItem() + "'";
                    // JOptionPane.showMessageDialog(null, query);

                }   

                if (availabilityBox.isSelected()) {
                    query = query + " AND availability = 'Available'";
                    // JOptionPane.showMessageDialog(null, query);

                }
                
                Dbconnect dbconnect = new Dbconnect();
                Statement stmt = dbconnect.con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                boolean resultSetEmpty = true;
                while (resultSet.next()) {
                    resultSetEmpty = false;
                    // Retrieve data from the ResultSet and add it to the table model
                    Vector<Object> row = new Vector<>();
                    row.add("<html><h3>" + resultSet.getString("roomNumber") + "</h3></html>");
                    row.add("<html><h3>" + resultSet.getString("availability") + "</h3></html>");
                    row.add("<html><h3>" + resultSet.getString("cleaningStatus") + "</h3></html>");
                    row.add("<html><h3>" + resultSet.getDouble("price") + "</h3></html>");
                    row.add("<html><h3>" + resultSet.getString("roomType") + "</h3></html>");
                    model.addRow(row);
                }

                // If the ResultSet is empty, add a default message to the table model
                if (resultSetEmpty) {
                    Vector<Object> defaultRow = new Vector<>();
                    defaultRow.add("<html><h3>No room available</h3></html>");
                    model.addRow(defaultRow);
                }
                dbconnect.con.close();
            } catch(Exception err){
                System.err.println(err);
            }
    
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setLocation(25,130);
            scrollPane.setSize(750,300);

            panel.add(scrollPane);
        } else if(e.getSource() == backButton){
            setVisible(false);
            new Reception();
        }
    }
}
