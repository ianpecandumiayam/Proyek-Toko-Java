import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class hapus_Barang{
    public static void main(String[] args){
        JFrame frame = new JFrame("Hapus Barang");
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Masukan Kode Yang Ingin DIHAPUS");
        JTextField txtkode = new JTextField(20);
        JButton tblHapus = new JButton("HAPUS ITEM");
        tblHapus.setBackground(Color.RED);
        tblHapus.setForeground(Color.WHITE);
        
        tblHapus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String kode = txtkode.getText();
                
                int tanya = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Item ini: "+kode+"?");
                if(tanya == JOptionPane.YES_OPTION){
                    try{
                        Class.forName("org.sqlite.JDBC");
                        String url ="jdbc:sqlite:/home/ian/kuis/barang_septian.db";
                        Connection con = DriverManager.getConnection(url);
                        String sql ="delete from barang where kode=?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, kode);
                        
                        int hasil = ps.executeUpdate();
                        
                        if(hasil>0){
                            JOptionPane.showMessageDialog(null, "ITEM BERHASIL DIHAPUS!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Kode "+kode+" Tidak Ditemukan!");
                        }
                        con.close();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "ERROR: "+ex.getMessage());
                    }
                }
            }
        });
        frame.add(label);
        frame.add(txtkode);
        frame.add(tblHapus);
        
        frame.setVisible(true);
    }
}