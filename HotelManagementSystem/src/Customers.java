import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Customers extends JFrame implements ActionListener{
    JTable table;
    JButton backButton;
    boolean isAdmin = false;
    public Customers(boolean isAdmin){
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
        panel.setLocation(240, 100);
        panel.setSize(800, 500);
        panel.setLayout(null);

        //create table model with column names
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("<html><h3>Name</h3></html>");
        model.addColumn("<html><h3>ID Type</h3></html>");
        model.addColumn("<html><h3>ID Number</h3></html>");
        model.addColumn("<html><h3>Gender</h3></html>");
        model.addColumn("<html><h3>Country</h3></html>");
        model.addColumn("<html><h3>Room Num</h3></html>");
        model.addColumn("<html><h3>Checkin</h3></html>");


        try{
            String query = "select * from customer";
            Dbconnect dbconnect = new Dbconnect();
            Statement stmt = dbconnect.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                    // Retrieve data from the ResultSet and add it to the table model
                    Vector<Object> row = new Vector<>();
                    row.add("<html><h4>" + resultSet.getString("name") + "</h4></html>");
                    row.add("<html><h4>" + resultSet.getString("idType") + "</h4></html>");
                    row.add("<html><h4>" + resultSet.getString("idNumber") + "</h4></html>");
                    row.add("<html><h4>" + resultSet.getString("gender") + "</h4></html>");
                    row.add("<html><h4>" + resultSet.getString("country") + "</h4></html>");
                    row.add("<html><h4>" + resultSet.getString("roomNumber") + "</h4></html>");
                    row.add("<html><h4>" + resultSet.getDate("checkinTime") + "</h4></html>");
                    model.addRow(row);
            }
            dbconnect.con.close();
        } catch(Exception e){
            System.err.println(e);
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setLocation(25,30);
        scrollPane.setSize(750,400);

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

        panel.add(scrollPane);

        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
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
        } else if(e.getSource() == backButton){
            dispose();
            new Reception(isAdmin);
        }
    }
}
