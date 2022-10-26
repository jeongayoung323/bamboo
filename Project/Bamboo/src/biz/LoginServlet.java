package biz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import vo.MemberVO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getMemberData(id);
		
		if (vo == null || !pwd.equals(vo.getMemberPwd())) {
			out.println("<script> alert('회원정보가 맞지 않습니다.'); history.back(); </script>");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginOK", vo);
			System.out.println(vo);
			response.sendRedirect("./index.jsp");
		}
	}
}