

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class InsertSubmissionServlet
 */
@WebServlet("/insertsubmission")
@MultipartConfig(location="D:/HAL/petter/tmp", maxFileSize=8000000)

public class InsertSubmissionServlet extends HttpServlet {
	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}
//	private String text(String text) {
//		String stext = "";
//		for( int i = 1; i < text.length(); i++) {
//			if(String.valueOf(text.charAt(i)).equals(" ")) {
//				stext += "\r\n";
//			}else {
//				stext += String.valueOf(text.charAt(i));
//			}
//		}
//		return stext;
//	}

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSubmissionServlet() {
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
		request.setCharacterEncoding("utf-8");


		String text = request.getParameter("text");
		String upload = request.getParameter("upload");
		//画像の保存処理
		//PHPを使う場合
//			Runtime runtime = Runtime.getRuntime();
//			try {
//
//				Process process = runtime.exec("php C:/hal/JV16/petter/WebContent/php/saveImg.php");
//				process.waitFor();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		//javaでやる場合
		Part part = request.getPart("file");
		String name = this.getFileName(part);
		//ここまで
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

				String sql = "INSERT INTO submission (address,typeindex,image,text) values(?, ?, ?, ?)";
				PreparedStatement smt = con.prepareStatement(sql);

				smt.setString(1, String.valueOf(session.getAttribute("address")));
				if(upload.equals("cat")) {
					smt.setInt(2, 1);
				} else {
					smt.setInt(2, 2);
				}
				smt.setString(3, name);
				smt.setString(4, text);

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
			session.setAttribute("text", text);
			response.sendRedirect("home");
	}
}
