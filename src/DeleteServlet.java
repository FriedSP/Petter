

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		String submission_id = request.getParameter("submission_id");
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

			String sql = "DELETE FROM submission WHERE submission_id = ?";
			PreparedStatement smt = con.prepareStatement(sql);

			smt.setString(1, submission_id);
			smt.executeUpdate();
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
		response.sendRedirect("home");
	}

}
