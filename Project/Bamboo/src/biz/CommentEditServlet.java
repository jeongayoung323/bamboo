package biz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDAO;
import vo.CommentVO;

@WebServlet("/commentEdit")
public class CommentEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentEditServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		CommentVO vo = new CommentVO();
		CommentDAO dao = new CommentDAO();
		String commentId = request.getParameter("commentId");
		String postId = request.getParameter("postId");
		String content = request.getParameter("editTextarea");
		int n = 0;
		
		if (content == null) {
			out.println("<script> alert('입력하지 않은 값이 있습니다.'); history.back(); </script>");
		} else {
			vo.setCommentContents(content);
			n = dao.editComment(content, Integer.parseInt(postId), Integer.parseInt(commentId));
			
			if (n > 0) {
				out.println("<script> alert('댓글이 정상적으로 수정 되었습니다.'); </script>");
				out.println("<script> window.location=document.referrer </script>");
			} else {
				out.println("<script> alert('댓글 수정에 실패하였습니다.'); history.back(); </script>");
			}
		}
	}
}