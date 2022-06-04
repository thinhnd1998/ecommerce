package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import java.util.List;

public interface IHanhDong<T> {
    List<T> layDanhSach();

    T layChiTietTheoMa(Object id);

    boolean themMoi(T obj);

    boolean capNhat(T obj);

    boolean xoa(Object id);
}
