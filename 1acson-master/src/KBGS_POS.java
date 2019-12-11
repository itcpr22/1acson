
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BenGarde
 */
public class KBGS_POS extends javax.swing.JFrame {

    dbconnector con = new dbconnector();

    /**
     * Creates new form POS
     */
    public KBGS_POS() {
        initComponents();
        tablepanel.setVisible(false);
        jFrame1.setVisible(true);
        jFrame1.setAlwaysOnTop(true);
//xxx-------------------------------------------------------------------xxx//
        ActionListener dtr = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                String ampm = null, hour = null;
                if (String.valueOf(cal.get(Calendar.AM_PM)).equals("0")) {
                    ampm = "AM";
                } else if (String.valueOf(cal.get(Calendar.AM_PM)).equals("1")) {
                    ampm = "PM";
                }
                if (String.valueOf(cal.get(Calendar.HOUR)).equals("0")) {
                    hour = "12";
                } else {
                    hour = String.valueOf(cal.get(Calendar.HOUR));
                }
                time.setText(hour + ":" + String.valueOf(cal.get(Calendar.MINUTE)) + ":"
                        + String.valueOf(cal.get(Calendar.SECOND)) + " " + ampm);
                int month = cal.get(cal.MONTH);
                int year = cal.get(cal.YEAR);
                int day = cal.get(cal.DAY_OF_MONTH);
                int sumOfDay, due, s1, s2;

                String a, b, c;
                a = String.valueOf(month);
                b = String.valueOf(year);
                c = String.valueOf(day);
                String[] strings = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//        strings[11] = "11";
                if (a.equals("0")) {
                    date.setText(strings[0] + " " + c + ",  " + b);
                    months.setText(strings[0]);
//                    months2.setText("1");
                } else if (a.equals("1")) {
                    date.setText(strings[1] + " " + c + ",  " + b);
                    months.setText(strings[1]);
//                    months2.setText("2");
                } else if (a.equals("2")) {
                    date.setText(strings[2] + " " + c + ",  " + b);
                    months.setText(strings[2]);
//                    months2.setText("3");
                } else if (a.equals("3")) {
                    date.setText(strings[3] + " " + c + ",  " + b);
                    months.setText(strings[3]);
//                    months2.setText("4");
                } else if (a.equals("4")) {
                    date.setText(strings[4] + " " + c + ",  " + b);
                    months.setText(strings[4]);
//                    months2.setText("5");
                } else if (a.equals("5")) {
                    date.setText(strings[5] + " " + c + ",  " + b);
                    months.setText(strings[5]);
//                    months2.setText("6");
                } else if (a.equals("6")) {
                    date.setText(strings[6] + " " + c + ",  " + b);
                    months.setText(strings[6]);
//                    months2.setText("7");
                } else if (a.equals("7")) {
                    date.setText(strings[7] + " " + c + ",  " + b);
                    months.setText(strings[7]);
//                    months2.setText("8");
                } else if (a.equals("8")) {
                    date.setText(strings[8] + " " + c + ", " + b);
                    months.setText(strings[8]);
//                    months2.setText("9");
                } else if (a.equals("9")) {
                    date.setText(strings[9] + " " + c + ", " + b);
                    months.setText(strings[9]);
//                    months2.setText("10");
                } else if (a.equals("10")) {
                    date.setText(strings[10] + " " + c + ", " + b);
                    months.setText(strings[10]);
//                    months2.setText("11");
                } else if (a.equals("11")) {
                    date.setText(strings[11] + " " + c + ", " + b);
                    months.setText(strings[11]);
//                    months2.setText("12");
                } else {
                }
//                yr1.setText(b);
//                jLabel8.setText(c);
            }
        };
        new Timer(1, dtr).start();

