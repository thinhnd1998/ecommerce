package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

@Entity
@Table(name = "ANH_SAN_PHAM")
public class AnhSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ANHSANPHAM")
    @SequenceGenerator(name = "SEQ_ANHSANPHAM", sequenceName = "SEQ_ANHSANPHAM", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "TEN_ANH", length = 50)
    private String tenAnh;

    @Column(name = "MA_SAN_PHAM", length = 10)
    private String maSanPham;

    @Column(name = "MO_TA", length = 100)
    private String moTa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}

