package module;

import java.util.Date;

public class MuonSach {
    private int id;
    private Sach sach;
    private ThanhVien thanhVien;
    private Date ngayMuon;
    private Date ngayTra;

    public MuonSach(int id, Sach sach, ThanhVien thanhVien, Date ngayMuon, Date ngayTra) {
        this.id = id;
        this.sach = sach;
        this.thanhVien = thanhVien;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
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
