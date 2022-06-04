package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ChucNang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ChucNangRepository;

import java.util.List;

@Service("chucNangDao")
public class ChucNangImpl implements ChucNangDao {
    @Autowired
    ChucNangRepository chucNangRepository;

    @Override
    public List<ChucNang> layDanhSach() {
        return (List<ChucNang>) chucNangRepository.findAll();
    }

    @Override
    public ChucNang layChiTietTheoMa(Object id) {
        return null;
    }

    @Override
    public boolean themMoi(ChucNang obj) {
        return false;
    }

    @Override
    public boolean capNhat(ChucNang obj) {
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        return false;
    }
}
