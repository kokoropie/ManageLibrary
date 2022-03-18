package module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MuonSach {
    private int id;
    private ThanhVien thanhVien;
    private Sach sach;
    private Date ngayMuon;
    private Date ngayTra;

    public MuonSach() {}

    public MuonSach(int id, Connection c) {
        setId(id);
    }

    public MuonSach(int id, ThanhVien thanhVien, Sach sach, Date ngayMuon, Date ngayTra) {
        this.id = id;
        this.thanhVien = thanhVien;
        this.sach = sach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public void themMuonSach (Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Nhap thong tin muon sach");
            (new ThanhVien()).danhSach(c);
            System.out.print("Nhap ID thanb vien: ");
            setThanhVien(new ThanhVien(sc.nextInt(), c));
            (new Sach()).danhSach(c);
            System.out.print("Nhap ID sach: ");
            setSach(new Sach(sc.nextInt(), c));
            System.out.print("Nhap ngay muon (dd/mm/yyyy): ");
            setNgayMuon((new SimpleDateFormat("dd/MM/yyyy")).parse(sc.next()));
            System.out.print("Nhap ngay tra (dd/mm/yyyy): ");
            setNgayTra((new SimpleDateFormat("dd/MM/yyyy")).parse(sc.next()));

            String sql = "INSERT INTO `muonSach` (`thanhVien`, `sach`, `ngayMuon`, `ngayTra`) VALUES ('" + getThanhVien().getId() + "', '"
                    + getSach().getId() +"','"
                    + (new SimpleDateFormat("yyyy-MM-dd")).format(getNgayMuon()) +"','"
                    + (new SimpleDateFormat("yyyy-MM-dd")).format(getNgayTra()) +
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

    public void xoaMuonSach (Connection c) {
        Scanner sc = new Scanner(System.in);
        this.danhSach(c);
        System.out.print("Nhap ID muon sach: ");
        xoa(sc.nextInt(), c);
    }

    public void xoa (int id, Connection c) {
        try {
            String sql = "DELETE FROM muonSach WHERE id = " + id;
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void xoaTacGia (int id, Connection c) {
        try {
            String sql = "SELECT id FROM sach WHERE tacGia = " + id;
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                this.xoaSach(rs.getInt("id"), c);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void xoaSach(int id, Connection c) {
        try {
            String sql = "DELETE FROM muonSach WHERE sach = " + id;
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void xoaThanhVien(int id, Connection c) {
        try {
            String sql = "DELETE FROM muonSach WHERE thanhVien = " + id;
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void danhSach (Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("Danh sach muon sach");
            String sql = "SELECT * FROM muonSach";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String leftAlignFormat = "| %-4s | %-30s | %-30s | %-10s | %-10s |%n";
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "ID", "Ten thanh vien", "Ten sach", "Ngay muon", "Ngay tra");
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "", "", "", "", "");
            while (rs.next()) {
                ThanhVien thanhVien = new ThanhVien(rs.getInt("thanhVien"), c);
                Sach sach = new Sach (rs.getInt("sach"), c);
                System.out.format(leftAlignFormat, rs.getString("id"), thanhVien.getTen(), sach.getTen(), rs.getString("ngayMuon"), rs.getString("ngayTra"));
            }
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void chiTiet(Connection c) {
        try {
            Scanner sc = new Scanner(System.in);
            (new ThanhVien()).danhSach(c);
            System.out.print("Nhap ID thanh vien: ");
            int id = sc.nextInt();
            System.out.println("------------------------------------------------------");
            System.out.println("Danh sach muon sach");
            String sql = "SELECT * FROM muonSach WHERE thanhVien = " + id;
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String leftAlignFormat = "| %-4s | %-30s | %-30s | %-10s | %-10s |%n";
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "ID", "Ten thanh vien", "Ten sach", "Ngay muon", "Ngay tra");
            System.out.format("+------+--------------------------------+--------------------------------+------------+------------+%n");
            System.out.format(leftAlignFormat, "", "", "", "", "");
            while (rs.next()) {
                ThanhVien thanhVien = new ThanhVien(rs.getInt("thanhVien"), c);
                Sach sach = new Sach (rs.getInt("sach"), c);
                System.out.format(leftAlignFormat, rs.getString("id"), thanhVien.getTen(), sach.getTen(), rs.getString("ngayMuon"), rs.getString("ngayTra"));
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

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }
}
