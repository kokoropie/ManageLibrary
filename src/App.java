import module.*;

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
        input();
    }

    public static void input() {
        try {
            Scanner sc = new Scanner(System.in);
            menu();
            int inp;
            TacGia tacGia;
            Sach sach;
            do {
                inp = sc.nextInt();
                switch (inp) {
                    case 1:
                        init();
                        menu();
                        break;
                    case 2:
                        tacGia = new TacGia();
                        tacGia.themTacGia(c);
                        menu();
                        break;
                    case 3:
                        tacGia = new TacGia();
                        tacGia.danhSach(c);
                        menu();
                        break;
                    case 4:
                        sach = new Sach();
                        sach.themSach(c);
                        menu();
                        break;
                    case 5:
                        sach = new Sach();
                        sach.danhSach(c);
                        menu();
                        break;
                    case 0:
                        System.out.println("Thoat chuong trinh...");
                        break;
                    default:
                        System.out.print("Nhap lai so: ");
                }
            } while (inp != 0);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public static void menu() {
        System.out.println("------------------------------------------------------");
        System.out.println("1. Cai dat CSDL");
        System.out.println("2. Them tac gia");
        System.out.println("3. Danh sach tac gia");
        System.out.println("4. Them sach");
        System.out.println("5. Danh sach sach");
        System.out.println("0. Thoat ");
        System.out.print("Nhap so: ");
    }

    public static void init() {
        try {
            System.out.println("------------------------------------------------------");
            System.out.println("Ket noi CSDL...");
            System.out.println("Tao cau truc...");
            Statement stmt = c.createStatement();
            String sql = Files.readString(Path.of("library.sql"));
            stmt.execute(sql);
            stmt.close();
            stmt = null;
            System.out.println("Hoan thanh!");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
