package module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TacGia {
    private int id;
    private String ten;
    private Date ngaySinh;

    public TacGia() {}

    public TacGia (int id, Connection c) {
        this.id = id;
        try {
            Scanner sc = new Scanner(System.in);
            String sql = "SELECT ten, ngaySinh FROM tacGia WHERE id = " + id + " LIMIT 1";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                setTen(rs.getString("ten"));
                setNgaySinh((new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("ngaySinh")));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public TacGia(int id, String ten, Date ngaySinh) {
        this.id = id;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
    }

    public void themTacGia(Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Nhap thong tin tac gia");
            System.out.print("Nhap ten: ");
            setTen(sc.nextLine());
            System.out.print("Nhap ngay sinh tac gia (dd/MM/yyyy): ");
            setNgaySinh((new SimpleDateFormat("dd/MM/yyyy")).parse(sc.next()));

            String sql = "INSERT INTO `tacGia` (`ten`, `ngaySinh`) VALUES ('" + getTen() + "', '"
                    + (new SimpleDateFormat("yyyy-MM-dd")).format(getNgaySinh()) +
                    "')";
            PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            setId(generatedKey);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void danhSach(Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Danh sach tac gia");
            String sql = "SELECT * FROM tacGia";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String leftAlignFormat = "| %-4s | %-30s | %-10s |%n";
            System.out.format("+------+--------------------------------+------------+%n");
            System.out.format(leftAlignFormat, "ID", "Ten", "Ngay sinh");
            System.out.format("+------+--------------------------------+------------+%n");
            while (rs.next()) {
                System.out.format(leftAlignFormat, rs.getString("id"), rs.getString("ten"), rs.getString("ngaySinh"));
            }
            System.out.format("+------+--------------------------------+------------+%n");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
