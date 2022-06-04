package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DON_HANG")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DONHANG")
    @SequenceGenerator(name = "SEQ_DONHANG", sequenceName = "SEQ_DONHANG", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "TEN_DON_HANG", length = 50)
    private String tenDonHang;

    @Column(name = "DIA_CHI", length = 250)
    private String diaChi;

    @Column(name = "DIEN_THOAI", length = 20)
    private String dienThoai;

    @Column(name = "NGAY_DAT")
    @Temporal(TemporalType.DATE)
    private Date ngayDat;

    @Column(name = "GHI_CHU", length = 300)
    private String ghiChu;

    @Column(name = "TRANG_THAI", length = 30)
    private String trangThai;

    @Column(name = "MA_KHACH_HANG")
    private int maKhachHang;

    @Column(name = "MA_NHAN_VIEN")
    private int maNhanVien;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenDonHang() {
        return tenDonHang;
    }

    public void setTenDonHang(String tenDonHang) {
        this.tenDonHang = tenDonHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
