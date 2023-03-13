

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class HomeServlet extends HttpServlet {

	public HashMap<String, ArrayList<HashMap<String, String>>> getImg() {
		ArrayList<HashMap<String, String>> dogImgList = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> catImgList = new ArrayList<HashMap<String, String>>();
		HashMap<String, ArrayList<HashMap<String, String>>> imgList = new HashMap<String, ArrayList<HashMap<String, String>>>();
		//ホーム画面に載せる画像を探索

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
		//猫の画像を探索して格納
			String sql = "SELECT image, submission_id FROM submission WHERE typeindex = 1 ORDER BY submission_id DESC";
			PreparedStatement smt = con.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				HashMap<String, String> catImgMap = new HashMap<String, String>();
				catImgMap.put("submission_id", rs.getString("submission_id"));
				catImgMap.put("image", rs.getString("image"));
				catImgList.add(catImgMap);
			}
			rs.close();
			smt.close();
		//ここまで
			//犬の画像を探索して格納
			sql = "SELECT image,submission_id FROM submission WHERE typeindex = 2 ORDER BY submission_id DESC";
			smt = con.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()) {
				HashMap<String, String> dogImgMap = new HashMap<String, String>();
				dogImgMap.put("submission_id", rs.getString("submission_id"));
				dogImgMap.put("image", rs.getString("image"));
				dogImgList.add(dogImgMap);
			}
			rs.close();
			smt.close();
		//ここまで
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//コネクションのクローズ
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
			}
		}

		imgList.put("cat", catImgList);
		imgList.put("dog", dogImgList);

		return imgList;
	}

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HashMap<String, ArrayList<HashMap<String, String>>> imgList = (HashMap<String, ArrayList<HashMap<String, String>>>)this.getImg();

		request.setAttribute("catImg", imgList.get("cat"));
		request.setAttribute("dogImg", imgList.get("dog"));

		request.getRequestDispatcher("home.jsp").forward(request, response);
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HashMap<String, ArrayList<HashMap<String, String>>> imgList = (HashMap<String, ArrayList<HashMap<String, String>>>)this.getImg();

		request.setAttribute("catImg", imgList.get("cat"));
		request.setAttribute("dogImg", imgList.get("dog"));

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
