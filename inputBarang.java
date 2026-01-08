import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class inputBarang{
    public static void main(String[] args){
        JFrame frame = new JFrame("Input Barang");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));
        
        JLabel lkode = new JLabel("Kode Barang");
        JTextField txtkode = new JTextField();
        
        JLabel lnama = new JLabel("Nama Barang");
        JTextField txtnama = new JTextField();
        
        JLabel lharga = new JLabel("Harga Barang");
        JTextField txtharga = new JTextField();
        
        JButton tblSimpan = new JButton("Simpan");
        JLabel lkosong = new JLabel(" ");
        
        tblSimpan.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                String kode = txtkode.getText();
                String nama = txtnama.getText();
                String hargaStr = txtharga.getText();
                
                if(kode.isEmpty() || nama.isEmpty() || hargaStr.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Dilarang Kosong!");
                    return;
                }
                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection con = DriverManager.getConnection("jdbc:sqlite:/home/ian/kuis/barang_septian.db");
                    
                    String sql = "insert into barang (kode, namabar, harga) values (?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, kode);
                    ps.setString(2, nama);
                    ps.setInt(3, Integer.parseInt(hargaStr));
                    ps.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Data Masuk!");
                    txtkode.setText("");
                    txtnama.setText("");
                    txtharga.setText("");
                    
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
            frame.add(lkosong); frame.add(tblSimpan);
            
            frame.setVisible(true);
        }
    }