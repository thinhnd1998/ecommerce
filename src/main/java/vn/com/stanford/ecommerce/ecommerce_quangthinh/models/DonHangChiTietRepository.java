package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DonHangChiTietRepository extends CrudRepository<DonHangChiTiet, Long> {
    @Query("SELECT u FROM DonHangChiTiet u WHERE u.maSanPham LIKE %:tuKhoa%")
    List<DonHangChiTiet> timKiemDonHangCT(@Param("tuKhoa") String tuKhoa);

    List<DonHangChiTiet> findByMaDonHang(long maDonHang);

    @Transactional
    @Modifying
    @Query("DELETE FROM DonHangChiTiet u WHERE u.maDonHang = :id")
    void xoaByMaDonHang(@Param("id") long id);
}
