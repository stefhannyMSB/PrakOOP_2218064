/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package OOPbab10;
import tugaspraktikum.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ASUS
 */
public class GuiTokoBuah extends javax.swing.JFrame {

    /**
     * Creates new form GuiTokoBuah
     */
    public GuiTokoBuah() {
        initComponents();
    
       tampil();
    }
    public void batal(){
        txt_nama.setText("");
        txt_warna.setText("");
        txt_harga.setText("");
        txt_Jumlah.setText("");
        txt_pendapatan.setText("");
        txt_pengeluaran.setText("");
        txt_total.setText("");
     
    }
     public Connection conn;
   
    String Nama,Warna,Harga,Jumlah,Pendapatan,Pengeluaran,Total ; 

    public void itempilih() {
        txt_nama.setText(Nama);
        txt_warna.setText(Warna);
        txt_harga.setText(Harga);
        txt_Jumlah.setText(Jumlah);
        txt_pendapatan.setText(Pendapatan);
        txt_pengeluaran.setText(Pengeluaran);
        txt_total.setText(Total);
    }
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oopprakbab10?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiTokoBuah.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GuiTokoBuah.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GuiTokoBuah.class.getName()).log(Level.SEVERE, null, es);
        }
    }
      
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("NamaBuah");
        tabelhead.addColumn("WarnaBuah");
        tabelhead.addColumn("HargaBuah");
        tabelhead.addColumn("JumlaBuah");
        tabelhead.addColumn("Pendapatan");
        tabelhead.addColumn("Pengeluaran");
        tabelhead.addColumn("Total");

        try {
            koneksi();
            String sql = "SELECT * FROM tb_tokobuah";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6),res.getString(7), res.getString(8)});
            }
            tabelToko.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_tokobuah WHERE namabuah ='" + txt_nama.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di hapus");
            }
        }
        refresh();
    }

    
    public void update() {
        String Namabuah = txt_nama.getText();
        String Warnabuah = txt_warna.getText();
        String Hargabuah = txt_harga.getText();
        String Jumlahbuah = txt_Jumlah.getText();
        String Pendapatanbuah = txt_pendapatan.getText();
        String Pengeluaran = txt_pengeluaran.getText();
        String Totalpendapatan = txt_total.getText();
        String nama = Nama;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_tokobuah SET `namabuah`='" + Namabuah + "'," + ",`warnabuah`='" + Warnabuah + "',"
                    + ",`hargabuah`='" + Hargabuah + "'" + ",`jumlahbuah`='" + Jumlahbuah + "'" +",`pendapatanbuah`='"+Pendapatanbuah + "',`pengeluaranbuah`='"
                    + Pengeluaran + "', `totalpendapatan`= '" + Totalpendapatan + "' WHERE Nama = '" + nama + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Mahasiswa Berhasil!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }

    public void refresh() {
        new GuiTokoBuah().setVisible(true);
        this.setVisible(false);
    }

    //public void insert() {
     //   String Nama = txt_nama.getText();
       // String Warna = txt_warna.getText();
       // String Harga = txt_harga.getText();
       // String Jumlah = txt_Jumlah.getText();
        //String Pendapatan = txt_pendapatan.getText();
        //String Pengeluaran = txt_pengeluaran.getText();
        //String Total = txt_total.getText();
        //try {
            //koneksi();
            //Statement statement = conn.createStatement();
           //statement.executeUpdate("INSERT INTO tb_tokobuah (`namabuah_id`,`warnabuah`,`hargabuah`,`jumlahbuah`,`pendapatanbuah`,`pengeluaranbuah`,`totalpendapatan`)"
             //       + "VALUES('" + Nama + "','" + Warna + "','" + Harga + "','" + Jumlah + "','" + Pendapatan + "','" + Pengeluaran + "' ,'" + Total + "')");
            //statement.close();
            //JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Mahasiswa!" + "\n" + Total);
        //} catch (Exception e) {
           // JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        //}
        //refresh();
   // }
            public void insert() {
        String Namabuah = txt_nama.getText();
        String Warnabuah = txt_warna.getText();
        String Hargabuah = txt_harga.getText();
        String Jumlahbuah = txt_Jumlah.getText();
        String Pendapatanbuah = txt_pendapatan.getText();
        String Pengeluaranbuah = txt_pengeluaran.getText();
        String Totalpendapatan = txt_total.getText();
        
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_tokobuah (namabuah,warnabuah,hargabuah,jumlahbuah,pendapatanbuah,pengeluaranbuah,totalpendapatan)"
                    + "VALUES('" + Namabuah + "','" + Warnabuah + "','" + Hargabuah + "','" + Jumlahbuah + "','" + Pendapatanbuah + "','" + Pengeluaranbuah + "', '" + Totalpendapatan +  "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Buah!" + "\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_warna = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_pendapatan = new javax.swing.JTextField();
        txt_pengeluaran = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        txt_C = new javax.swing.JButton();
        txt_clo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelToko = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_Jumlah = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pendapatan Toko Buah");

        jLabel2.setText("Jumlah");

        jLabel3.setText("Warna Buah");

        jLabel4.setText("Harga Buah");

        jLabel5.setText("Pendapatan");

        jLabel6.setText("Pengeluaran");

        jLabel7.setText("Total");

        txt_C.setText("Simpan");
        txt_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CActionPerformed(evt);
            }
        });

        txt_clo.setText("Close");
        txt_clo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cloActionPerformed(evt);
            }
        });

        tabelToko.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "nama buah", "warna buah", "harga buah", "Jumlah", "pendapatan", "pengeluaran", "total"
            }
        ));
        tabelToko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTokoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelToko);

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel8.setText("nama Buah");

        btnUpdate.setText("update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7))
                            .addComponent(jLabel2))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_pengeluaran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addComponent(txt_pendapatan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_harga, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_warna, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_total))
                            .addComponent(txt_Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_C)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapus)
                                .addGap(102, 102, 102)
                                .addComponent(txt_clo)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_warna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txt_Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_C)
                    .addComponent(btnHapus)
                    .addComponent(txt_clo)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnUpdate))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CActionPerformed
        // TODO add your handling code here:
       insert();
     
    }//GEN-LAST:event_txt_CActionPerformed

    private void txt_cloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cloActionPerformed
        
         // TODO add your handling code here:
   //exit
    dispose();
    }//GEN-LAST:event_txt_cloActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tabelTokoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTokoMouseClicked
        // TODO add your handling code here:
        int Tabel = tabelToko.getSelectedRow();
        Nama = tabelToko.getValueAt(Tabel, 0).toString();
        Warna = tabelToko.getValueAt(Tabel, 1).toString();
        Harga = tabelToko.getValueAt(Tabel, 2).toString();
        Jumlah = tabelToko.getValueAt(Tabel, 3).toString();
        Pendapatan = tabelToko.getValueAt(Tabel, 4).toString();
        Pengeluaran = tabelToko.getValueAt(Tabel, 5).toString();
        Total = tabelToko.getValueAt(Tabel, 6).toString();
        itempilih();
    }//GEN-LAST:event_tabelTokoMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
         update();
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(GuiTokoBuah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiTokoBuah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiTokoBuah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiTokoBuah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiTokoBuah().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelToko;
    private javax.swing.JButton txt_C;
    private javax.swing.JTextField txt_Jumlah;
    private javax.swing.JButton txt_clo;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_pendapatan;
    private javax.swing.JTextField txt_pengeluaran;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_warna;
    // End of variables declaration//GEN-END:variables
}
