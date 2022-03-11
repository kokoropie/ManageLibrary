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

    public Sach(int id) {
        this.id = id;
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
            System.out.print("Nhap ID tac gia: ");
            setTacGia(new TacGia(sc.nextInt(), c));
            System.out.print("Nhap ngay xuat ban (dd/mm/yyyy): ");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String ngayXuatBan = sc.next();
            setNgayXuatBan(formatter.parse(ngayXuatBan));

            String sql = "INSERT INTO `sach` (`ten`, `tacGia`, `ngayXuatBan`) VALUES ('" + getTen() + "', '"
                    + getTacGia().getId() +"','"
                    + (new SimpleDateFormat("yyyy-MM-dd")).format(getNgayXuatBan()) +
                    "')";
            PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            setId(generatedKey);
            setNgayNhap(new Date());
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void xoaSach(Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.print("Nhap ID sach: ");
            int id = sc.nextInt();
            String sql = "DELETE FROM sach WHERE id = " + id;
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
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
