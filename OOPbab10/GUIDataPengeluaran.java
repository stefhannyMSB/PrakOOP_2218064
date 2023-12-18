/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package OOPbab10;

import tugaspraktikum.*;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class GUIDataPengeluaran extends javax.swing.JFrame {

    /**
     * Creates new form GUIDataPengeluaran
     */
    public GUIDataPengeluaran() {
        initComponents();
        tampil();
        tampil_toko();
        tampil_tokobuah();
    }
     public void clear(){
        //txt_n.setText("");
        //txt_w.setText("");
        txt_t.setText("");
        txt_d.setText("");
        txt_no.setText("");
    }
     
     String nama,warna,tanggal,deskripsi,nominal;
     
     public Connection conn;
    //masukkan method koneksi()
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oopprakbab10?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIDataPengeluaran.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUIDataPengeluaran.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUIDataPengeluaran.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("NamaBuah");
        tabelhead.addColumn("WarnaBuah");
        tabelhead.addColumn("TanggalPengeluaran");
        tabelhead.addColumn("DeskripsiPengeluaran");
        tabelhead.addColumn("NominalPengeluaran");

        try {
            koneksi();
            String sql = "SELECT * FROM tb_datapengeluaran";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6),});
            }
            tabelPengeluaran.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void tampil_toko() {
        try {
            koneksi();
            String sql = "SELECT NamaBuah FROM tb_tokobuah order by NamaBuah asc";
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);
                cmbNama.addItem((String) ob[0]);
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void tampil_tokobuah() {
        try {
            koneksi();
            String sql = "SELECT Warnabuah FROM tb_tokobuah order by Warnabuah asc";
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);
                cmbWarna.addItem((String) ob[0]);
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void refresh() {
        new GUIDataPengeluaran().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        String Nama = (String) cmbNama.getSelectedItem();
        String warna = (String) cmbWarna.getSelectedItem();
        String tanggal = txt_t.getText();
        String deskripsi = txt_d.getText();
        String nominal = txt_no.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_datapengeluaran(NamaBuah,Warnabuah,tanggalpengeluaran,deskripsipengeluaran,totalpengeluaran)"
                    + "VALUES('" + Nama + "','" + warna + "','" + tanggal + "','" + deskripsi + "','" + nominal + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data pengeluaran!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
    
    public void update() {
        String nama = (String) cmbNama.getSelectedItem();
        String warna  = (String) cmbWarna.getSelectedItem();
        String tanggal = txt_t.getText();
        String deskripsi = txt_d.getText();
        String nominal = txt_no.getText();

        
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_pengeluaran SET `NamaBuah`='" + nama + "'," + ",`Warnabuah`='" + warna + "'"
                    + ",`tanggalpengeluaran`='" + tanggal + "',`deskripsipengeluaran`='" + deskripsi + "',`totalpengeluaran`='" + nominal );
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data pengeluaran!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
    
    void itempilih() {
        cmbNama.setSelectedItem(nama);
        cmbWarna.setSelectedItem(warna);
        txt_t.setText(tanggal);
        txt_d.setText(deskripsi);
        txt_no.setText(nominal);
        
    }
    public void batal() {
        txt_t.setText("");
        txt_d.setText("");
        txt_no.setText("");
        
    }

     public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_datapengeluaran WHERE NamaBuah='" + cmbNama.getSelectedItem() + "' AND Warnabuah='" + cmbWarna.getSelectedItem() + "'";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_t = new javax.swing.JTextField();
        txt_no = new javax.swing.JTextField();
        btnCE = new javax.swing.JButton();
        btnCO = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPengeluaran = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        btnNama = new javax.swing.JButton();
        btnWarna = new javax.swing.JButton();
        cmbNama = new javax.swing.JComboBox<>();
        cmbWarna = new javax.swing.JComboBox<>();
        txt_d = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Data Pengeluaran");

        jLabel4.setText("tanggal Pengeluaran");

        jLabel5.setText("Deskripsi Pengeluaran");

        jLabel6.setText("Nominal Pengeluaran");

        btnCE.setText("Cetak");
        btnCE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCEActionPerformed(evt);
            }
        });

        btnCO.setText("Close");
        btnCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOActionPerformed(evt);
            }
        });

        tabelPengeluaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama buah", "Warna Buah", "Tanggal Pengeluaran", "Deskripsi Pengeluaran", "Nominal Pengeluaran"
            }
        ));
        jScrollPane2.setViewportView(tabelPengeluaran);

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnNama.setText("Nama");
        btnNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNamaActionPerformed(evt);
            }
        });

        btnWarna.setText("Warna Buah");
        btnWarna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWarnaActionPerformed(evt);
            }
        });

        cmbNama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NAMA BUAH" }));

        cmbWarna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "WARNA BUAH" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(btnCE)
                                .addGap(31, 31, 31)
                                .addComponent(btnHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addComponent(btnCO))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(btnNama)
                                    .addComponent(btnWarna))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_no)
                                    .addComponent(txt_t)
                                    .addComponent(cmbNama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbWarna, 0, 208, Short.MAX_VALUE)
                                    .addComponent(txt_d))))
                        .addGap(35, 35, 35)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNama)
                    .addComponent(cmbNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWarna)
                    .addComponent(cmbWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCE)
                    .addComponent(btnHapus)
                    .addComponent(btnCO))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCEActionPerformed
        // TODO add your handling code here:
         insert();
    }//GEN-LAST:event_btnCEActionPerformed

    private void btnCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCOActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnCOActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        delete();

    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNamaActionPerformed
        // TODO add your handling code here:
        new GuiTokoBuah().setVisible(true);
    }//GEN-LAST:event_btnNamaActionPerformed

    private void btnWarnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWarnaActionPerformed
        // TODO add your handling code here:
        new GuiTokoBuah().setVisible(true);
    }//GEN-LAST:event_btnWarnaActionPerformed

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
            java.util.logging.Logger.getLogger(GUIDataPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDataPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDataPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDataPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIDataPengeluaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCE;
    private javax.swing.JButton btnCO;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnNama;
    private javax.swing.JButton btnWarna;
    private javax.swing.JComboBox<String> cmbNama;
    private javax.swing.JComboBox<String> cmbWarna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelPengeluaran;
    private javax.swing.JTextField txt_d;
    private javax.swing.JTextField txt_no;
    private javax.swing.JTextField txt_t;
    // End of variables declaration//GEN-END:variables
}
