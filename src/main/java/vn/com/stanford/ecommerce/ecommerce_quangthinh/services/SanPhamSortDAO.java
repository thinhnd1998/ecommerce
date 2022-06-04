package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPham;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPhamExtend;

public interface SanPhamSortDAO extends PagingAndSortingRepository<SanPham, String> {

}
