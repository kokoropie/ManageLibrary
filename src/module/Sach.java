package module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Sach {
    private int id;
    private String ten;
    private TacGia tacGia;
    private Date ngayXuatBan;
    private Date ngayNhap;

    public Sach() {}

    public Sach(int id, Connection c) {
        setId(id);
        try {
            Scanner sc = new Scanner(System.in);
            String sql = "SELECT ten, tacGia, ngayXuatBan, ngayNhap FROM sach WHERE id = " + id + " LIMIT 1";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                setTen(rs.getString("ten"));
                setTacGia(new TacGia(rs.getInt("tacGia"), c));
                setNgayXuatBan((new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("ngayXuatBan")));
                setNgayNhap((new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("ngayNhap")));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public Sach (int id, TacGia tacGia, Connection c) {
        setId(id);
        try {
            Scanner sc = new Scanner(System.in);
            String sql = "SELECT ten, tacGia, ngayXuatBan, ngayNhap FROM sach WHERE id = " + id + " LIMIT 1";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                setTen(rs.getString("ten"));
                setTacGia(tacGia);
                setNgayXuatBan((new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("ngayXuatBan")));
                setNgayNhap((new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("ngayNhap")));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public Sach(int id, String ten, TacGia tacGia, Date ngayXuatBan, Date ngayNhap) {
        this.id = id;
        this.ten = ten;
        this.tacGia = tacGia;
        this.ngayXuatBan = ngayXuatBan;
        this.ngayNhap = ngayNhap;
    }

    public void themSach(Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Nhap thong tin sach");
            System.out.print("Nhap ten: ");
            setTen(sc.nextLine());
            (new TacGia()).danhSach(c);
            System.out.print("Nhap ID tac gia: ");
            setTacGia(new TacGia(sc.nextInt(), c));
            System.out.print("Nhap ngay xuat ban (dd/mm/yyyy): ");
            setNgayXuatBan((new SimpleDateFormat("dd/MM/yyyy")).parse(sc.next()));
            System.out.print("Nhap ngay nhap (dd/mm/yyyy): ");
            setNgayNhap((new SimpleDateFormat("dd/MM/yyyy")).parse(sc.next()));

            String sql = "INSERT INTO `sach` (`ten`, `tacGia`, `ngayXuatBan`, `ngayNhap`) VALUES ('" + getTen() + "', '"
                    + getTacGia().getId() +"','"
                    + (new SimpleDateFormat("yyyy-MM-dd")).format(getNgayXuatBan()) +"','"
                    + (new SimpleDateFormat("yyyy-MM-dd")).format(getNgayNhap()) +
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

    public void xoaSach(Connection c) {
        Scanner sc = new Scanner(System.in);
        this.danhSach(c);
        System.out.print("Nhap ID sach: ");
        xoa(sc.nextInt(), c);
    }

    public void xoa(int id, Connection c) {
        try {
            String sql = "DELETE FROM sach WHERE id = " + id;
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            (new MuonSach()).xoaSach(id, c);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void xoaTacGia (int id, Connection c) {
        try {
            (new MuonSach()).xoaTacGia(id, c);
            String sql = "DELETE FROM sach WHERE tacGia = " + id;
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void danhSach(Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Danh sach sach");
            String sql = "SELECT * FROM sach";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String leftAlignFormat = "| %-4s | %-30s | %-30s | %-10s | %-10s |%n";
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "ID", "Ten", "Tac gia", "Xuat ban", "Nhap ngay");
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "", "", "", "", "");
            while (rs.next()) {
                TacGia tacGia = new TacGia(rs.getInt("tacGia"), c);
                System.out.format(leftAlignFormat, rs.getString("id"), rs.getString("ten"), tacGia.getTen(), rs.getString("ngayXuatBan"), rs.getString("ngayNhap"));
            }
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void danhSachTacGia(Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            (new TacGia()).danhSach(c);
            System.out.print("Nhap ID tac gia: ");
            int id = sc.nextInt();
            System.out.println("------------------------------------------------------");
            System.out.println("Danh sach sach");
            String sql = "SELECT * FROM sach WHERE tacGia = " + id;
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String leftAlignFormat = "| %-4s | %-30s | %-10s | %-10s |%n";
            System.out.format("+------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "ID", "Ten", "Xuat ban", "Nhap ngay");
            System.out.format("+------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "", "", "", "");
            while (rs.next()) {
                System.out.format(leftAlignFormat, rs.getString("id"), rs.getString("ten"), rs.getString("ngayXuatBan"), rs.getString("ngayNhap"));
            }
            System.out.format("+------+--------------------------------+------------+------------+%n");
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

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public Date getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(Date ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
