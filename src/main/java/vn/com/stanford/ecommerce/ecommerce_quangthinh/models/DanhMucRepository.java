package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DanhMucRepository extends CrudRepository<DanhMuc, Integer> {
    @Query("SELECT u FROM DanhMuc u WHERE u.tenDanhMuc LIKE %:tuKhoa%")
    public List<DanhMuc> timKiemDanhMuc(@Param("tuKhoa") String tuKhoa);
}
