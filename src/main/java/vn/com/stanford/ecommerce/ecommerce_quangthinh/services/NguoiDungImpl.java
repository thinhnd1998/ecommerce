package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NguoiDung;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NguoiDungExtend;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NguoiDungRepository;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPhamExtend;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service("nguoiDungDao")
public class NguoiDungImpl implements NguoiDungDao {
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public NguoiDungImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    EntityManager entityManager;

    @Override
    public List<NguoiDungExtend> layDanhSachExtend() {
        List<NguoiDungExtend> lstNguoiDung = new ArrayList<>();

        Query query = entityManager.createQuery("SELECT nd.hoTen, nd.tenDangNhap, nd.matKhau, nd.dienThoai, nd.email, nd.diaChi, vt.tenVaiTro, nd.id " +
                "FROM NguoiDung nd inner join VaiTro vt ON nd.vaiTroID = vt.id");

        List<Object[]> lstObject = query.getResultList();

        for (Object[] x : lstObject) {
            NguoiDungExtend objNguoiDung = new NguoiDungExtend();

            objNguoiDung.setHoTen(x[0] + "");
            objNguoiDung.setTenDangNhap(x[1] + "");
            objNguoiDung.setMatKhau(x[2] + "");
            objNguoiDung.setDienThoai(x[3] + "");
            objNguoiDung.setEmail(x[4] + "");
            objNguoiDung.setDiaChi(x[5] + "");
            objNguoiDung.setVaiTro(x[6] + "");
            objNguoiDung.setId(Long.parseLong(x[7] + ""));

            lstNguoiDung.add(objNguoiDung);
        }
        return lstNguoiDung;
    }

    @Override
    public List<NguoiDung> layDanhSach() {
        return (List<NguoiDung>) nguoiDungRepository.findAll();
    }

    @Override
    public NguoiDung layChiTietTheoMa(Object id) {
        NguoiDung objNguoiDung = null;
        try {
            objNguoiDung = nguoiDungRepository.findById(Integer.parseInt(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objNguoiDung = null;
        }
        return objNguoiDung;
    }

    @Override
    public List<NguoiDungExtend> timKiemNguoiDung(String tuKhoa, int vaiTroId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT nd.id, nd.ho_Ten, nd.ten_Dang_Nhap, nd.mat_Khau, nd.dien_Thoai, nd.email, nd.dia_Chi, vt.ten_Vai_Tro as vaiTro FROM Nguoi_Dung nd inner join Vai_Tro vt ON nd.VAI_TRO_ID = vt.id ");
        if (tuKhoa != null) {
            builder.append("AND (nd.ho_Ten like '%' || :tuKhoa || '%' " +
                    "OR nd.ten_Dang_Nhap like '%' || :tuKhoa || '%' " +
                    "OR nd.dien_Thoai like '%' || :tuKhoa || '%' " +
                    "OR nd.email like '%' || :tuKhoa || '%' " +
                    "OR nd.dia_Chi like '%' || :tuKhoa || '%') ");
        }
        if (vaiTroId != 0) {
            builder.append("AND nd.VAI_TRO_ID = :vaiTroId ");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuKhoa", tuKhoa);
        params.addValue("vaiTroId", vaiTroId);
        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(NguoiDungExtend.class));
    }

    @Override
    public boolean themMoi(NguoiDung obj) {
        NguoiDung objNguoiDung = nguoiDungRepository.save(obj);

        if (objNguoiDung != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(NguoiDung obj) {
        NguoiDung objNguoiDung = nguoiDungRepository.findById(obj.getId()).get();

        if (objNguoiDung != null) {
            objNguoiDung.setTenDangNhap(obj.getTenDangNhap());
            nguoiDungRepository.save(objNguoiDung);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        NguoiDung objNguoiDung = layChiTietTheoMa(id);

        if (objNguoiDung != null) {
            nguoiDungRepository.delete(objNguoiDung);
            return true;
        }
        return false;
    }

    @Override
    public NguoiDung layNguoiDungTheoUsername(String tenDN) {
        List<NguoiDung> lstNguoiDung = nguoiDungRepository.findByTenDangNhap(tenDN);

        if (lstNguoiDung != null && lstNguoiDung.size() > 0) {
            return lstNguoiDung.get(0);
        }

        return null;
    }
}
