package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHang;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("donHangDao")
public class DonHangImpl implements DonHangDao {
    @Autowired
    DonHangRepository donHangRepository;

    @Autowired
    EntityManager entityManager;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DonHangImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DonHangExtend> layDanhSachExtend() {
        List<DonHangExtend> lstDonHang = new ArrayList<>();

        Query query = entityManager.createQuery("SELECT dh.id, dh.tenDonHang, dh.diaChi, dh.dienThoai, dh.ngayDat, dh.ghiChu, dh.trangThai, kh.hoTen, nv.hoTen " +
                "FROM DonHang dh inner join NguoiDung kh ON dh.maKhachHang = kh.id inner join NguoiDung nv ON dh.maNhanVien = nv.id");

        List<Object[]> lstObject = query.getResultList();

        for (Object[] x : lstObject) {
            DonHangExtend objDonHang = new DonHangExtend();

            objDonHang.setId(Long.parseLong(x[0] + ""));
            objDonHang.setTenDonHang(x[1] + "");
            objDonHang.setDiaChi(x[2] + "");
            objDonHang.setDienThoai(x[3] + "");
            objDonHang.setNgayDat((Date) x[4]);
            objDonHang.setGhiChu(x[5] + "");
            objDonHang.setTrangThai(x[6] + "");
            objDonHang.setTenKhachHang(x[7] + "");
            objDonHang.setTenNhanVien(x[8] + "");

            lstDonHang.add(objDonHang);
        }
        return lstDonHang;
    }

    @Override
    public List<DonHang> layDanhSach() {
        return (List<DonHang>) donHangRepository.findAll();
    }

    @Override
    public List<DonHang> layDanhSachTheoMaKH(int maKH) {
        return donHangRepository.findByMaKhachHang(maKH);
    }

    @Override
    public DonHang layChiTietTheoMa(Object id) {
        DonHang objDonHang = null;
        try {
            objDonHang = donHangRepository.findById(Long.parseLong(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objDonHang = null;
        }
        return objDonHang;
    }

    @Override
    public List<DonHangExtend> timKiemDonHang(String tuKhoa) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT dh.id, dh.ten_Don_Hang, dh.dia_Chi, dh.dien_Thoai, dh.ngay_Dat, dh.ghi_Chu, dh.trang_Thai, kh.ho_Ten as tenKhachHang, nv.ho_Ten as tenNhanVien " +
                "FROM Don_Hang dh inner join Khach_Hang kh ON dh.ma_Khach_Hang = kh.id inner join Nhan_Vien nv ON dh.ma_Nhan_Vien = nv.id ");
        if (tuKhoa != null) {
            builder.append("AND (dh.ten_Don_Hang like '%' || :tuKhoa || '%' " +
                    "OR dh.dia_Chi like '%' || :tuKhoa || '%' " +
                    "OR dh.dien_Thoai like '%' || :tuKhoa || '%' " +
                    "OR dh.ngay_Dat like '%' || :tuKhoa || '%' " +
                    "OR nv.ho_Ten like '%' || :tuKhoa || '%' " +
                    "OR kh.ho_Ten like '%' || :tuKhoa || '%') ");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuKhoa", tuKhoa);
        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(DonHangExtend.class));
    }

    @Override
    public boolean themMoi(DonHang obj) {
        DonHang objDonHang = donHangRepository.save(obj);

        if (objDonHang != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(DonHang obj) {
        DonHang objDonHang = donHangRepository.findById(obj.getId()).get();

        if (objDonHang != null) {
            objDonHang.setTenDonHang(obj.getTenDonHang());
            objDonHang.setDiaChi(obj.getDiaChi());
            objDonHang.setDienThoai(obj.getDienThoai());
            objDonHang.setNgayDat(obj.getNgayDat());
            objDonHang.setGhiChu(obj.getGhiChu());
            objDonHang.setTrangThai(obj.getTrangThai());
            objDonHang.setMaKhachHang(obj.getMaKhachHang());
            objDonHang.setMaNhanVien(obj.getMaNhanVien());
            donHangRepository.save(objDonHang);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        DonHang objDonHang = layChiTietTheoMa(id);

        if (objDonHang != null) {
            donHangRepository.delete(objDonHang);
            return true;
        }
        return false;
    }
}
