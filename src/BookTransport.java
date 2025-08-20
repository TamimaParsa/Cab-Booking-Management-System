
import dao.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author P A R S A
 */
public class BookTransport extends javax.swing.JFrame {

    private String customerName;
    /**
     * Creates new form BookTransport
     */
    public BookTransport(String customerName) {
        initComponents();
        this.customerName = customerName;
        setLocationRelativeTo(null);
        loadSourceComboBox();
        populateTimeComboBox();
        ampmComboBox();

        //cmbSource.removeAllItems();
        cmbDestination.removeAllItems();
        cmbDriver.removeAllItems();
        txtCar.setText("");
        txtPrice.setText("");
        loadBookingId();
        txtPrice.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            calculateAdvance();
        }
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            calculateAdvance();
        }
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            calculateAdvance();
        }
    });
}
    
    private void populateTimeComboBox() {
    String[] values = {"12-1", "1-2", "2-3", "3-4", "4-5", "5-6", "6-7", "7-8", "8-9", "9-10", "10-11", "11-12"};
    for (String time : values) {
        cmbTime.addItem(time);
    }
}
    private void ampmComboBox() {
    String[] values = {"AM", "PM"};
    for (String time : values) {
        cmbAmPm.addItem(time);
    }
}
// Method to calculate 10% advance and update the field
private void calculateAdvance() {
    try {
        String priceText = txtPrice.getText().trim();
        if (!priceText.isEmpty()) {
            double price = Double.parseDouble(priceText);
            double advance = price * 0.10; // 10% of the price
            txtAdvancePayment.setText(String.format("%.2f", advance)); // Format to 2 decimal places
        } else {
            txtAdvancePayment.setText(""); // Clear if price is empty
        }
    } catch (NumberFormatException e) {
        txtAdvancePayment.setText(""); // Handle invalid input
    }} 
    /**
     * Creates new form CustomerFeatures
     */
    private int generateBookingId() {
        return (int) (Math.random() * 1000000); // Random booking ID between 0 and 999,999
    }

    private void loadBookingId() {
        int bookingId = generateBookingId();
        lblBookingId.setText(String.valueOf(bookingId)); // Assuming txtBookingId is the text field for the booking ID
    }

    private void loadSourceComboBox() {
        try (Connection con = ConnectionProvider.getCon()) {
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                return;
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT source FROM transportdriver");

            cmbSource.removeAllItems(); // Clear the previous items
            while (rs.next()) {
                String source = rs.getString("source");
                cmbSource.addItem(source); // Add the item to the combobox
            }

            if (cmbSource.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "No sources found in the database.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching sources: " + e.getMessage());
        }
    }

    private void loadDestinationComboBox(String selectedSource) {
        try (Connection con = ConnectionProvider.getCon(); PreparedStatement ps = con.prepareStatement("SELECT DISTINCT destination FROM transportdriver WHERE source = ?")) {
            ps.setString(1, selectedSource);
            ResultSet rs = ps.executeQuery();
            cmbDestination.removeAllItems(); // Clear previous items
            while (rs.next()) {
                cmbDestination.addItem(rs.getString("destination"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching destinations: " + e.getMessage());
        }
    }

    private void loadDriverComboBox(String selectedSource, String selectedDestination) {
        try (Connection con = ConnectionProvider.getCon(); PreparedStatement ps = con.prepareStatement("SELECT DISTINCT drivername FROM transportdriver WHERE source = ? AND destination = ?")) {
            ps.setString(1, selectedSource);
            ps.setString(2, selectedDestination);
            ResultSet rs = ps.executeQuery();
            cmbDriver.removeAllItems(); // Clear previous items
            while (rs.next()) {
                cmbDriver.addItem(rs.getString("drivername"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching drivers: " + e.getMessage());
        }
    }

    private void loadCarAndPrice(String selectedDriver) {
        try (Connection con = ConnectionProvider.getCon(); PreparedStatement ps = con.prepareStatement("SELECT truck, price FROM transportdriver WHERE drivername = ?")) {
            ps.setString(1, selectedDriver);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtCar.setText(rs.getString("truck"));
                txtPrice.setText(rs.getString("price"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching car and price: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbDestination = new javax.swing.JComboBox<>();
        lblBookingId = new javax.swing.JLabel();
        cmbDriver = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnBook = new javax.swing.JButton();
        cmbSource = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtAdvancePayment = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdoBooking = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        cmbTime = new javax.swing.JComboBox<>();
        cmbAmPm = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbDestination.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        cmbDestination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDestination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDestinationActionPerformed(evt);
            }
        });
        getContentPane().add(cmbDestination, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 281, 305, -1));

        lblBookingId.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        lblBookingId.setForeground(new java.awt.Color(255, 255, 255));
        lblBookingId.setText("jLabel9");
        getContentPane().add(lblBookingId, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 113, 220, -1));

        cmbDriver.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        cmbDriver.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDriverActionPerformed(evt);
            }
        });
        getContentPane().add(cmbDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 330, 309, -1));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Book Transport Truck");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 23, -1, -1));

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Book ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 111, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Source");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 242, -1, -1));

        jLabel4.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Destination");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 282, -1, -1));

        jLabel5.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Driver Name");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 331, -1, -1));

        jLabel6.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Truck");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 370, -1, -1));

        txtCar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 370, 308, -1));

        jLabel7.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Price");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 416, -1, -1));

        txtPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 414, 310, -1));

        btnBook.setBackground(new java.awt.Color(255, 204, 255));
        btnBook.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        btnBook.setText("Book Truck");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });
        getContentPane().add(btnBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 600, -1, 39));

        cmbSource.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        cmbSource.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSource.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbSourceMouseClicked(evt);
            }
        });
        cmbSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSourceActionPerformed(evt);
            }
        });
        getContentPane().add(cmbSource, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 241, 306, -1));

        btnCancel.setBackground(new java.awt.Color(255, 204, 255));
        btnCancel.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 600, 93, 39));

        jLabel9.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Date of Booking");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 151, -1, -1));

        jLabel10.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Address");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 523, -1, -1));
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 500, 308, 67));
        getContentPane().add(txtAdvancePayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 460, 308, -1));

        jLabel12.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" Advance (10%)");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 461, -1, -1));
        getContentPane().add(txtdoBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 148, 306, -1));

        jLabel8.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Time of Booking");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 196, -1, -1));

        cmbTime.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        cmbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTimeActionPerformed(evt);
            }
        });
        getContentPane().add(cmbTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 195, 205, -1));

        cmbAmPm.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        getContentPane().add(cmbAmPm, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 195, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/black900x900.jpg"))); // NOI18N
        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbDestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDestinationActionPerformed
        // TODO add your handling code here:
        String selectedSource = (String) cmbSource.getSelectedItem();
        String selectedDestination = (String) cmbDestination.getSelectedItem();
        if (selectedSource != null && selectedDestination != null) {
            loadDriverComboBox(selectedSource, selectedDestination);
        }
    }//GEN-LAST:event_cmbDestinationActionPerformed

    private void cmbDriverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDriverActionPerformed
        // TODO add your handling code here:
        String selectedDriver = (String) cmbDriver.getSelectedItem();
        if (selectedDriver != null) {
            loadCarAndPrice(selectedDriver);
        }
    }//GEN-LAST:event_cmbDriverActionPerformed

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        // TODO add your handling code here:
           String bookingId = lblBookingId.getText().trim();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String doBooking = sdf.format(txtdoBooking.getDate());
