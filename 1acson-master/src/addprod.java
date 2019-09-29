
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BenGarde
 */
public class addprod {

    dbconnector con = new dbconnector();

    public int addProduct(String prod_desc, String prod_brand, int quantity, Object price) {
        int x1 = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);

            String sql = "insert into products values(null,?,?,?,?);";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

            String np = price.toString();
            float newprice = Float.parseFloat(np);

            pstmt.setString(1, prod_desc);
            pstmt.setString(2, prod_brand);
            pstmt.setInt(3, quantity);
            pstmt.setFloat(4, newprice);

            x1 = pstmt.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addprod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addprod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x1;
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
    public int deleteProduct(Object Barcode) {
        int x2 = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);

            String sql = "DELETE FROM products WHERE Barcode = ?;";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

            int newbar = Integer.parseInt(Barcode.toString());
            pstmt.setInt(1, newbar);

            x2 = pstmt.executeUpdate();
            //System.out.println(pstmt);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addprod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addprod.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x2;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
        public int checkUsername(String Barcode){
        int x = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);
        
            String sql = "SELECT Barcode FROM products WHERE Barcode = ?;";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        
            pstmt.setString(1, Barcode);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                x = 1;
            }else{
                x = 0;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addprod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addprod.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
}
