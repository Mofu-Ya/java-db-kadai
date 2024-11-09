package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// DBに接続
		Connection con = null;		
		Statement statement = null;
						
		try {
			// 	DB接続
			con = DriverManager.getConnection(
		
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "mofu/My89Sql08"
            );
			System.out.println("データベース接続成功：" + con);
			System.out.println("レコード追加を実行します");
			
			// INSERT用SQLクエリを準備
			String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES " +
			  "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13)," +
			  "(1002, '2023-02-08', 'お疲れ様です！', 12)," +
			  "(1003, '2023-02-09', '今日も頑張ります！', 18)," +
			  "(1001, '2023-02-09', '無理は禁物ですよ！', 17)," +
			  "(1002, '2023-02-10', '明日から連休ですね！', 20);" ;
			  			  			
			statement = con.createStatement();
			
			// INSERTを実行
			int rowCnt = statement.executeUpdate(sql);
			
			// SQLクエリを実行(DBMSに送信)			
			System.out.println(rowCnt + "件のレコードが追加されました");
			
			// SELECT用SQLクエリを準備			
			sql = "SELECT * FROM posts WHERE user_id = 1002;";
		
			// SQLクエリを実行
			ResultSet result = statement.executeQuery(sql);
			
			int user_id = 1002;
			int i = 1;
			
			System.out.println("ユーザーIDが" + user_id + "のレコードを検索しました");
			
			while(result.next()) {
				
				System.out.println(i + "件目:投稿日時=" + result.getDate("posted_at") +
						"／投稿内容=" + result.getString("post_content") + "／いいね数=" + result.getInt("likes"));
				i++;				
			}
		} catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if(statement != null) {
				try { statement.close();} catch(SQLException ignore) {}
			}
			
			if(con != null) {
				try { con.close();} catch(SQLException ignore) {}
			}
		}

	}

}
