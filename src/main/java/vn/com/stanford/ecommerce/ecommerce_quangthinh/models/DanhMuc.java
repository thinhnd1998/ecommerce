package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

@Entity
@Table(name = "DANH_MUC")
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DANHMUC")
    @SequenceGenerator(name = "SEQ_DANHMUC", sequenceName = "SEQ_DANHMUC", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "TEN_DANH_MUC", length = 50)
    private String tenDanhMuc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
