package characterProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {
	Connection connection = null;

	// connect()
	public void connect() {
//		String url = "jdbc:mysql://localhost:3306/characterDB?serverTimezone=UTC";
		String url = "jdbc:mysql://localhost:3306/characterDB?serverTimezone=UTC";
		String user = "root";
		String password = "123456";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.err.println("데이터베이스 로드오류" + e.getStackTrace());
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결오류" + e.getStackTrace());
		}
	}// end connect()
		// insert()

	public int insert(Character c) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		System.out.println(c);
		String query = "INSERT INTO characterTBL VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setString(2, c.getLoginId());
			ps.setString(3, c.getLoginPw());
			ps.setString(4, c.getTribe());
			ps.setString(5, c.getMyclass());
			ps.setString(6, c.getGender());
			ps.setInt(7, c.getLevel());
			ps.setInt(8, c.getViability());
			ps.setInt(9, c.getPower());
			ps.setInt(10, c.getIntelligence());
			ps.setInt(11, c.getTotal());
			ps.setDouble(12, c.getAvg());
			ps.setString(13, c.getGrede());
			returnValue = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("INSERT ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("PS CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return returnValue;
	}// end of insert()
		// select()

	public ArrayList<Character> select() {
		ArrayList<Character> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM characterTBL";

		try {
			ps = connection.prepareStatement(query);
			// select 성공하면 ResultSet, 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tribe = rs.getString("tribe");
				String myclass = rs.getString("myclass");
				String gender = rs.getString("gender");
				int level = rs.getInt("level");
				int viability = rs.getInt("viability");
				int power = rs.getInt("power");
				int intelligence = rs.getInt("intelligence");
				int total = rs.getInt("total");
				Double avg = rs.getDouble("avg");
				String grade = rs.getString("grade");
				list.add(new Character(id, name, null, null, null, null, tribe, myclass, gender, level, viability,
						power, intelligence, total, avg, grade));
			}
		} catch (SQLException e) {
			System.err.println("SELECT ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("PS CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return list;
	}

	// analizeSelect()
	public ArrayList<Character> analizeSelect() {
		ArrayList<Character> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM characterTBL";

		try {
			ps = connection.prepareStatement(query);
			// select 성공하면 ResultSet, 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int total = rs.getInt("total");
				int avg = rs.getInt("avg");
				String grade = rs.getString("grade");

				list.add(new Character(id, name, total, avg, grade));
			}

		} catch (SQLException e) {
			System.err.println("SELECT ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("PS CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return list;
	}

	// nameSearch()
	public ArrayList<Character> nameSearch(String dataName) {
		ArrayList<Character> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM characterTBL where name like ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%" + dataName + "%");
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tribe = rs.getString("tribe");
				String myclass = rs.getString("myclass");
				String gender = rs.getString("gender");
				int level = rs.getInt("level");
				int viability = rs.getInt("viability");
				int power = rs.getInt("power");
				int intelligence = rs.getInt("intelligence");
				int total = rs.getInt("total");
				Double avg = rs.getDouble("avg");
				String grade = rs.getString("grade");
				list.add(new Character(id, name, null, null, null, null, tribe, myclass, gender, level, viability,
						power, intelligence, total, avg, grade));
			}

		} catch (SQLException e) {
			System.err.println("SELECT ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("PS CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return list;
	}

	// selectId()
	public Character selectId(int dataId) {
		Character character = null;
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM characterTBL where loginId=?";

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, dataId);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tribe = rs.getString("tribe");
				String myclass = rs.getString("myclass");
				String gender = rs.getString("gender");
				int level = rs.getInt("level");
				int viability = rs.getInt("viability");
				int power = rs.getInt("power");
				int intelligence = rs.getInt("intelligence");
				int total = rs.getInt("total");
				Double avg = rs.getDouble("avg");
				String grade = rs.getString("grade");
				character = new Character(id, name, null, null, null, null, tribe, myclass, gender, level, viability,
						power, intelligence, total, avg, grade);
			}

		} catch (SQLException e) {
			System.err.println("selectId ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("PS CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return character;
	}

	// update()
	public int update(Character c) {

		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		System.out.println(c);
		String query = "UPDATE  characterTBL SET  viability=?,power=?,intelligence=? where id=?";

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, c.getViability());
			ps.setInt(2, c.getPower());
			ps.setInt(3, c.getIntelligence());
			ps.setInt(4, c.getId());
			returnValue = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("UPDATE ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("PS CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return returnValue;
	}

	// selectSort()
	public ArrayList<Character> selectSort() {
		ArrayList<Character> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM characterTBL order by total desc";

		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tribe = rs.getString("tribe");
				String myclass = rs.getString("myclass");
				String gender = rs.getString("gender");
				int level = rs.getInt("level");
				int viability = rs.getInt("viability");
				int power = rs.getInt("power");
				int intelligence = rs.getInt("intelligence");
				int total = rs.getInt("total");
				Double avg = rs.getDouble("avg");
				String grade = rs.getString("grade");
				list.add(new Character(id, name, "aa123", "a123456", "aa123", "a123456", tribe, myclass, gender, level,
						viability, power, intelligence, total, avg, grade));
			}

		} catch (SQLException e) {
			System.err.println("selectId ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("PS CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return list;
	}

	// delete()
	public int delete(int delete) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "delete from characterTBL where id=?";

		try {
			ps = connection.prepareStatement(query);

			ps.setInt(1, delete);
			returnValue = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("DELETE ERROR" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("ps close ERROR" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("CONNECTION CLOSE ERROR" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		return returnValue;
	}

	public int signIn(String signInId, String signInPw) {
//		ArrayList<SignUp> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = -1;
		String query = "select * from signupTBL;";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				if (id.equals(signInId) && pw.equals(signInPw)) {
					returnValue = 1;
				}
			}
		} catch (Exception e) {
			System.out.println("signin error : " + e.getMessage());
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			System.err.println("ps close error" + e.getMessage());
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println("connection close error" + e.getMessage());
		}
		return returnValue;
	}

}
