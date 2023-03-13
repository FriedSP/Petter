

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConfirmServlet
 */
@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("confirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String confirm = request.getParameter("confirm");
		if(confirm.equals("fix")) {
			//修正するを選択した時のリダイレクト
			response.sendRedirect("regist");
		} else {
			//登録を選択した時の登録処理
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String name = String.valueOf(session.getAttribute("name"));
			String address = String.valueOf(session.getAttribute("address"));
			String password = String.valueOf(session.getAttribute("pass"));

		//JDBCドライバーのロード
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection con = null;
			try {
				//コネクションの確立
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petter?characterEncoding=utf8&serverTimezone=JST","root","");

				String sql = "SELECT address FROM accounts WHERE address = ?";
				PreparedStatement smt = con.prepareStatement(sql);
				smt.setString(1, address);
				ResultSet rs = smt.executeQuery();
				rs.last();
				int count = rs.getRow();
				if(count > 0) {
					smt.close();
					rs.close();
					request.setAttribute("error", "既に登録されているメールアドレスです。");
					request.setAttribute("link", "insert.html");
					request.getRequestDispatcher("newsub.jsp").forward(request, response);
					return;
				}else {
					smt.close();

					sql = "INSERT INTO accounts values(?, ?, ?)";
					smt = con.prepareStatement(sql);
					smt.setString(1, address);
					smt.setString(2, password);
					smt.setString(3, name);
					smt.executeUpdate();
				}
				smt.close();
				rs.close();

			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				//コネクションのクローズ
				try {
					if(con != null) con.close();
				} catch (SQLException e) {
				}
			}
			session.removeAttribute("pass");
			session.removeAttribute("address");
			session.removeAttribute("name");

			response.sendRedirect("thanks");
		}
	}

}
