package characterProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {
		// 1.�ܺο��� ������ ���̽��� �����Ҽ� �ֵ��� ����. studentDB�� ����.
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/signupDB?serverTimezone=UTC";
		String user = "root";
		String password = "123456";
		String driverName = "com.mysql.cj.jdbc.Driver";

		// 2.���������� JDBC�� �˾Ƽ� �� ���ֱ� ������ �츮�� JDBC�� DriverManager �� ���ؼ� DB���� ������ ������ �ȴ�.
		try {
			// 2-1 JDBC Ŭ���� �ε�. DB���� ������ ������ȴ�.
			Class.forName(driverName);
			// 2-2 mysql DB�� ����.
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// `com.mysql.cj.jdbc.Driver` ���Ŭ���������̺귯�����߰������ʾҴٸ�����߻�
			System.err.println("�����ͺ��̽� �ε����" + e.getStackTrace());
		} catch (SQLException e) {
			// DB ���� ������ Ʋ�ȴٸ� ���� �߻�
			System.err.println("�����ͺ��̽� �������" + e.getStackTrace());
		} //
			// 3. �����͸� �����Ѵ�. insert into ���̺� �� values();
			// ��ũ��ġ ��
		PreparedStatement ps = null;
		int returnValue = 1;
		Character c = new Character("aa123", "a123456");
		String query = "insert into signupTBL values(?,?);";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, c.getLoginId());
			ps.setString(2, c.getLoginPw());

			// ���� �����ϸ� 1�� �����Ѵ�.
			returnValue = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("insert ���� �߻�" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				System.out.println("ps close ����" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("connection close ����" + e.getMessage());
				e.printStackTrace();
			}
		} // end of finally
		if (returnValue == 1) {
			System.out.println("���Լ���");
		} else {
			System.out.println("���Խ���");
		}
	}// end of main
}