String time = (String) cmbTime.getSelectedItem();
String ampm = (String) cmbAmPm.getSelectedItem();
String source = (String) cmbSource.getSelectedItem();
String destination = (String) cmbDestination.getSelectedItem();
String driverName = (String) cmbDriver.getSelectedItem();
String car = txtCar.getText().trim();
String priceText = txtPrice.getText().trim();
String address = txtAddress.getText().trim();

// Ensure customer name is defined
String userName = customerName != null ? customerName.trim() : "";

// Validate input fields
if (userName.isEmpty() || doBooking.isEmpty() || time.isEmpty() || ampm.isEmpty() || source.isEmpty() || 
    destination.isEmpty() || driverName.isEmpty() || car.isEmpty() || 
    priceText.isEmpty() || address.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please fill all fields before booking.");
    return;
}

// Parse price as double
double price;
try {
    price = Double.parseDouble(priceText);
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid number.");
    return;
}

// Calculate advance payment (default to 10% of price)
double advance = price * 0.10;

try {
    String advanceText = txtAdvancePayment.getText().trim();
    if (!advanceText.isEmpty()) {
        advance = Double.parseDouble(advanceText);
    }
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Invalid advance payment amount.");
    return;
}

// Calculate cash on delivery
double cashOnDelivery = price - advance;

String insertQuery = "INSERT INTO transportBooking (booking_id, userName, doBooking, time, ampm, source, destination, driver_name, car, price, advance_payment, cash_on_delivery, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

try (Connection con = ConnectionProvider.getCon();
     PreparedStatement pst = con.prepareStatement(insertQuery)) {

    pst.setString(1, bookingId);
    pst.setString(2, userName);
    pst.setString(3, doBooking);
    pst.setString(4, time);
    pst.setString(5, ampm);
    pst.setString(6, source);
    pst.setString(7, destination);
    pst.setString(8, driverName);
    pst.setString(9, car);
    pst.setDouble(10, price); // Use double for price
    pst.setDouble(11, advance); // Use double for advance payment
    pst.setDouble(12, cashOnDelivery); // Insert calculated cash on delivery
    pst.setString(13, address);

    int rowsAffected = pst.executeUpdate();

    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Booking successfully placed!");
    } else {
        JOptionPane.showMessageDialog(null, "Failed to place booking. Try again.");
    }

} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error placing booking: " + e.getMessage());
}

    }//GEN-LAST:event_btnBookActionPerformed

    private void cmbSourceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbSourceMouseClicked
        // TODO add your handling code here:
        loadSourceComboBox();
    }//GEN-LAST:event_cmbSourceMouseClicked

    private void cmbSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSourceActionPerformed
        // TODO add your handling code here:
        String selectedSource = (String) cmbSource.getSelectedItem();
        if (selectedSource != null) {
            loadDestinationComboBox(selectedSource);
        }
    }//GEN-LAST:event_cmbSourceActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTimeActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cmbTimeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookTransport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookTransport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookTransport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookTransport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new BookTransport("Test User").setVisible(true); // Test with a sample name
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cmbAmPm;
    private javax.swing.JComboBox<String> cmbDestination;
    private javax.swing.JComboBox<String> cmbDriver;
    private javax.swing.JComboBox<String> cmbSource;
    private javax.swing.JComboBox<String> cmbTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblBookingId;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAdvancePayment;
    private javax.swing.JTextField txtCar;
    private javax.swing.JTextField txtPrice;
    private com.toedter.calendar.JDateChooser txtdoBooking;
    // End of variables declaration//GEN-END:variables
}
