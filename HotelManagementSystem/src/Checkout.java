import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Checkout extends JFrame implements ActionListener{
    JButton checkoutButton, backButton, completeCheckoutButton;
    Choice roomNumberBox;
    JLabel background;
    JLabel roomNumberLabel, label1 , label2, label3, billLabel, checkinLabel, checkoutLabel;
    JPanel panel;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Checkout(){
        setSize(1280, 720);
        setLocation(100,100);

        ImageIcon backgroundImg =  new ImageIcon(ClassLoader.getSystemResource("images/dashboard.jpeg"));
        background = new JLabel(backgroundImg);
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
            String query = "select * from staying;";
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
        checkoutButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Checkout</span></html>");
        checkoutButton.setLocation(100,420);
        checkoutButton.setSize(120,30);
        checkoutButton.setOpaque(true);
        checkoutButton.setBorderPainted(false);
        checkoutButton.setBackground(Color.decode("#424554"));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(this);
        panel.add(checkoutButton);

        //for checkout button
        completeCheckoutButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Complete Checkout</span></html>");
        completeCheckoutButton.setLocation(100,420);
        completeCheckoutButton.setSize(120,40);
        completeCheckoutButton.setOpaque(true);
        completeCheckoutButton.setBorderPainted(false);
        completeCheckoutButton.setBackground(Color.decode("#424554"));
        completeCheckoutButton.setForeground(Color.WHITE);
        completeCheckoutButton.addActionListener(this);



        //for checkout button
        backButton = new JButton("<html><span style='font-size:14px; font-family: serif'>Back</span></html>");
        backButton.setLocation(250,420);
        backButton.setSize(120,30);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.decode("#424554"));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        panel.add(backButton);

        billLabel = new JLabel("~ ~ ~ Bill ~ ~ ~");
        billLabel.setLocation(195, 100);
        billLabel.setSize(150, 30);
        billLabel.setFont(new Font("serif", Font.PLAIN, 19));

        label1 = new JLabel();
        label1.setLocation(75, 150);
        label1.setSize(200, 30);
        label1.setFont(new Font("serif", Font.PLAIN, 19));

        checkinLabel = new JLabel();
        checkinLabel.setLocation(75, 200);
        checkinLabel.setSize(300, 30);
        checkinLabel.setFont(new Font("serif", Font.PLAIN, 19));

        checkoutLabel = new JLabel();
        checkoutLabel.setLocation(75, 250);
        checkoutLabel.setSize(300, 30);
        checkoutLabel.setFont(new Font("serif", Font.PLAIN, 19));

        label2 = new JLabel();
        label2.setLocation(75, 300);
        label2.setSize(200, 30);
        label2.setFont(new Font("serif", Font.PLAIN, 19));


        label3 = new JLabel();
        label3.setLocation(75, 350);
        label3.setSize(400, 30);
        label3.setFont(new Font("serif", Font.PLAIN, 19));



        background.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Checkout();
    }

    @SuppressWarnings("deprecation")
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
        } else if(e.getSource() == checkoutButton){
            label1.setText("Room Number : " + roomNumberBox.getSelectedItem());
            panel.add(billLabel);
            panel.add(label1);
            Date checkoutDate = new Date();
            checkoutLabel.setText("Checkout time : " + sdf.format(checkoutDate));
            panel.add(checkoutLabel);
            Date checkinDate = new Date();
            double price = 0.0;

            try {   
                Dbconnect dbconnect = new Dbconnect();
                Statement stmt = dbconnect.con.createStatement();
                String roomNumber = roomNumberBox.getSelectedItem();
                String query1 = "select * from customer where roomNumber = '" + roomNumber + "';";
                ResultSet resultSet = stmt.executeQuery(query1);
                while(resultSet.next()){

                    checkinDate = sdf.parse(resultSet.getString("checkinTime"));
                    checkinLabel.setText("Checkin Time : " + resultSet.getString("checkinTime"));
                    panel.add(checkinLabel);
                    checkinDate.setHours(12);
                    checkinDate.setMinutes(0);
                    checkinDate.setSeconds(0);
                }

                String query5 = "select * from room where roomNumber = '" + roomNumber + "';";
                resultSet = stmt.executeQuery(query5);
                while(resultSet.next()){
                    price= Double.parseDouble(resultSet.getString("price"));
                }

                    // Calculate the duration of stay in milliseconds
                long durationInMillis = checkoutDate.getTime() - checkinDate.getTime();
                JOptionPane.showMessageDialog(null, durationInMillis);

                if (checkoutDate.getHours() >= 12) {
                    // If checkout is after 12 PM, add one more day to the duration
                    durationInMillis += TimeUnit.DAYS.toMillis(1);
                }
                // Convert duration to days
                long days = TimeUnit.MILLISECONDS.toDays(durationInMillis);
                
                double totalPrice = days * price;

                label2.setText("Duration : " + days + " days");
                panel.add(label2);


                label3.setText("Total bill : " + days + " x " + price + " per day = $" + totalPrice);
                panel.add(label3);

                panel.remove(checkoutButton);
                panel.add(completeCheckoutButton);
                panel.revalidate();
                panel.repaint();

                dbconnect.con.close();
            } catch (Exception err) {
                System.err.println(err);
            }
        } else if(e.getSource() == completeCheckoutButton){
            try {   
                Dbconnect dbconnect = new Dbconnect();
                Statement stmt = dbconnect.con.createStatement();
                String roomNumber = roomNumberBox.getSelectedItem();
                
                String query2 = "delete from staying where roomNumber = '" + roomNumber + "';";
                stmt.executeUpdate(query2);

                String query3 = "update room set availability = 'Available', cleaningStatus = 'Dirty' where roomNumber = '" + roomNumber + "';";
                stmt.executeUpdate(query3);

                String query4 = "delete from customer where roomNumber = '" + roomNumber + "';";
                stmt.executeUpdate(query4);

                JOptionPane.showMessageDialog(null, "Checkout completed");
                setVisible(false);
                new Checkout();

                dbconnect.con.close();
            } catch (Exception err) {
                System.err.println(err);
            }
        }
    }
}
