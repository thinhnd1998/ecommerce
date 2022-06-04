package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPham;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPhamExtend;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPhamRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("sanPhamDao")
public class SanPhamImpl implements SanPhamDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SanPhamImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SanPhamSortDAO sanPhamSortDAO;

    @Override
    public List<SanPhamExtend> layDanhSachExtend() {
        List<SanPhamExtend> lstSanPham = new ArrayList<>();

        Query query = entityManager.createQuery("SELECT sp.maSanPham, sp.tenSanPham, sp.anhSanPham, sp.giaBan, dm.tenDanhMuc, th.tenThuongHieu, " +
                "nd.hoTen as nguoiTao, sp.ngayTao, sp.ngayCapNhat, sp.ngayDuyet, nd2.hoTen as nguoiDuyet, sp.duyet, sp.cpu, sp.ram, sp.dungLuongPin, sp.manHinh, sp.heDieuHanh FROM SanPham sp " +
                        "inner join DanhMuc dm ON sp.danhMucId = dm.id " +
                        "inner join ThuongHieu th ON sp.thuongHieuId = th.id " +
                        "inner join NguoiDung nd ON sp.nguoiTao = nd.id " +
                        "inner join NguoiDung nd2 ON sp.nguoiDuyet = nd2.id");

        List<Object[]> lstObject = query.getResultList();

        for (Object[] x : lstObject) {
            SanPhamExtend objSanPham = new SanPhamExtend();

            objSanPham.setMaSanPham(x[0] + "");
            objSanPham.setTenSanPham(x[1] + "");
            objSanPham.setAnhSanPham(x[2] + "");
            objSanPham.setGiaBan(Double.parseDouble(x[3] + ""));
            objSanPham.setTenDanhMuc(x[4] + "");
            objSanPham.setTenThuongHieu(x[5] + "");
            objSanPham.setNguoiTao(x[6] + "");
            objSanPham.setNgayTao((Date) x[7]);
            objSanPham.setNgayCapNhat((Date) x[8]);
            objSanPham.setNgayDuyet((Date) x[9]);
            objSanPham.setNguoiDuyet(x[10] + "");
            if (Integer.parseInt(x[11]+"") == 0) {
                objSanPham.setTrangThai("Chưa duyệt");
            } else {
                objSanPham.setTrangThai("Đã duyệt");
            }
            objSanPham.setCpu(x[12]+"");
            objSanPham.setRam(x[13]+"");
            objSanPham.setDungLuongPin(x[14]+"");
            objSanPham.setManHinh(x[15]+"");
            objSanPham.setHeDieuHanh(x[16]+"");

            lstSanPham.add(objSanPham);
        }
        return lstSanPham;
    }

    @Override
    public SanPhamExtend layChiTietTheoMaExtend(String id) {
        List<SanPhamExtend> lstSanPham = layDanhSachExtend();
        SanPhamExtend objSanPham = null;
        for (SanPhamExtend sp : lstSanPham) {
            if (sp.getMaSanPham().equals(id)) {
                objSanPham = sp;
            }
        }
        return objSanPham;
    }

    @Override
    public List<SanPham> layDanhSachTheoDanhMuc(int danhMucId) {
        List<SanPham> lstSanPham = sanPhamRepository.findByDanhMucId(danhMucId);
        return lstSanPham;
    }

    @Override
    public List<SanPham> layDanhSachTheoThuongHieu(int thuongHieuId) {
        List<SanPham> lstSanPham = sanPhamRepository.findByThuongHieuId(thuongHieuId);
        return lstSanPham;
    }

    @Override
    public List<SanPham> layDanhSach() {
        return (List<SanPham>) sanPhamRepository.findAll();
    }

    @Override
    public SanPham layChiTietTheoMa(Object id) {
        SanPham objSanPham = null;
        try {
            objSanPham = sanPhamRepository.findById(id + "").get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objSanPham = null;
        }
        return objSanPham;
    }

    @Override
    public List<SanPhamExtend> timKiemSanPham(String tuKhoa, int danhMucId, int thuongHieuId, Date startDate, Date endDate, int duyet) {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT sp.ma_san_pham, sp.ten_san_pham, sp.anh_san_pham, sp.gia_ban, dm.ten_danh_muc, th.ten_thuong_hieu, nd.ho_ten as nguoi_tao, sp.ngay_tao, sp.ngay_cap_nhat, sp.ngay_duyet, nd2.ho_ten as nguoi_duyet, sp.duyet as trangThai " +
                "FROM SAN_PHAM sp inner join DANH_MUC dm ON sp.danh_muc_id = dm.id " +
                "inner join THUONG_HIEU th ON sp.thuong_hieu_id = th.id " +
                "inner join NGUOI_DUNG nd ON sp.nguoi_duyet = nd.id " +
                "inner join NGUOI_DUNG nd2 ON sp.nguoi_tao = nd2.id WHERE 1=1 ");
        if (tuKhoa != null) {
            builder.append("AND (MA_SAN_PHAM like '%' || :tuKhoa || '%' " +
                    "OR TEN_SAN_PHAM like '%' || :tuKhoa || '%' " +
                    "OR CPU like '%' || :tuKhoa || '%' " +
                    "OR RAM like '%' || :tuKhoa || '%' " +
                    "OR HE_DIEU_HANH like '%' || :tuKhoa || '%') ");
        }
        if (danhMucId != 0) {
            builder.append("AND DANH_MUC_ID = :danhMucId ");
        }
        if (thuongHieuId != 0) {
            builder.append("AND THUONG_HIEU_ID = :thuongHieuId ");
        }
        if (startDate != null && endDate == null) {
            builder.append("AND NGAY_TAO >= :startDate ");
        }
        if (startDate == null && endDate != null) {
            builder.append("AND NGAY_TAO <= :endDate ");
        }
        if (startDate != null && endDate != null) {
            builder.append("AND (NGAY_TAO BETWEEN :startDate AND :endDate) ");
        }
        if (duyet == 0 || duyet == 1) {
            builder.append("AND DUYET = :duyet ");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuKhoa", tuKhoa);
        params.addValue("danhMucId", danhMucId);
        params.addValue("thuongHieuId", thuongHieuId);
        params.addValue("startDate", startDate);
        params.addValue("endDate", endDate);
        params.addValue("duyet", duyet);
        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(SanPhamExtend.class));
    }

    @Override
    public List<SanPham> timKiemSanPhamClient(String tuKhoa) {
        return sanPhamRepository.timKiemSanPhamClient(tuKhoa);
    }

    //    @Override
