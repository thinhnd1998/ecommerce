package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThuongHieuRepository extends CrudRepository<ThuongHieu, Integer> {
    @Query("SELECT u FROM ThuongHieu u WHERE u.tenThuongHieu LIKE %:tuKhoa%")
    public List<ThuongHieu> timKiemThuongHieu(@Param("tuKhoa") String tuKhoa);
}
