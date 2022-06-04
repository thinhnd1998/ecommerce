package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

    @Entity
    @Table(name = "NGUOI_DUNG")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NGUOIDUNG")
    @SequenceGenerator(name = "SEQ_NGUOIDUNG", sequenceName = "SEQ_NGUOIDUNG", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "TEN_DANG_NHAP", length = 20)
    private String tenDangNhap;

    @Column(name = "MAT_KHAU", length = 20)
    private String matKhau;

    @Column(name = "HO_TEN", length = 30)
    private String hoTen;

    @Column(name = "DIEN_THOAI", length = 20)
    private String dienThoai;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "DIA_CHI", length = 150)
    private String diaChi;

    @Column(name = "VAI_TRO_ID")
    private int vaiTroID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getVaiTroID() {
        return vaiTroID;
    }

    public void setVaiTroID(int vaiTroID) {
        this.vaiTroID = vaiTroID;
    }
}
