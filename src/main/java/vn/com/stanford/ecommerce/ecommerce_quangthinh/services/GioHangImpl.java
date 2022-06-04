package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.GioHang;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service("gioHangDao")
@SessionScope
public class GioHangImpl implements GioHangDao {
    Map<String, GioHang> maps = new HashMap<>(); //giỏ hàng

    @Override
    public void add(GioHang item) {
        GioHang cartItem = maps.get(item.getMaSanPham());

        if (cartItem == null) {
            maps.put(item.getMaSanPham(), item);
        } else {
            cartItem.setSoLuong(cartItem.getSoLuong() + item.getSoLuong());
        }
    }

    @Override
    public void remove(String maSanPham) {
        maps.remove(maSanPham);
    }

    @Override
    public GioHang update(String maSanPham, int soLuong) {
        GioHang cartItem = maps.get(maSanPham);
        cartItem.setSoLuong(soLuong);
        return cartItem;
    }

    @Override
    public void clear() {
        maps.clear();
    }

    @Override
    public Collection<GioHang> getAllItems() {
        return maps.values();
    }

    @Override
    public int getCount() {
        return maps.values().size();
    }

    @Override
    public double getTotal() {
        return maps.values().stream()
                .mapToDouble(item -> item.getSoLuong() * item.getGiaBan())
                .sum();
    }
}
