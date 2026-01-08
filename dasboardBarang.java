import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class dasboardBarang{
    static DefaultTableModel model;
    static JTable tabel;
    public static void main(String[] args){
        JFrame frame = new JFrame("Dashboar Stok Barang");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        
        String[] judul ={"Kode Barang", "Nama Barang", "Harga Barang"};
        model = new DefaultTableModel(judul, 0);
        tabel = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabel);
        JButton tblRefresh = new JButton("REFRESH!");
        tblRefresh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ambilDataDataBase();
            }
        });
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        ambilDataDataBase();
        frame.setVisible(true);
    }
    public static void ambilDataDataBase(){
        model.setRowCount(0);
        try{
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:/home/ian/kuis/barang_septian.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from barang");
            while(rs.next()){
                String kode = rs.getString("kode");
                String nama = rs.getString("namabar");
                int harga = rs.getInt("harga");
                
                Object[] dataBaris ={kode, nama, harga};
                model.addRow(dataBaris);
                
            }
            con.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "GAGAL MEMUAT DATA: "+ex.getMessage());
        }
        }
    }