import java.sql.*;
class cekUpdate{
    public static void main(String[] args){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:/home/ian/kuis/barang_septian.db");
            Statement s = c.createStatement();
            System.out.println("berhasil connect");
            
            System.out.println("Kode \tNama Barang \tHarga ");
            System.out.println("=========================================");
            ResultSet rs = s.executeQuery("select * from barang");
            while(rs.next()){
                System.out.print(rs.getString("kode"));
                System.out.print("\t"+rs.getString("namabar"));
                System.out.println("\t\t"+rs.getInt("harga"));
            }
        }catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}