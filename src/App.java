import module.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static Connection c = null;

    public static void main(String[] args)  {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        try {
            cls();
            menu();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        String format = "%2d. %s %n";
        System.out.format(format, 1, "Cai dat CSDL");
        System.out.format(format, 2, "Tac gia");
        System.out.format(format, 3, "Sach");
        System.out.format(format, 4, "Thanh vien");
        System.out.format(format, 5, "Muon sach");
        System.out.format(format, 0, "Thoat");
        System.out.print("Nhap so: ");
        int inp;
        do {
            inp = sc.nextInt();
            cls();
            subMenu(inp);
        } while (inp != 0);
        System.exit(0);
    }

    public static void subMenu(int id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        String format = "%2d. %s %n";
        TacGia tacGia;
        Sach sach;
        ThanhVien thanhVien;
        MuonSach muonSach;
        int inp;
        if (id == 1) {
            cls();
            init();
            menu();
        } else if (id == 2) {
            System.out.println("Menu tac gia");
            System.out.format(format, 1, "Them tac gia");
            System.out.format(format, 2, "Xoa tac gia");
            System.out.format(format, 3, "Danh sach tac gia");
            System.out.format(format, 0, "Tro ve menu chinh");
            System.out.print("Nhap so: ");
            do {
                inp = sc.nextInt();
                switch (inp) {
                    case 1:
                        tacGia = new TacGia();
                        cls();
                        tacGia.themTacGia(c);
                        cls();
                        tacGia.danhSach(c);
                        subMenu(id);
                        break;
                    case 2:
                        tacGia = new TacGia();
                        cls();
                        tacGia.xoaTacGia(c);
                        cls();
                        tacGia.danhSach(c);
                        subMenu(id);
                        break;
                    case 3:
                        tacGia = new TacGia();
                        cls();
                        tacGia.danhSach(c);
                        subMenu(id);
                        break;
                    case 0:
                        cls();
                        menu();
                        break;
                    default:
                        cls();
                        subMenu(id);
                        break;
                }
            } while (inp != 0);
        } else if (id == 3) {
            System.out.println("Menu sach");
            System.out.format(format, 1, "Them sach");
            System.out.format(format, 2, "Xoa sach");
            System.out.format(format, 3, "Danh sach sach");
            System.out.format(format, 0, "Tro ve menu chinh");
            System.out.print("Nhap so: ");
            do {
                inp = sc.nextInt();
                switch (inp) {
                    case 1:
                        sach = new Sach();
                        cls();
                        sach.themSach(c);
                        cls();
                        sach.danhSach(c);
                        subMenu(id);
                        break;
                    case 2:
                        sach = new Sach();
                        cls();
                        sach.xoaSach(c);
                        cls();
                        sach.danhSach(c);
                        subMenu(id);
                        break;
                    case 3:
                        sach = new Sach();
                        cls();
                        sach.danhSach(c);
                        subMenu(id);
                        break;
                    case 0:
                        cls();
                        menu();
                        break;
                    default:
                        cls();
                        subMenu(id);
                        break;
                }
            } while (inp != 0);
        } else if (id == 4) {
            System.out.println("Menu thanh vien");
            System.out.format(format, 1, "Them thanh vien");
            System.out.format(format, 2, "Xoa thanh vien");
            System.out.format(format, 3, "Danh sach thanh vien");
            System.out.format(format, 0, "Tro ve menu chinh");
            System.out.print("Nhap so: ");
            do {
                inp = sc.nextInt();
                switch (inp) {
                    case 1:
                        thanhVien = new ThanhVien();
                        cls();
                        thanhVien.themThanhVien(c);
                        cls();
                        thanhVien.danhSach(c);
                        subMenu(id);
                        break;
                    case 2:
                        thanhVien = new ThanhVien();
                        cls();
                        thanhVien.xoaThanhVien(c);
                        cls();
                        thanhVien.danhSach(c);
                        subMenu(id);
                        break;
                    case 3:
                        thanhVien = new ThanhVien();
                        cls();
                        thanhVien.danhSach(c);
                        subMenu(id);
                        break;
                    case 0:
                        cls();
                        menu();
                        break;
                    default:
                        cls();
                        subMenu(id);
                        break;
                }
            } while (inp != 0);
        } else if (id == 5) {
            System.out.println("Menu muon sach");
            System.out.format(format, 1, "Them muon sach");
            System.out.format(format, 2, "Xoa muon sach");
            System.out.format(format, 3, "Danh sach muon sach");
            System.out.format(format, 4, "Chi tiet muon sach");
            System.out.format(format, 0, "Tro ve menu chinh");
            System.out.print("Nhap so: ");
            do {
                inp = sc.nextInt();
                switch (inp) {
                    case 1:
                        muonSach = new MuonSach();
                        cls();
                        muonSach.themMuonSach(c);
                        cls();
                        muonSach.danhSach(c);
                        subMenu(id);
                        break;
                    case 2:
                        muonSach = new MuonSach();
                        cls();
                        muonSach.xoaMuonSach(c);
                        cls();
                        muonSach.danhSach(c);
                        subMenu(id);
                        break;
                    case 3:
                        muonSach = new MuonSach();
                        cls();
                        muonSach.danhSach(c);
                        subMenu(id);
                        break;
                    case 4:
                        muonSach = new MuonSach();
                        cls();
                        muonSach.chiTiet(c);
                        subMenu(id);
                        break;
                    case 0:
                        cls();
                        menu();
                        break;
                    default:
                        cls();
                        subMenu(id);
                        break;
                }
            } while (inp != 0);
        } else if (id == 0) {
            cls();
            System.out.println("Thoat chuong trinh...");
        } else {
            cls();
            menu();
            //System.out.print("Nhap lai so: ");
        }
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        try {
            System.out.println("------------------------------------------------------");
            System.out.println("Ket noi CSDL...");
            System.out.println("Tao cau truc...");
            Statement stmt = c.createStatement();
            String sql = Files.readString(Path.of("library.sql"));
//            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Hoan thanh!");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
