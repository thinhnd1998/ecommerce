package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

@Entity
@Table(name = "VAI_TRO")
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VAITRO")
    @SequenceGenerator(name = "SEQ_VAITRO", sequenceName = "SEQ_VAITRO", allocationSize = 1)
    private int id;

    @Column(name = "TEN_VAI_TRO", length = 20)
    private String tenVaiTro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }
}
