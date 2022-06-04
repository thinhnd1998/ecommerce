package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KhachHangRepository extends CrudRepository<KhachHang, Integer> {
    @Query("SELECT u FROM KhachHang u WHERE u.hoTen LIKE %:tuKhoa% " +
            "OR u.diaChi LIKE %:tuKhoa% " +
            "OR u.dienThoai LIKE %:tuKhoa% " +
            "OR u.email LIKE %:tuKhoa%")
    public List<KhachHang> timKiemKhachHang(@Param("tuKhoa") String tuKhoa);
}
