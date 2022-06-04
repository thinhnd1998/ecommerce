package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaiTroRepository extends CrudRepository<VaiTro, Integer> {
    @Query("SELECT u FROM VaiTro u WHERE u.tenVaiTro LIKE %:tuKhoa%")
    public List<VaiTro> timKiemVaiTro(@Param("tuKhoa") String tuKhoa);
}
