package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import javax.persistence.*;

@Entity
@Table(name = "PHAN_QUYEN")
public class PhanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PHANQUYEN")
    @SequenceGenerator(name = "SEQ_PHANQUYEN", sequenceName = "SEQ_PHANQUYEN", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "VAI_TRO_ID")
    private int vaiTroId;

    @Column(name = "CHUC_NANG_ID")
    private int chucNangId;

    @Column(name = "XEM")
    private int xem;

    @Column(name = "THEM")
    private int them;

    @Column(name = "SUA")
    private int sua;

    @Column(name = "XOA")
    private int xoa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVaiTroId() {
        return vaiTroId;
    }

    public void setVaiTroId(int vaiTroId) {
        this.vaiTroId = vaiTroId;
    }

    public int getChucNangId() {
        return chucNangId;
    }

    public void setChucNangId(int chucNangId) {
        this.chucNangId = chucNangId;
    }

    public int getXem() {
        return xem;
    }

    public void setXem(int xem) {
        this.xem = xem;
    }

    public int getThem() {
        return them;
    }

    public void setThem(int them) {
        this.them = them;
    }

    public int getSua() {
        return sua;
    }

    public void setSua(int sua) {
        this.sua = sua;
    }

    public int getXoa() {
        return xoa;
    }

    public void setXoa(int xoa) {
        this.xoa = xoa;
    }
}
