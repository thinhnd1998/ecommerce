package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ChucNang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.PhanQuyen;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.VaiTro;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.ChucNangDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.PhanQuyenDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.VaiTroDao;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("call-api")
public class PhanQuyenCallAPI {
    @Autowired
    PhanQuyenDao phanQuyenDao;

    @RequestMapping("phanquyen/view")
    public String hienThiPhanQuyen(Model model, HttpSession session) {
        model.addAttribute("lstQuyenTheoVaiTro", new PhanQuyen());
        model.addAttribute("lstQuyenChuaPhan", new PhanQuyen());
        model.addAttribute("QL_PHANQUYEN_XEM", session.getAttribute("QL_PHANQUYEN_XEM"));

        if (Integer.parseInt(session.getAttribute("QL_PHANQUYEN_XEM") + "") == 1) {
            return "PhanQuyen";
        } else {
            return "AuthErr";
        }
    }

    @GetMapping("/phanquyen/{id}")
    public String layChiTietQuyenCuaVaiTro(@PathVariable("id") int id, Model model, HttpSession session) {
        List<PhanQuyen> lstQuyenTheoVaiTro = phanQuyenDao.layChiTietTheoVaiTroId(id);
        List<Integer> lstQuyenDaPhan = new ArrayList<>();
        for (int i = 0; i < lstQuyenTheoVaiTro.size(); i++) {
            lstQuyenDaPhan.add(lstQuyenTheoVaiTro.get(i).getChucNangId());
        }

        List<PhanQuyen> lstQuyenChuaPhan = new ArrayList<>();

        if (lstQuyenDaPhan.size() == 0) {
            lstQuyenChuaPhan = phanQuyenDao.layChiTietTheoVaiTroId(1);
        } else {
            lstQuyenChuaPhan = phanQuyenDao.listQuyenChuaPhan(lstQuyenDaPhan);
        }


        model.addAttribute("lstQuyenTheoVaiTro", lstQuyenTheoVaiTro);
        model.addAttribute("lstQuyenChuaPhan", lstQuyenChuaPhan);

        model.addAttribute("QL_PHANQUYEN_XEM", session.getAttribute("QL_PHANQUYEN_XEM"));

        if (Integer.parseInt(session.getAttribute("QL_PHANQUYEN_XEM") + "") == 1) {
            return "PhanQuyen";
        } else {
            return "AuthErr";
        }
    }

    @Autowired
    VaiTroDao vaiTroDao;

    @ModelAttribute("lstVaiTro")
    public List<VaiTro> danhSachVaiTro() {
        return vaiTroDao.layDanhSach();
    }

    @Autowired
    ChucNangDao chucNangDao;

    @ModelAttribute("lstChucNang")
    public List<ChucNang> danhSachChucNang() {
        return chucNangDao.layDanhSach();
    }
}
