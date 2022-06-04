package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnhSanPhamRepository extends CrudRepository<AnhSanPham, Long> {
    List<AnhSanPham> findByMaSanPham(String maSanPham);

    AnhSanPham findByMaSanPhamAndMoTa(String maSanPham, String tenAnh);

    @Query("SELECT u FROM AnhSanPham u WHERE u.tenAnh LIKE %:tuKhoa% OR u.maSanPham LIKE %:tuKhoa%")
    List<AnhSanPham> timKiemAnh(@Param("tuKhoa") String tuKhoa);

    @Transactional
    @Modifying
    @Query("DELETE FROM AnhSanPham u WHERE u.maSanPham = :maSP")
    void xoaByMaDonHang(@Param("maSP") String maSP);
}
