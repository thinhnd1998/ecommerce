package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.interceptor.Encryption;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NguoiDung;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.PhanQuyen;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.NguoiDungDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.PhanQuyenDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DangNhapController {
    @RequestMapping(value = "/dang-nhap")
    public String hienThiDangNhapHeThong(Model model) {
        //Truyền đối tượng ra bên ngoài view
        model.addAttribute("user", new NguoiDung());
        return "DangNhap";
    }

    @Autowired
    NguoiDungDao nguoiDungDao;

    @Autowired
    PhanQuyenDao phanQuyenDao;

    @PostMapping(value = "/thucHienDangNhap")
    public String thucHienDangNhap(@ModelAttribute("user") NguoiDung user,
                                   Model model,
                                   HttpSession session) {
        System.out.println("Tên đăng nhập: " + user.getTenDangNhap());
        System.out.println("Mật khẩu: " + user.getMatKhau());

        if (user.getTenDangNhap().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập tên đăng nhập");

            return "DangNhap";
        }
        if (user.getMatKhau().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập mật khẩu");

            return "DangNhap";
        }

        model.addAttribute("user", user);

        NguoiDung objUser = nguoiDungDao.layNguoiDungTheoUsername(user.getTenDangNhap());

        PhanQuyen QL_SANPHAM = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTroID(), 1);
        int QL_SANPHAM_XEM = 0;
        int QL_SANPHAM_THEM = 0;
        int QL_SANPHAM_SUA = 0;
        int QL_SANPHAM_XOA = 0;

        if (QL_SANPHAM != null) {
            QL_SANPHAM_XEM = QL_SANPHAM.getXem();
            QL_SANPHAM_THEM = QL_SANPHAM.getThem();
            QL_SANPHAM_SUA = QL_SANPHAM.getSua();
            QL_SANPHAM_XOA = QL_SANPHAM.getXoa();
        }

        PhanQuyen QL_DONHANG = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTroID(), 5);
        int QL_DONHANG_XEM = 0;
        int QL_DONHANG_THEM = 0;
        int QL_DONHANG_SUA = 0;
        int QL_DONHANG_XOA = 0;
        if (QL_DONHANG != null) {
            QL_DONHANG_XEM = QL_DONHANG.getXem();
            QL_DONHANG_THEM = QL_DONHANG.getThem();
            QL_DONHANG_SUA = QL_DONHANG.getSua();
            QL_DONHANG_XOA = QL_DONHANG.getXoa();
        }

        PhanQuyen QL_THUONGHIEU = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTroID(), 4);
        int QL_THUONGHIEU_XEM = 0;
        int QL_THUONGHIEU_THEM = 0;
        int QL_THUONGHIEU_SUA = 0;
        int QL_THUONGHIEU_XOA = 0;
        if (QL_THUONGHIEU != null) {
            QL_THUONGHIEU_XEM = QL_THUONGHIEU.getXem();
            QL_THUONGHIEU_THEM = QL_THUONGHIEU.getThem();
            QL_THUONGHIEU_SUA = QL_THUONGHIEU.getSua();
            QL_THUONGHIEU_XOA = QL_THUONGHIEU.getXoa();
        }

        PhanQuyen QL_DANHMUC = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTroID(), 3);
        int QL_DANHMUC_XEM = 0;
        int QL_DANHMUC_THEM = 0;
        int QL_DANHMUC_SUA = 0;
        int QL_DANHMUC_XOA = 0;
        if (QL_DANHMUC != null) {
            QL_DANHMUC_XEM = QL_DANHMUC.getXem();
            QL_DANHMUC_THEM = QL_DANHMUC.getThem();
            QL_DANHMUC_SUA = QL_DANHMUC.getSua();
            QL_DANHMUC_XOA = QL_DANHMUC.getXoa();
        }

        PhanQuyen QL_NGUOIDUNG = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTroID(), 8);
        int QL_NGUOIDUNG_XEM = 0;
        int QL_NGUOIDUNG_THEM = 0;
        int QL_NGUOIDUNG_SUA = 0;
        int QL_NGUOIDUNG_XOA = 0;
        if (QL_NGUOIDUNG != null) {
            QL_NGUOIDUNG_XEM = QL_NGUOIDUNG.getXem();
            QL_NGUOIDUNG_THEM = QL_NGUOIDUNG.getThem();
            QL_NGUOIDUNG_SUA = QL_NGUOIDUNG.getSua();
            QL_NGUOIDUNG_XOA = QL_NGUOIDUNG.getXoa();
        }

        PhanQuyen QL_VAITRO = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTroID(), 7);
        int QL_VAITRO_XEM = 0;
        int QL_VAITRO_THEM = 0;
        int QL_VAITRO_SUA = 0;
        int QL_VAITRO_XOA = 0;
        if (QL_VAITRO != null) {
            QL_VAITRO_XEM = QL_VAITRO.getXem();
            QL_VAITRO_THEM = QL_VAITRO.getThem();
            QL_VAITRO_SUA = QL_VAITRO.getSua();
            QL_VAITRO_XOA = QL_VAITRO.getXoa();
        }


        PhanQuyen QL_PHANQUYEN = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTroID(), 9);
        int QL_PHANQUYEN_XEM = 0;
        int QL_PHANQUYEN_THEM = 0;
        int QL_PHANQUYEN_SUA = 0;
        int QL_PHANQUYEN_XOA = 0;
        if (QL_PHANQUYEN != null) {
            QL_PHANQUYEN_XEM = QL_PHANQUYEN.getXem();
            QL_PHANQUYEN_THEM = QL_PHANQUYEN.getThem();
            QL_PHANQUYEN_SUA = QL_PHANQUYEN.getSua();
            QL_PHANQUYEN_XOA = QL_PHANQUYEN.getXoa();
        }

        if (objUser != null) {
            String matKhauDb = objUser.getMatKhau();

            if (Encryption.MD5(user.getMatKhau()).equals(matKhauDb)) {
                //Lưu thông tin vào session
                session.setAttribute("userOnline", objUser.getHoTen());
                session.setAttribute("userId", objUser.getId());

                session.setAttribute("QL_SANPHAM_XEM", QL_SANPHAM_XEM);
                session.setAttribute("QL_SANPHAM_THEM", QL_SANPHAM_THEM);
                session.setAttribute("QL_SANPHAM_SUA", QL_SANPHAM_SUA);
                session.setAttribute("QL_SANPHAM_XOA", QL_SANPHAM_XOA);

                session.setAttribute("QL_DONHANG_XEM", QL_DONHANG_XEM);
                session.setAttribute("QL_DONHANG_THEM", QL_DONHANG_THEM);
                session.setAttribute("QL_DONHANG_SUA", QL_DONHANG_SUA);
                session.setAttribute("QL_DONHANG_XOA", QL_DONHANG_XOA);

                session.setAttribute("QL_THUONGHIEU_XEM", QL_THUONGHIEU_XEM);
                session.setAttribute("QL_THUONGHIEU_THEM", QL_THUONGHIEU_THEM);
                session.setAttribute("QL_THUONGHIEU_SUA", QL_THUONGHIEU_SUA);
                session.setAttribute("QL_THUONGHIEU_XOA", QL_THUONGHIEU_XOA);

                session.setAttribute("QL_DANHMUC_XEM", QL_DANHMUC_XEM);
                session.setAttribute("QL_DANHMUC_THEM", QL_DANHMUC_THEM);
                session.setAttribute("QL_DANHMUC_SUA", QL_DANHMUC_SUA);
                session.setAttribute("QL_DANHMUC_XOA", QL_DANHMUC_XOA);

                session.setAttribute("QL_NGUOIDUNG_XEM", QL_NGUOIDUNG_XEM);
                session.setAttribute("QL_NGUOIDUNG_THEM", QL_NGUOIDUNG_THEM);
                session.setAttribute("QL_NGUOIDUNG_SUA", QL_NGUOIDUNG_SUA);
                session.setAttribute("QL_NGUOIDUNG_XOA", QL_NGUOIDUNG_XOA);

                session.setAttribute("QL_VAITRO_XEM", QL_VAITRO_XEM);
                session.setAttribute("QL_VAITRO_THEM", QL_VAITRO_THEM);
                session.setAttribute("QL_VAITRO_SUA", QL_VAITRO_SUA);
                session.setAttribute("QL_VAITRO_XOA", QL_VAITRO_XOA);

                session.setAttribute("QL_PHANQUYEN_XEM", QL_PHANQUYEN_XEM);
                session.setAttribute("QL_PHANQUYEN_THEM", QL_PHANQUYEN_THEM);
                session.setAttribute("QL_PHANQUYEN_SUA", QL_PHANQUYEN_SUA);
                session.setAttribute("QL_PHANQUYEN_XOA", QL_PHANQUYEN_XOA);

                if (objUser.getVaiTroID() == 1 || objUser.getVaiTroID() == 3 || objUser.getVaiTroID() == 4){
                    return "redirect:/call-api/sanpham/all";
                } else {
                    return "redirect:/trang-chu";
                }
            } else {
                model.addAttribute("thongBao", "Tài khoản không chính xác");
            }
        } else {
            model.addAttribute("thongBao", "Tài khoản không tồn tại");
        }

        return "DangNhap";
    }

    @GetMapping(value = "/dang-xuat")
    public String dangXuat(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("userOnline") != null) {
            session.invalidate();
        }

        return "redirect:/trang-chu";
    }

    @RequestMapping(value = "/dang-ky")
    public String hienThiDangKy(Model model) {
        //Truyền đối tượng ra bên ngoài view
        model.addAttribute("user", new NguoiDung());
        return "DangKy";
    }

    @PostMapping(value = "/thucHienDangKy")
    public String thucHienDangKy(@ModelAttribute("user") NguoiDung user, Model model) {
        if (user.getHoTen().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập họ tên");

            return "DangKy";
        }
        if (user.getDienThoai().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập số điện thoại");

            return "DangKy";
        }
        if (user.getDiaChi().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập địa chỉ");

            return "DangKy";
        }
        if (user.getTenDangNhap().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập tên đăng nhập");

            return "DangKy";
        }
        if (user.getMatKhau().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập tên mật khẩu");

            return "DangKy";
        }

        NguoiDung oldUser = nguoiDungDao.layNguoiDungTheoUsername(user.getTenDangNhap());

        if (oldUser != null) {
            if (user.getEmail().equals(oldUser.getEmail())) {
                model.addAttribute("thongBao", "Địa chỉ email đã được sử dụng");

                return "DangKy";
            }
            model.addAttribute("thongBao", "Tên đăng nhập đã được sử dụng");
            return "DangKy";

        } else {
            String password = user.getMatKhau();
            user.setMatKhau(Encryption.MD5(password));
            user.setVaiTroID(2);
            boolean ketQua = nguoiDungDao.themMoi(user);
            if (ketQua){
                return "redirect:/dang-nhap";
            }
        }
        return "DangKy";
    }
}
