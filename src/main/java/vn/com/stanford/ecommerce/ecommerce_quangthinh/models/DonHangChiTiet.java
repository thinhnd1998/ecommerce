package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

@Entity
@Table(name = "DON_HANG_CHI_TIET")
public class DonHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DONHANGCT")
    @SequenceGenerator(name = "SEQ_DONHANGCT", sequenceName = "SEQ_DONHANGCT", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "DON_GIA")
    private double donGia;

    @Column(name = "SO_LUONG")
    private int soLuong;

    @Column(name = "MA_DON_HANG")
    private long maDonHang;

    @Column(name = "MA_SAN_PHAM")
    private String maSanPham;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(long maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
}
