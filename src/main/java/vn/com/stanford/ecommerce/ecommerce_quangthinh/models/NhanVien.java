package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

@Entity
@Table(name = "NHAN_VIEN")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NHANVIEN")
    @SequenceGenerator(name = "SEQ_NHANVIEN", sequenceName = "SEQ_NHANVIEN", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "HO_TEN", length = 50)
    private String hoTen;

    @Column(name = "DIEN_THOAI", length = 20)
    private String dienThoai;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "DIA_CHI", length = 250)
    private String diaChi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
