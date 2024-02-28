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

public class Departments extends JFrame implements ActionListener{
    JTable table;
    JButton backButton;
    public Departments(){
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
        panel.setLocation(240, 100);
        panel.setSize(800, 500);
        panel.setLayout(null);

        //create table model with column names
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("<html><h2>Department Name</h2></html>");
        model.addColumn("<html><h2>Budget</h2></html>");
        
        try{
            String query = "select * from department";
            Dbconnect dbconnect = new Dbconnect();
            Statement stmt = dbconnect.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                    // Retrieve data from the ResultSet and add it to the table model
                    Vector<Object> row = new Vector<>();
                    row.add("<html><h3>" + resultSet.getString("name") + "</h3></html>");
                    row.add("<html><h3>" + resultSet.getString("budget") + "</h3></html>");
                    row.setSize(100);
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

        panel.add(scrollPane);


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
        } else if(e.getSource() == backButton){
            setVisible(false);
            new Reception();
        }
    }
}
