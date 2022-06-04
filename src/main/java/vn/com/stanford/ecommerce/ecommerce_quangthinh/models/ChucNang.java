package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHUC_NANG")
public class ChucNang {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "TEN_CHUC_NANG", length = 255)
    private String tenChucNang;

    @Column(name = "MO_TA", length = 255)
    private String moTa;

    @Column(name = "TEN_FORM", length = 255)
    private String tenForm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenForm() {
        return tenForm;
    }

    public void setTenForm(String tenForm) {
        this.tenForm = tenForm;
    }
}
