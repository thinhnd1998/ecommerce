package vn.com.stanford.ecommerce.ecommerce_quangthinh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("userOnline") != null) {
            System.out.println("Bạn đang đăng nhập với tài khoản: " + session.getAttribute("userOnline"));

            return true;
        } else {
            response.sendRedirect("../dang-nhap");
        }

        return false;
    }
}
