
import dao.ConnectionProvider;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author P A R S A
 */
public class BookIntra extends javax.swing.JFrame {

    /**
     * Creates new form BookIntra
     */
    private String customerName;

    public BookIntra(String customerName) {
        initComponents();
        setLocationRelativeTo(null);
        loadCityComboBox();
        //loadSourceComboBox(); 

        this.customerName = customerName;
        cmbSource.removeAllItems();
        cmbDestination.removeAllItems();
        cmbDriver.removeAllItems();
        txtCar.setText("");
        txtPrice.setText("");
        loadBookingId();

    }

    private int generateBookingId() {
        return (int) (Math.random() * 1000000); // Random booking ID between 0 and 999,999
    }

    private void loadBookingId() {
        int bookingId = generateBookingId();
        lblBookingId.setText(String.valueOf(bookingId)); // Assuming txtBookingId is the text field for the booking ID
    }

    private void loadCityComboBox() {
        try (Connection con = ConnectionProvider.getCon()) {
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                return;
            }
            System.out.println("Database connection established.");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT city FROM intracitydriver");

            cmbCity.removeAllItems(); // Clear the previous items
            while (rs.next()) {
                String city = rs.getString("city");
                //System.out.println("Adding source: " + source); // Debugging statement
                cmbCity.addItem(city); // Add the item to the combobox
            }

            if (cmbCity.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "No sources found in the database.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching sources: " + e.getMessage());
        }
    }

    private void loadSourceComboBox(String selectedCity) {
        try (Connection con = ConnectionProvider.getCon(); PreparedStatement ps = con.prepareStatement("SELECT DISTINCT source FROM intracitydriver WHERE city = ?")) {
            ps.setString(1, selectedCity);
            ResultSet rs = ps.executeQuery();
            cmbSource.removeAllItems(); // Clear previous items
            while (rs.next()) {
                cmbSource.addItem(rs.getString("source"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching sources: " + e.getMessage());
        } 
    }

    private void loadDestinationComboBox(String selectedCity, String selectedSource) {
        try (Connection con = ConnectionProvider.getCon(); PreparedStatement ps = con.prepareStatement("SELECT DISTINCT destination FROM intracitydriver WHERE city = ? AND source = ?")) {
            ps.setString(1, selectedCity);
            ps.setString(2, selectedSource);
            ResultSet rs = ps.executeQuery();
            cmbDestination.removeAllItems(); // Clear previous items
            while (rs.next()) {
                cmbDestination.addItem(rs.getString("destination"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching drivers: " + e.getMessage());
        }
    }

    private void loadDriverComboBox(String selectedSource, String selectedDestination) {
        try (Connection con = ConnectionProvider.getCon(); PreparedStatement ps = con.prepareStatement("SELECT DISTINCT drivername FROM intracitydriver WHERE source = ? AND destination = ?")) {
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
        try (Connection con = ConnectionProvider.getCon(); PreparedStatement ps = con.prepareStatement("SELECT car, price FROM intracitydriver WHERE drivername = ?")) {
            ps.setString(1, selectedDriver);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtCar.setText(rs.getString("car"));
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

        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnBook = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtCar = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmbSource = new javax.swing.JComboBox<>();
        cmbDestination = new javax.swing.JComboBox<>();
        cmbDriver = new javax.swing.JComboBox<>();
        lblBookingId = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbCity = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cab (1).jpg"))); // NOI18N
        jLabel10.setText("jLabel10");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 36)); // NOI18N
        jLabel1.setText("Book Intracity Cab");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Book ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Source");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Destination");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel5.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel5.setText("Driver Name");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jLabel6.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Car");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, -1));

        jLabel7.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Price");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

        btnBook.setBackground(new java.awt.Color(255, 204, 255));
        btnBook.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        btnBook.setText("Book Cab");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });
        getContentPane().add(btnBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 540, 105, 39));

        btnCancel.setBackground(new java.awt.Color(255, 204, 255));
        btnCancel.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, 93, 39));

        txtCar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 220, -1));

        txtPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 220, -1));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        getContentPane().add(cmbSource, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 220, -1));

        cmbDestination.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        cmbDestination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDestination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDestinationActionPerformed(evt);
            }
        });
        getContentPane().add(cmbDestination, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 220, -1));

        cmbDriver.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        cmbDriver.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDriverActionPerformed(evt);
            }
        });
        getContentPane().add(cmbDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 220, -1));

        lblBookingId.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        lblBookingId.setText("jLabel9");
        getContentPane().add(lblBookingId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 220, -1));
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 220, 67));

        jLabel11.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel11.setText("Address");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, -1));

        jLabel15.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel15.setText("City");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        cmbCity.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        cmbCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbCityMouseClicked(evt);
            }
        });
        cmbCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCityActionPerformed(evt);
            }
        });
        getContentPane().add(cmbCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 220, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/q (1).jpg"))); // NOI18N
        jLabel12.setText("jLabel12");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        // TODO add your handling code here:
        String bookingId = lblBookingId.getText(); // Booking ID from the label
String userName = customerName;
//String doBooking = txtdoBooking.getText();
String city = (String) cmbCity.getSelectedItem();
String source = (String) cmbSource.getSelectedItem(); // Selected source
String destination = (String) cmbDestination.getSelectedItem(); // Selected destination
String driverName = (String) cmbDriver.getSelectedItem(); // Selected driver
String car = txtCar.getText(); // Car model from the text field
String price = txtPrice.getText(); // Price from the text field
String address = txtAddress.getText();

// Validate input (e.g., check if any field is empty)
if ( city.isEmpty() || source.isEmpty() || destination.isEmpty() || driverName.isEmpty() || car.isEmpty() || price.isEmpty() || address.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please fill all fields before booking.");
    return;
}

// Corrected SQL query
String insertQuery = "INSERT INTO intraBooking (booking_id, userName, doBooking, city, source, destination, driver_name, car, price, address) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

String doBooking = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

try (Connection con = ConnectionProvider.getCon(); PreparedStatement pst = con.prepareStatement(insertQuery)) {
    pst.setString(1, bookingId);
    pst.setString(2, userName);
    pst.setString(3, doBooking);
    pst.setString(4, city);
    pst.setString(5, source);
    pst.setString(6, destination);
    pst.setString(7, driverName);
    pst.setString(8, car);
    pst.setString(9, price);
    pst.setString(10, address);

    int rowsAffected = pst.executeUpdate();

    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Booking successfully placed!");
        // Clear inputs
        cmbCity.setSelectedIndex(0);
        cmbSource.removeAllItems();
        cmbDestination.removeAllItems();
        cmbDriver.removeAllItems();
        txtCar.setText("");
        txtPrice.setText("");
        txtAddress.setText("");
        loadBookingId();
    } else {
        JOptionPane.showMessageDialog(null, "Failed to place booking. Try again.");
    }

} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error placing booking: " + e.getMessage());
    e.printStackTrace();
}
    }//GEN-LAST:event_btnBookActionPerformed

    private void cmbDriverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDriverActionPerformed
        // TODO add your handling code here:
        String selectedDriver = (String) cmbDriver.getSelectedItem();
        if (selectedDriver != null) {
            loadCarAndPrice(selectedDriver);
        }
    }//GEN-LAST:event_cmbDriverActionPerformed

    private void cmbDestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDestinationActionPerformed
        // TODO add your handling code here:
        String selectedSource = (String) cmbSource.getSelectedItem();
        String selectedDestination = (String) cmbDestination.getSelectedItem();
        if (selectedSource != null && selectedDestination != null) {
            loadDriverComboBox(selectedSource, selectedDestination);
        }
    }//GEN-LAST:event_cmbDestinationActionPerformed

    private void cmbSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSourceActionPerformed
        // TODO add your handling code here:
        String selectedCity = (String) cmbCity.getSelectedItem();
        String selectedSource = (String) cmbSource.getSelectedItem();
        if (selectedCity != null && selectedSource != null) {
            loadDestinationComboBox(selectedCity, selectedSource);
        }
    }//GEN-LAST:event_cmbSourceActionPerformed

    private void cmbSourceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbSourceMouseClicked
        // TODO add your handling code here:
        //loadSourceComboBox();
    }//GEN-LAST:event_cmbSourceMouseClicked

    private void cmbCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCityActionPerformed
        // TODO add your handling code here:
        String selectedCity = (String) cmbCity.getSelectedItem();
        if (selectedCity != null) {
            loadSourceComboBox(selectedCity);
        }
    }//GEN-LAST:event_cmbCityActionPerformed

    private void cmbCityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCityMouseClicked
        // TODO add your handling code here:
        loadCityComboBox();
    }//GEN-LAST:event_cmbCityMouseClicked

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
            java.util.logging.Logger.getLogger(BookIntra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookIntra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookIntra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookIntra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookIntra("Test User").setVisible(true); // Test with a sample name
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cmbCity;
    private javax.swing.JComboBox<String> cmbDestination;
    private javax.swing.JComboBox<String> cmbDriver;
    private javax.swing.JComboBox<String> cmbSource;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblBookingId;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCar;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