//xxx-------------------------------------------------------------------xxx//        
    }

    private void clear() {
        qty.setValue(0);
        desc.setText("Description");
        itemtype.setText("Item type");
        brand.setText("Brand");
        price.setText("Price");
    }
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//

    public void userlogin() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
            Connection con = DriverManager.getConnection(url);

            Statement stmt = null;
            ResultSet rs = null;
            String sql = "select * from account where ID = '" + cashierid.getText() + "' ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("User_type").equals("User")) {
                    if (cashierid.getText().equals(rs.getString("ID"))) {
                        jFrame1.setVisible(false);
                        jFrame1.setAlwaysOnTop(false);
                        userid.setText(rs.getString("ID"));
                        cashierusername.setText(rs.getString("Username"));
                        tablepanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "ID Number Not Found!?");
                    }
                } else {
                    JOptionPane.showMessageDialog(jFrame1, "Trying to access another account!?");
                    cashierid.setText("");

                }

            }
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KBGS_POS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KBGS_POS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
    public void refresh() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);

//            String sqllog = "select * from account";
//            Statement stmtlog = (Statement) conn.createStatement();
//            ResultSet rslog = stmtlog.executeQuery(sqllog);
//            if (rslog.next()) {
//                if (cashierid.getText().equals(rslog.getString("ID"))) {
            if (cashierid.getText().equals("Cashiername")) {
                JOptionPane.showMessageDialog(rootPane, "NO FUCKING!!! CASHIER ID!!!");
            } else {

                String sql = "select * from " + cashierusername.getText() + " ";
                Statement stmt = (Statement) conn.createStatement();

                ResultSet rs = stmt.executeQuery(sql);
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("Barcode"), rs.getString("Description"), rs.getString("Item_type"),
                        rs.getString("Brand"), rs.getString("Quantity"), rs.getString("Price"), rs.getString("Subtotal")});
                }
                String sqlTotal = "SELECT SUM(Subtotal) AS TotalItemsOrdered FROM " + cashierusername.getText() + " ";
                Statement stmtTotal = (Statement) conn.createStatement();
                ResultSet rsTotal = stmtTotal.executeQuery(sqlTotal);
                int getmodel = model.getRowCount();

                if (rsTotal.next()) {
                    if (getmodel <= 0) {

                    } else {

                        totalamount.setText(rsTotal.getString("TotalItemsOrdered"));
                        amountdue.setText(rsTotal.getString("TotalItemsOrdered") + ".00");

                        double vatamount = Double.parseDouble(totalamount.getText());
                        double vat = vatamount * .12;
                        vat12.setText(String.valueOf(vat));

                    }

                } else {

                }
            }
