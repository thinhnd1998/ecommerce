package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonHangRepository extends CrudRepository<DonHang, Long> {

    List<DonHang> findByMaKhachHang(int maKH);
}
