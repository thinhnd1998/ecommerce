package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NhanVienRepository extends CrudRepository<NhanVien, Integer> {
    @Query("SELECT u FROM NhanVien u WHERE u.hoTen LIKE %:tuKhoa% " +
            "OR u.diaChi LIKE %:tuKhoa% " +
            "OR u.dienThoai LIKE %:tuKhoa% " +
            "OR u.email LIKE %:tuKhoa%")
    public List<NhanVien> timKiemNhanVien(@Param("tuKhoa") String tuKhoa);
}
