package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHang {
    private String maSanPham;
    private String tenSanPham;
    private String anhSanPham;
    private double giaBan;
    private int soLuong = 1;
}
