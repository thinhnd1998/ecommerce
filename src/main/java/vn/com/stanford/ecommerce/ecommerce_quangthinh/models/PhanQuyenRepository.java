package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhanQuyenRepository extends CrudRepository<PhanQuyen, Long> {
    PhanQuyen findByVaiTroIdAndAndChucNangId(int vaiTroId, int chucNangId);
    List<PhanQuyen> findByChucNangIdNotIn(List<Integer> lstChucNangId);
    List<PhanQuyen> findByVaiTroId(int vaiTroId);
}
