package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SAN_PHAM")
public class SanPham {
    @Id
    @Column(name = "MA_SAN_PHAM", unique = true, nullable = false, length = 10)
    private String maSanPham;

    @Column(name = "TEN_SAN_PHAM", length = 100)
    private String tenSanPham;

    @Column(name = "CPU", length = 100)
    private String cpu;

    @Column(name = "RAM", length = 100)
    private String ram;

    @Column(name = "DUNG_LUONG_PIN", length = 100)
    private String dungLuongPin;

    @Column(name = "MAN_HINH", length = 100)
    private String manHinh;

    @Column(name = "HE_DIEU_HANH", length = 20)
    private String heDieuHanh;

    @Column(name = "THIET_KE", length = 250)
    private String thietKe;

    @Column(name = "BAO_HANH", length = 30)
    private String baoHanh;

    @Column(name = "THONG_TIN_CHUNG", length = 500)
    private String thongTinChung;

    @Column(name = "ANH_SAN_PHAM", length = 20)
    private String anhSanPham;

    @Column(name = "GIA_BAN")
    private double giaBan;

    @Column(name = "DANH_MUC_ID")
    private int danhMucId;

    @Column(name = "THUONG_HIEU_ID")
    private int thuongHieuId;

    @Column(name = "NGUOI_TAO")
    private Integer nguoiTao;

    @Column(name = "NGAY_TAO")
    @Temporal(TemporalType.DATE)
    private Date ngayTao;

    @Column(name = "NGAY_CAP_NHAT")
    @Temporal(TemporalType.DATE)
    private Date ngayCapNhat;

    @Column(name = "NGAY_DUYET")
    @Temporal(TemporalType.DATE)
    private Date ngayDuyet;

    @Column(name = "NGUOI_DUYET")
    private long nguoiDuyet;

    @Column(name = "DUYET")
    private int duyet;

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDungLuongPin() {
        return dungLuongPin;
    }

    public void setDungLuongPin(String dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public String getThietKe() {
        return thietKe;
    }

    public void setThietKe(String thietKe) {
        this.thietKe = thietKe;
    }

    public String getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(String baoHanh) {
        this.baoHanh = baoHanh;
    }

    public String getThongTinChung() {
        return thongTinChung;
    }

    public void setThongTinChung(String thongTinChung) {
        this.thongTinChung = thongTinChung;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getDanhMucId() {
        return danhMucId;
    }

    public void setDanhMucId(int danhMucId) {
        this.danhMucId = danhMucId;
    }

    public int getThuongHieuId() {
        return thuongHieuId;
    }

    public void setThuongHieuId(int thuongHieuId) {
        this.thuongHieuId = thuongHieuId;
    }

    public Integer getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(Integer nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public Date getNgayDuyet() {
        return ngayDuyet;
    }

    public void setNgayDuyet(Date ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }

    public long getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(long nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }

    public int getDuyet() {
        return duyet;
    }

    public void setDuyet(int duyet) {
        this.duyet = duyet;
    }
}
