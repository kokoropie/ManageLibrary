package module;

import java.util.Date;

public class ThanhVien {
    private int id;
    private char ten;
    private Date ngayThamGia;

    public ThanhVien(int id, char ten, Date ngayThamGia) {
        this.id = id;
        this.ten = ten;
        this.ngayThamGia = ngayThamGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getTen() {
        return ten;
    }

    public void setTen(char ten) {
        this.ten = ten;
    }

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }
}