//    public List<SanPham> timKiemSanPhamTheoNgay(Date startDate, Date endDate) {
//        return sanPhamRepository.findByNgayTaoBetween(startDate, endDate);
//    }

    @Override
    public boolean themMoi(SanPham obj) {
        SanPham objSanPham = sanPhamRepository.save(obj);

        if (objSanPham != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(SanPham obj) {
        SanPham objSanPham = sanPhamRepository.findById(obj.getMaSanPham()).get();

        if (objSanPham != null) {
            objSanPham.setTenSanPham(obj.getTenSanPham());
            objSanPham.setCpu(obj.getCpu());
            objSanPham.setRam(obj.getRam());
            objSanPham.setDungLuongPin(obj.getDungLuongPin());
            objSanPham.setManHinh(obj.getManHinh());
            objSanPham.setHeDieuHanh(obj.getHeDieuHanh());
            objSanPham.setThietKe(obj.getThietKe());
            objSanPham.setBaoHanh(obj.getBaoHanh());
            objSanPham.setThongTinChung(obj.getThongTinChung());
            objSanPham.setAnhSanPham(obj.getAnhSanPham());
            objSanPham.setGiaBan(obj.getGiaBan());
            objSanPham.setDanhMucId(obj.getDanhMucId());
            objSanPham.setThuongHieuId(obj.getThuongHieuId());
            objSanPham.setNgayCapNhat(new Date());

            sanPhamRepository.save(objSanPham);

            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        SanPham objSanPham = layChiTietTheoMa(id);

        if (objSanPham != null) {
            sanPhamRepository.delete(objSanPham);
            return true;
        }
        return false;
    }

    @Override
    public boolean duyetSanPham(Object ma) {
        SanPham objSanPham = layChiTietTheoMa(ma);
        if (objSanPham != null) {
            objSanPham.setDuyet(1);
            sanPhamRepository.save(objSanPham);
            return true;
        }
        return false;
    }



    public List<SanPham> findAll(Sort sort) {
        return (List<SanPham>) sanPhamSortDAO.findAll(sort);
    }

    @Override
    public Page<SanPham> phanTrangSanPham(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 12);
        return sanPhamSortDAO.findAll(pageable);
    }

}