//
//                } else {
//
//                }
//            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KBGS_MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KBGS_MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
    public void checkorder() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
            Connection conn = DriverManager.getConnection(url);

            Statement stmt = null;
            ResultSet rs = null;
            String sql = "select * from products where Barcode = '" + barcode.getText() + "' "
                    + "and Description = '" + desc.getText() + "' "
                    + "and Item_type = '" + itemtype.getText() + "' "
                    + "and Brand = '" + brand.getText() + "' "
                    + "and Price = '" + price.getText() + "' ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            Statement stmtq = null;
            ResultSet rss = null;
            String sqlq = "select * from " + cashierusername.getText() + " ";
            stmtq = conn.createStatement();
            rss = stmtq.executeQuery(sqlq);
            
            if (rss.next()) {
                if (rs.next()) {
                    if (barcode.getText().equals(rss.getString("Barcode"))) {
                        JOptionPane.showMessageDialog(rootPane, "barcode already exist!");

                        int qq = Integer.parseInt(rss.getString("Quantity"));
                        int qq_txt = Integer.parseInt(qty.getValue().toString());
                        int sol = qq + qq_txt;

                        Statement st = null;
                        String sq = "UPDATE `kbgs`.`" + cashierusername.getText() + "` SET `Quantity`='" + sol + "' WHERE `Barcode`='" + barcode.getText() + "'";
                        st = conn.createStatement();
                        st.executeUpdate(sq);

                        int quantity_db = Integer.parseInt(rs.getString("Quantity"));
                        int quantity_txt = Integer.parseInt(qty.getValue().toString());
                        int solve = quantity_db - quantity_txt;

                        Statement stmtqty = null;
                        String sqlqty = "UPDATE `kbgs`.`products` SET `Quantity`='" + solve + "' WHERE  `Barcode`='" + barcode.getText() + "'";
                        stmtqty = conn.createStatement();
                        stmtqty.executeUpdate(sqlqty);
                        
                        refresh();
                        clear();
                        barcode.setText("");

                    } else {
                        orderMinusQuantity();
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KBGS_POS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KBGS_POS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//  
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//  
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//  
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
    public void orderMinusQuantity() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
            Connection conn = DriverManager.getConnection(url);

            Statement stmt = null;
            ResultSet rs = null;
            String sql = "select * from products where Barcode = '" + barcode.getText() + "' "
                    + "and Description = '" + desc.getText() + "' "
                    + "and Item_type = '" + itemtype.getText() + "' "
                    + "and Brand = '" + brand.getText() + "' "
                    + "and Price = '" + price.getText() + "' ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            Object checkqty = qty.getValue();
            int dcheck = (int) checkqty;
            int x = 0;

            if (dcheck == x) {
                JOptionPane.showMessageDialog(rootPane, "add quantity!?");
                barcode.setText(barcode.getText());
            } else if (barcode.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "No fucking barcode!!!");
            } else {

                if (rs.next()) {

                    int quantity_db = Integer.parseInt(rs.getString("Quantity"));
                    int quantity_txt = Integer.parseInt(qty.getValue().toString());
                    int solve = quantity_db - quantity_txt;

                    Statement stmtqty = null;
                    String sqlqty = "UPDATE `kbgs`.`products` SET `Quantity`='" + solve + "' WHERE  `Barcode`='" + barcode.getText() + "'";
                    stmtqty = conn.createStatement();
                    stmtqty.executeUpdate(sqlqty);
//                JOptionPane.showMessageDialog(rootPane, "Quantity have been subtracted");
                    int orderqty = Integer.parseInt(qty.getValue().toString());
                    int orderprice = Integer.parseInt(price.getText());
                    int ordertotal = orderqty * orderprice;

                    Statement stmtInsertOrder = null;
                    String sqlInsertOrder = "INSERT INTO `kbgs`. `" + cashierusername.getText() + "` (`Barcode`, `Description`, `Item_type`, `Brand`, `Quantity`, `Price`, `Subtotal`, `User_id`, `Date_purchase`)"
                            + " VALUES "
                            + "('" + barcode.getText() + "',"
                            + " '" + desc.getText() + "',"
                            + " '" + itemtype.getText() + "',"
                            + " '" + brand.getText() + "',"
                            + " '" + orderqty + "',"
                            + " '" + price.getText() + "',"
                            + " '" + ordertotal + "',"
                            + " '" + userid.getText() + "',"
                            + " '" + date.getText() + " " + time.getText() + "')";
                    stmtInsertOrder = conn.createStatement();
                    stmtInsertOrder.executeUpdate(sqlInsertOrder);

                    JOptionPane.showMessageDialog(rootPane, "Order have been added!");
//                    refresh();
                    barcode.setText("");

                }
            }

        } catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        months = new javax.swing.JLabel();
        jFrame1 = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        cashierid = new javax.swing.JTextField();
        jDialog1 = new javax.swing.JDialog();
        jLabel20 = new javax.swing.JLabel();
        BTNOK = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        backquantity = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        removeorder = new javax.swing.JButton();
        removeorder2 = new javax.swing.JButton();
        removeorder3 = new javax.swing.JButton();
        removeorder4 = new javax.swing.JButton();
        removeorder5 = new javax.swing.JButton();
        removeorder6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        totalamount = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        amountdue = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        vat12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        barcode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        qty = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        desc = new javax.swing.JLabel();
        brand = new javax.swing.JLabel();
        itemtype = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        searchProd = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        change = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cash = new javax.swing.JTextField();
        addproduct = new javax.swing.JButton();
        userid = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cashierusername = new javax.swing.JLabel();
        tablepanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cashout = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        home = new javax.swing.JMenuItem();
        lbldate = new javax.swing.JMenu();
        date = new javax.swing.JMenu();
        lbltime = new javax.swing.JMenu();
        time = new javax.swing.JMenu();

        jMenu2.setText("jMenu2");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        months.setText("jLabel20");

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jFrame1.setLocation(new java.awt.Point(300, 250));
        jFrame1.setResizable(false);
        jFrame1.setSize(new java.awt.Dimension(280, 130));
        jFrame1.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                jFrame1WindowClosed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("ENTER CASHIER ID");

        cashierid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cashierid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashieridActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cashierid, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cashierid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setLocation(new java.awt.Point(200, 250));
        jDialog1.setResizable(false);
        jDialog1.setSize(new java.awt.Dimension(298, 93));
        jDialog1.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                jDialog1WindowClosed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setText("Click");

        BTNOK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BTNOK.setText("OK");
        BTNOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNOKActionPerformed(evt);
            }
        });
        BTNOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BTNOKKeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("to continue");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(BTNOK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        backquantity.setText("jLabel28");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("kiNGBEN GUNS STORE");

        jLabel3.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Military supplies & equipment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Sold to: ");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("TIN No : ");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Business style : ");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Address : ");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        removeorder.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        removeorder.setText("Remove order");
        removeorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeorderActionPerformed(evt);
            }
        });

        removeorder2.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        removeorder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeorder2ActionPerformed(evt);
            }
        });

        removeorder3.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        removeorder3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeorder3ActionPerformed(evt);
            }
        });

        removeorder4.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        removeorder4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeorder4ActionPerformed(evt);
            }
        });

        removeorder5.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        removeorder5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeorder5ActionPerformed(evt);
            }
        });

        removeorder6.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        removeorder6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeorder6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jTextField4))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeorder4, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(removeorder5, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeorder2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(removeorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeorder6, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(removeorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator3)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(removeorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeorder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(removeorder3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeorder6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(removeorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeorder4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total Amount : ");

        totalamount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalamount.setForeground(new java.awt.Color(102, 0, 0));
        totalamount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalamount.setText("0.00");
        totalamount.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalamount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        amountdue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        amountdue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        amountdue.setText("0.00");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("0.00");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("0.00");

        vat12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        vat12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        vat12.setText("0.00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("VATable");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("0.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("VAT-Exempt Sale");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Discount");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("VAT 12%");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Amount Due");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(amountdue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vat12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(21, 21, 21))
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vat12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(21, 21, 21))
                            .addComponent(amountdue))))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        barcode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        barcode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        barcode.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                barcodeCaretUpdate(evt);
            }
        });
        barcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                barcodeMouseEntered(evt);
            }
        });
        barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcodeActionPerformed(evt);
            }
        });
        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("BARCODE");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("QUANTITY");

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                qtyHierarchyChanged(evt);
            }
        });
        qty.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                qtyAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        qty.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qtyStateChanged(evt);
            }
        });
        qty.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                qtyCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                qtyInputMethodTextChanged(evt);
            }
        });
        qty.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                qtyPropertyChange(evt);
            }
        });
        qty.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                qtyVetoableChange(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Description");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Item Type");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Price");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Brand");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        desc.setBackground(new java.awt.Color(0, 0, 0));
        desc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        desc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desc.setText("Description");
        desc.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        brand.setBackground(new java.awt.Color(0, 0, 0));
        brand.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        brand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        brand.setText("Brand");
        brand.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        itemtype.setBackground(new java.awt.Color(0, 0, 0));
        itemtype.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        itemtype.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemtype.setText("Item type");
        itemtype.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        price.setBackground(new java.awt.Color(0, 0, 0));
        price.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        price.setText("Price");
        price.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 153)));

        searchProd.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        searchProd.setText("search");
        searchProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProdActionPerformed(evt);
            }
        });

        change.setBackground(new java.awt.Color(0, 0, 0));
        change.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        change.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        change.setText("0.00");
        change.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 0, 0)));

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Change");
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 0, 0)));

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Cash");
        jLabel27.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 0, 0)));

        cash.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cash.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cash.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 0, 0)));
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });

        addproduct.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        addproduct.setText("ADD");
        addproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addproductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itemtype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(desc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(price, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(barcode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchProd))
                    .addComponent(jSeparator2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(change, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cash))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addproduct)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addproduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cash, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(change, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(price)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(desc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(itemtype)
                        .addGap(30, 30, 30)))
                .addContainerGap())
        );

        userid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        userid.setText("0000");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Username : ");

        cashierusername.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cashierusername.setText("Cashiername");

        tablepanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 204)));

        jTable1.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode", "Description", "Item Type", "Brand", "Quantity", "Price", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout tablepanelLayout = new javax.swing.GroupLayout(tablepanel);
        tablepanel.setLayout(tablepanelLayout);
        tablepanelLayout.setHorizontalGroup(
            tablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                .addContainerGap())
        );
        tablepanelLayout.setVerticalGroup(
            tablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        cashout.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cashout.setText("Cashout");
        cashout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashoutActionPerformed(evt);
            }
        });

        jMenu1.setText("Navigate");
        jMenu1.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N

        home.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        jMenu1.add(home);

        jMenuBar1.add(jMenu1);

        lbldate.setText("Date : ");
        jMenuBar1.add(lbldate);

        date.setText("date");
        jMenuBar1.add(date);

        lbltime.setText("Time : ");
        jMenuBar1.add(lbltime);

        time.setText("time");
        jMenuBar1.add(time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tablepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cashierusername)
                                .addGap(18, 18, 18)
                                .addComponent(cashout, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(userid)
                                .addComponent(jLabel26)
                                .addComponent(cashierusername))
                            .addComponent(cashout, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tablepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barcodeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_barcodeCaretUpdate

        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
            Connection conn = DriverManager.getConnection(url);

            Statement stmt = null;
            ResultSet rs = null;
            String sql = "select * from products where Barcode like '%" + barcode.getText() + "%'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            int x = 0;
            while (x < table.getRowCount()) {
                table.removeRow(0);
            }
            while (rs.next()) {

                String a = rs.getString("Description");
                String b = rs.getString("Brand");
                String c = rs.getString("Item_type");
                String d = rs.getString("Price");
                String bar = rs.getString("Barcode");

//                table.addRow(new Object[]{
//                    a, b, c, d, e
//                });
                if (barcode.getText().equals(bar)) {

                    desc.setText(a);
                    brand.setText(b);
                    itemtype.setText(c);
                    price.setText(d);
                    refresh();

                } else {

//                  desc.setText("Barcode not available");
//                  brand.setText("Barcode not available");
//                  itemtype.setText("Barcode not available");
//                  price.setText("Barcode not available");
                    refresh();

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeCaretUpdate

    private void barcodeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barcodeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeMouseEntered

    private void searchProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProdActionPerformed
//        tablepanel.setVisible(false);
//        jDialog1.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_searchProdActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        new KBGS_MainPage().setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_homeActionPerformed

    private void barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeKeyPressed

    private void qtyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qtyStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_qtyStateChanged

    private void qtyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_qtyPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_qtyPropertyChange

    private void qtyVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_qtyVetoableChange

        // TODO add your handling code here:
    }//GEN-LAST:event_qtyVetoableChange

    private void qtyInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_qtyInputMethodTextChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_qtyInputMethodTextChanged

    private void qtyCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_qtyCaretPositionChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_qtyCaretPositionChanged

    private void qtyHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_qtyHierarchyChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_qtyHierarchyChanged

    private void qtyAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_qtyAncestorAdded

        // TODO add your handling code here:
    }//GEN-LAST:event_qtyAncestorAdded

    private void barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcodeActionPerformed
        orderMinusQuantity();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeActionPerformed

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        int tamount = Integer.parseInt(totalamount.getText());
        int cashtendered = Integer.parseInt(cash.getText());
        int cashchange = cashtendered - tamount;
        if (tamount > cashtendered) {
            JOptionPane.showMessageDialog(rootPane, "not enough cash");
            change.setText("0.00");
        } else {
            change.setText(String.valueOf(cashchange));
            int confirm = JOptionPane.showConfirmDialog(rootPane, "Customer change is " + change.getText() + "\n\nClick OK to continue", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (confirm == JOptionPane.OK_OPTION) {
                dropdb();
//                JOptionPane.showMessageDialog(rootPane, "Table has been drop");
                cash.setText("");
                change.setText("0.00");
                vat12.setText("0.00");
                amountdue.setText("0.00");
                totalamount.setText("0.00");
                tablepanel.setVisible(false);
                jDialog1.setVisible(true);
            } else {
//                JOptionPane.showMessageDialog(rootPane, "Table has been drop");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cashActionPerformed

    private void cashieridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashieridActionPerformed
        userlogin();
        // TODO add your handling code here:
    }//GEN-LAST:event_cashieridActionPerformed

    private void jDialog1WindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowClosed

        JOptionPane.showMessageDialog(rootPane, "Click OK for new transaction", "Information message", JOptionPane.INFORMATION_MESSAGE);
        jDialog1.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog1WindowClosed

    private void BTNOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNOKActionPerformed

        createdb();
        refresh();
        jDialog1.setVisible(false);
        tablepanel.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_BTNOKActionPerformed

    private void BTNOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BTNOKKeyPressed
        createdb();
        refresh();
        jDialog1.setVisible(false);
        tablepanel.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNOKKeyPressed

    private void jFrame1WindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jFrame1WindowClosed
        JOptionPane.showMessageDialog(rootPane, "Please enter your cashier ID Number!");
        // TODO add your handling code here:
    }//GEN-LAST:event_jFrame1WindowClosed

    private void addproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addproductActionPerformed
//        orderMinusQuantity();
        checkorder();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_addproductActionPerformed

    private void removeorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeorderActionPerformed
        int table = jTable1.getSelectedRow();

        if (table == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please select table content first! ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            Object quant = jTable1.getValueAt(table, 4);
            Object barc = jTable1.getValueAt(table, 0);
            Object itemname = jTable1.getValueAt(table, 1);

            backquantity.setText((String) quant);

            int confirm_prod = JOptionPane.showConfirmDialog(rootPane, "Remove product " + itemname + "?"
                    + "\nClick OK to continue", "Confirm Removed", JOptionPane.OK_CANCEL_OPTION);

            if (confirm_prod == JOptionPane.YES_OPTION) {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
                    Connection conn = DriverManager.getConnection(url);

                    Statement stmt = null;
                    ResultSet rs = null;
                    String sql = "select * from products where Barcode = '" + barc + "' ";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        int quantity_db = Integer.parseInt(rs.getString("Quantity"));
                        int quantity_txt = Integer.parseInt(backquantity.getText());
                        int solve = quantity_db + quantity_txt;

                        Statement stmtqty = null;
                        String sqlqty = "UPDATE `kbgs`.`products` SET `Quantity`='" + solve + "' WHERE  `Barcode`='" + barc + "'";
                        stmtqty = conn.createStatement();
                        stmtqty.executeUpdate(sqlqty);

                        Statement stmt1 = null;
                        String sql1 = "DELETE FROM `kbgs`.`" + cashierusername.getText() + "` WHERE  `Barcode`='" + barc + "'";
                        stmt1 = conn.createStatement();
                        stmt1.executeUpdate(sql1);

                        JOptionPane.showMessageDialog(rootPane, "Order had been removed");
                        refresh();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(KBGS_POS.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(KBGS_POS.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_removeorderActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int table = jTable1.getSelectedRow();

        if (table == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please select table content first! ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            Object quant = jTable1.getValueAt(table, 3);
            Object itemname = jTable1.getValueAt(table, 3);
            backquantity.setText((String) quant);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void cashoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashoutActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
            Connection con = DriverManager.getConnection(url);

            Statement stmt4 = null;
            String sql4 = "INSERT INTO `kbgs`.`user_logger` (`ID`, `Username`, `Action`) "
                    + "VALUES ('" + userid.getText() + "', '" + cashierusername.getText() + "', 'logout account/cashout');";
            stmt4 = con.createStatement();
            stmt4.executeUpdate(sql4);

            userid.setText("0000");
            cashierusername.setText("Cashiername");
            new login().setVisible(true);
            this.setVisible(false);

        } catch (Exception e) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cashoutActionPerformed

    private void removeorder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeorder2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeorder2ActionPerformed

    private void removeorder3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeorder3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeorder3ActionPerformed

    private void removeorder4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeorder4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeorder4ActionPerformed

    private void removeorder5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeorder5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeorder5ActionPerformed

    private void removeorder6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeorder6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeorder6ActionPerformed

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
            java.util.logging.Logger.getLogger(KBGS_POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KBGS_POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KBGS_POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KBGS_POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KBGS_POS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNOK;
    private javax.swing.JButton addproduct;
    private javax.swing.JLabel amountdue;
    private javax.swing.JLabel backquantity;
    private javax.swing.JTextField barcode;
    private javax.swing.JLabel brand;
    private javax.swing.JTextField cash;
    private javax.swing.JTextField cashierid;
    private javax.swing.JLabel cashierusername;
    private javax.swing.JButton cashout;
    private javax.swing.JLabel change;
    private javax.swing.JMenu date;
    private javax.swing.JLabel desc;
    private javax.swing.JMenuItem home;
    private javax.swing.JLabel itemtype;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JMenu lbldate;
    private javax.swing.JMenu lbltime;
    private javax.swing.JLabel months;
    private javax.swing.JLabel price;
    private javax.swing.JSpinner qty;
    private javax.swing.JButton removeorder;
    private javax.swing.JButton removeorder2;
    private javax.swing.JButton removeorder3;
    private javax.swing.JButton removeorder4;
    private javax.swing.JButton removeorder5;
    private javax.swing.JButton removeorder6;
    private javax.swing.JButton searchProd;
    private javax.swing.JPanel tablepanel;
    private javax.swing.JMenu time;
    private javax.swing.JLabel totalamount;
    private javax.swing.JLabel userid;
    private javax.swing.JLabel vat12;
    // End of variables declaration//GEN-END:variables
//x-------------------------------------------------------------------------------------------x//

    public void dropdb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
            Connection con = DriverManager.getConnection(url);

//            Statement stmt4 = null;
//            String sql4 = "SELECT CustomerName, ContactName INTO sales_record FROM Customers ";
//            stmt4 = con.createStatement();
//            stmt4.executeUpdate(sql4);
            Statement stmt2 = null;
            String sql2 = "DROP TABLE `" + cashierusername.getText() + "` ";
            stmt2 = con.createStatement();
            stmt2.executeUpdate(sql2);

        } catch (Exception e) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, e);
        }
    }
//x-------------------------------------------------------------------------------------------x//
    //x-------------------------------------------------------------------------------------------x//

    public void createdb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kbgs?user=root&password=";
            Connection con = DriverManager.getConnection(url);

            Statement stmt1 = null;
            String sql1 = "CREATE TABLE `" + cashierusername.getText() + "` (\n"
                    + "	`Barcode` INT(55) NOT NULL,\n"
                    + "	`Description` TEXT NOT NULL,\n"
                    + "	`Item_type` TEXT NOT NULL,\n"
                    + "	`Brand` TEXT NOT NULL,\n"
                    + "	`Quantity` INT(55) NOT NULL,\n"
                    + "	`Price` INT(55) NOT NULL,\n"
                    + "	`Subtotal` INT(55) NOT NULL,\n"
                    + "	`User_id` INT(55) NOT NULL,\n"
                    + "	`Date_purchase` VARCHAR(55) NOT NULL\n"
                    + ")\n"
                    + "COLLATE='latin1_swedish_ci'\n"
                    + ";";
            stmt1 = con.createStatement();
            stmt1.executeUpdate(sql1);

//            String userlogger, userenter, enteruser;
//            userlogger = "user";
//            userenter = uname.getText();
//            enteruser = userlogger + userenter;
//
//            Statement stmt2 = null;
//            String sql2 = "CREATE TABLE `" + enteruser + "` (\n"
//                    + "	`ID` INT NOT NULL,\n"
//                    + "	`Username` TEXT NOT NULL\n"
//                    + ")\n"
//                    + "COLLATE='latin1_swedish_ci'\n"
//                    + ";";
//            stmt2 = con.createStatement();
//            stmt2.executeUpdate(sql2);
//
//            Statement stmt3 = null;
//            String sql3 = "INSERT INTO `kbgs`.`" + enteruser + "` (`ID`, `Username`) VALUES ('" + userenterid.getText() + "', '" + uname.getText() + "');";
//            stmt3 = con.createStatement();
//            stmt3.executeUpdate(sql3);
//            Statement stmt4 = null;
//            String sql4 = "INSERT INTO `kbgs`.`user_logger` (`ID`, `Username`, `Action`) VALUES ('"+userenterid.getText()+"', '"+uname.getText()+"', 'login account');";
//            stmt4 = con.createStatement();
//            stmt4.executeUpdate(sql4);
        } catch (Exception e) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, e);
        }
    }
//x-------------------------------------------------------------------------------------------x//
}
