import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class editBarang{
    public static void main(String[] args){
        JFrame frame = new JFrame("From Edit Barang");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));
        
        JLabel lkode = new JLabel("Kode Target");
        JTextField txtkode = new JTextField();
        
        JLabel lnama = new JLabel("Nama Baru");
        JTextField txtnama = new JTextField();
        
        JLabel lharga = new JLabel("Harga Baru");
        JTextField txtharga = new JTextField();
        
        JButton tblUpdate = new JButton("Update Data");
        JLabel lkosong = new JLabel("");
        
        tblUpdate.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                String kodeTarget = txtkode.getText();
                String nama = txtnama.getText();
                String hargaStr = txtharga.getText();
                
                if(kodeTarget.isEmpty() || nama.isEmpty() || hargaStr.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Dilarang Kosong!");
                    return;
                }
                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection con = DriverManager.getConnection("jdbc:sqlite:/home/ian/kuis/barang_septian.db");
                    
                    String sql = "update barang set namabar=?, harga=? where kode=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, nama);
                    ps.setInt(2, Integer.parseInt(hargaStr));
                    ps.setString(3, kodeTarget);
                    int hasil = ps.executeUpdate();
                        
                   if(hasil>0){
                       JOptionPane.showMessageDialog(null, "SUKSES,DATA BERHASIL DIUPDATE!");
                   }else{
                       JOptionPane.showMessageDialog(null, "Kode: "+kodeTarget+" Tidak Ditemukan!");
                   }
                   con.close();
                }catch(Exception ex){
                    System.out.println("ERROR: "+ex.getMessage());
                    JOptionPane.showMessageDialog(null, "GAGAL: "+ex.getMessage());
                }
                }
            });
            frame.add(lkode); frame.add(txtkode);
            frame.add(lnama); frame.add(txtnama);
            frame.add(lharga); frame.add(txtharga);
            frame.add(lkosong); frame.add(tblUpdate);
            frame.add(new JLabel("Masukan Kode"));
            frame.add(new JLabel("Yang Mau Diedit!"));
            
            frame.setVisible(true);
        }
    }