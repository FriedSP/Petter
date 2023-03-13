

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		if(session.getAttribute("name") != null) {
			request.setAttribute("name", String.valueOf(session.getAttribute("name")));
			request.setAttribute("address", String.valueOf(session.getAttribute("address")));
			request.setAttribute("pass", String.valueOf(session.getAttribute("pass")));
		}else {
			request.setAttribute("name", "");
			request.setAttribute("address", "");
			request.setAttribute("pass", "");
		}
		request.getRequestDispatcher("regist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String pass = request.getParameter("pass");
		String conPass = request.getParameter("conPass");

		if(name.equals("") || address.equals("") || pass.equals("") || conPass.equals("")) {
			request.setAttribute("error", "未入力の項目があります");

			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("pass", pass);

			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}
		if(pass.length() > 10) {
			request.setAttribute("error", "パスワードは10文字以内の英数字で入力してください。");

			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("pass", pass);

			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}
		if(!(pass.equals(conPass))) {
			request.setAttribute("error", "パスワード確認が一致しません。");

			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("pass", pass);

			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}

		session.setAttribute("name", name);
		session.setAttribute("address", address);
		session.setAttribute("pass", pass);

		response.sendRedirect("confirm");

	}

}
