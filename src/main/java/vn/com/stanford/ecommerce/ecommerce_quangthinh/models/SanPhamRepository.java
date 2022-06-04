package vn.com.stanford.ecommerce.ecommerce_quangthinh.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SanPhamRepository extends CrudRepository<SanPham, String> {
//    @Query("SELECT sp.maSanPham, sp.tenSanPham, sp.cpu, sp.ram, sp.dungLuongPin, sp.manHinh, sp.heDieuHanh, " +
//            "sp.thietKe, sp.baoHanh, sp.thongTinChung, sp.giaBan, dm.tenDanhMuc, th.tenThuongHieu, sp.anhSanPham, " +
//            "nd.hoTen, sp.ngayTao, sp.ngayCapNhat, sp.ngayDuyet, nd2.hoTen, sp.duyet FROM SanPham sp " +
//            "inner join DanhMuc dm ON sp.danhMucId = dm.id " +
//            "inner join ThuongHieu th ON sp.thuongHieuId = th.id " +
//            "inner join NguoiDung nd ON sp.nguoiTao = nd.id " +
//            "inner join NguoiDung nd2 ON sp.nguoiDuyet = nd2.id")
//    List<SanPhamExtend> layDanhSach1();

//    List<SanPham> findByNgayTaoBetween(Date startDate, Date endDate);

    List<SanPham> findByDanhMucId(int danhMucId);
    List<SanPham> findByThuongHieuId(int thuongHieuId);

    @Query("SELECT u FROM SanPham u WHERE u.maSanPham LIKE %:tuKhoa%"
            + " OR u.tenSanPham LIKE %:tuKhoa%"
            + " OR u.ram LIKE %:tuKhoa%"
            + " OR u.manHinh LIKE %:tuKhoa%"
            + " OR u.heDieuHanh LIKE %:tuKhoa%"
            + " OR u.cpu LIKE %:tuKhoa%")
    List<SanPham> timKiemSanPhamClient(@Param("tuKhoa") String tuKhoa);
}
