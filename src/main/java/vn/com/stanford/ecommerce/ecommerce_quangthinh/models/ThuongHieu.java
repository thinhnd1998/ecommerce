package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

@Entity
@Table(name = "THUONG_HIEU")
public class ThuongHieu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_THUONGHIEU")
    @SequenceGenerator(name = "SEQ_THUONGHIEU", sequenceName = "SEQ_THUONGHIEU", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "TEN_THUONG_HIEU", length = 20)
    private String tenThuongHieu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }
}
