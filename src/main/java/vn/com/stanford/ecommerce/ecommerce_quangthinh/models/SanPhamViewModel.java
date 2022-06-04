package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import java.util.Date;

public class SanPhamViewModel {
    private String tuKhoa;
    private int danhMucId;
    private int thuongHieuId;
    private Date startDate;
    private Date endDate;
    private int duyet;

    public String getTuKhoa() {
        return tuKhoa;
    }

    public void setTuKhoa(String tuKhoa) {
        this.tuKhoa = tuKhoa;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuyet() {
        return duyet;
    }

    public void setDuyet(int duyet) {
        this.duyet = duyet;
    }
}
