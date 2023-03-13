

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	//ログイン画面の画像をランダムにするためのメソッド
	private String img () {
		Random rand = new Random();
		int imgNum = rand.nextInt(3);

		HashMap<Integer, String> imgHash = new HashMap<Integer, String>();
		imgHash.put(0, "rogin1.gif");
		imgHash.put(1, "rogin2.jpg");
		imgHash.put(2, "rogin3.jpg");

		return imgHash.get(imgNum);
	}

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("name", "");
		request.setAttribute("pass", "");
		request.setAttribute("img", img());
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String address = "";
		request.setAttribute("img", img());


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

			String sql = "SELECT address FROM accounts WHERE password = ? AND user_name = ?";
			PreparedStatement smt = con.prepareStatement(sql);
			smt.setString(1, pass);
			smt.setString(2, name);
			ResultSet rs = smt.executeQuery();

			if(rs.next()) {
				address = rs.getString("address");
			} else {
				smt.close();
				rs.close();
				request.setAttribute("name", name);
				request.setAttribute("pass", "");
				request.setAttribute("error", "ユーザーネームまたはパスワードが間違っています");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
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
	//ここまで
		session.setAttribute("address", address);
		response.sendRedirect("home");

	}
}