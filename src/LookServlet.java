

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
 * Servlet implementation class LookServlet
 */
@WebServlet("/look")
public class LookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String text(String text) {
		String stext = "";
		int count = 0;
		for( int i = 0; i < text.length(); i++) {
			if(text.substring(i, i + 1).equals("\n")) {
				stext += text.substring(count, i) + "<br>";
				count = i + 1;
			}else if(i + 1 == text.length()){
				stext += text.substring(count, i + 1);
			}
		}
		return stext;
	}


    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String submission_id = request.getParameter("look_submission");
		String page = request.getParameter("page");
		String text = "";
		String address = "";
		String name = "";
		String img = "";
		int typeindex = 0;
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

			String sql = "SELECT address, text, typeindex, image FROM submission WHERE submission_id = ?";
			PreparedStatement smt = con.prepareStatement(sql);
			smt.setInt(1, Integer.parseInt(submission_id));
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				text = text(rs.getString("text"));
				address = rs.getString("address");
				typeindex = rs.getInt("typeindex");
				img = rs.getString("image");
			}
			rs.close();
			smt.close();

			sql = "SELECT user_name FROM accounts WHERE address = ?";
			smt = con.prepareStatement(sql);
			smt.setString(1, address);
			rs = smt.executeQuery();
			while(rs.next()) {
				name = rs.getString("user_name");
			}
			rs.close();
			smt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//コネクションのクローズ
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
			}
		}
		if(String.valueOf(session.getAttribute("address")).equals(address)) {
			request.setAttribute("delete", "delete");
		}
		request.setAttribute("text", text);
		request.setAttribute("name", name);
		request.setAttribute("img", img);
		request.setAttribute("submission_id", submission_id);
		session.setAttribute("type", typeindex);
		request.getRequestDispatcher("lookSubmission.jsp").forward(request, response);
	}

}
