package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.GioHang;

import java.util.Collection;

public interface GioHangDao {
    void add(GioHang item);
    void remove(String maSanPham);
    GioHang update(String maSanPham, int soLuong);
    void clear();
    Collection<GioHang> getAllItems();
    int getCount();
    double getTotal();
}
