package module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ThanhVien {
    private int id;
    private String ten;
    private Date ngayThamGia;

    public ThanhVien () {}

    public ThanhVien (int id, Connection c) {
        setId(id);
        try {
            Scanner sc = new Scanner(System.in);
            String sql = "SELECT * FROM thanhVien WHERE id = " + id + " LIMIT 1";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                setTen(rs.getString("ten"));
                setNgayThamGia((new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("ngayThamGia")));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public ThanhVien(int id, String ten, Date ngayThamGia) {
        this.id = id;
        this.ten = ten;
        this.ngayThamGia = ngayThamGia;
    }

    public void themThanhVien (Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Nhap thong tin thanh vien");
            System.out.print("Nhap ten: ");
            setTen(sc.nextLine());
            System.out.print("Nhap ngay tham gia (dd/MM/yyyy): ");
            setNgayThamGia((new SimpleDateFormat("dd/MM/YYYY")).parse(sc.next()));

            String sql = "INSERT INTO `thanhVien` (`ten`, `ngayThamGia`) VALUES ('" + getTen() + "', '"
                    + (new SimpleDateFormat("yyyy-MM-dd")).format(getNgayThamGia()) +
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

    public void xoaThanhVien (Connection c) {
        Scanner sc = new Scanner(System.in);
        this.danhSach(c);
        System.out.print("Nhap ID thanh vien: ");
        xoa(sc.nextInt(), c);
    }

    public void xoa (int id, Connection c) {
        try {
            String sql = "DELETE FROM tacGia WHERE id = " + id;
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            (new MuonSach()).xoaThanhVien(id, c);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void danhSach (Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Danh sach thanh vien");
            String sql = "SELECT * FROM thanhVien";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String leftAlignFormat = "| %-4s | %-30s | %-4s | %-10s |%n";
            System.out.format("+------+--------------------------------+------+------------+%n");
            System.out.format(leftAlignFormat, "ID", "Ten", "Muon", "Tham gia");
            System.out.format("+------+--------------------------------+------+------------+%n");
            System.out.format(leftAlignFormat, "", "", "", "");
            while (rs.next()) {
                String sql1 = "SELECT COUNT(*) AS `count` FROM `muonSach` WHERE `thanhVien` = " + rs.getString("id");
                Statement stmt1 = c.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);
                int count = 0;
                if (rs1.next()) {
                    count = rs1.getInt("count");
                }
                System.out.format(leftAlignFormat, rs.getString("id"), rs.getString("ten"), count, rs.getString("ngayThamGia"));
            }
            System.out.format("+------+--------------------------------+------+------------+%n");
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

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }
}
