package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import base.DBManager;
import beans.BuyDataBeans;

public class BuyDAO {
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static BuyDAO getInstance() {
		return new BuyDAO();
	}

	/**
	 * 購入情報登録処理
	 * @param bdb 購入情報
	 * @throws SQLException 呼び出し元にスローさせるため
	 */
	public static int insertBuy(BuyDataBeans bdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int autoIncKey = -1;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy(user_id,total_price,delivery_method_id,create_date) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getTotalPrice());
			st.setInt(3, bdb.getDelivertMethodId());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();////これでStatement.RETURN_GENERATED_KEYSを取得している
			if (rs.next()) {////nullを想定しているものではないので要注意※
				autoIncKey = rs.getInt(1);////なぜgetInt(1);なのかは謎。これは後回しにする。
			}
			System.out.println("inserting buy-datas has been completed");

			return autoIncKey;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return BuyDataBeans
	 * 				購入情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static BuyDataBeans getBuyDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM t_buy"
							+ " JOIN m_delivery_method"
							+ " ON t_buy.delivery_method_id = m_delivery_method.id"
							+ " WHERE t_buy.id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			BuyDataBeans bdb = new BuyDataBeans();
			if (rs.next()) {
				bdb.setId(rs.getInt("id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
				bdb.setDeliveryMethodName(rs.getString("name"));
			}

			System.out.println("searching BuyDataBeans by buyID has been completed");

			return bdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static ArrayList<BuyDataBeans> getBuyDataBeansWhereUser_Id(int user_id) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			ArrayList<BuyDataBeans> bdbList = new ArrayList<BuyDataBeans>();

			st = con.prepareStatement(
					"SELECT * FROM t_buy inner join m_delivery_method on delivery_method_id = m_delivery_method.id WHERE user_id = ?");
			st.setInt(1, user_id);

			ResultSet rs = st.executeQuery();




			while (rs.next()) {
				int id1 = rs.getInt("id");
				int user_id1 = rs.getInt("user_id");
                int total_price = rs.getInt("total_price");
                int delivery_method_id = rs.getInt("delivery_method_id");
                Date create_date = rs.getTimestamp("create_date");
                int id_1 = rs.getInt("m_delivery_method.id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                BuyDataBeans bdb11 = new BuyDataBeans(id1, user_id1,  total_price, delivery_method_id , create_date,id_1, name, price);
                bdbList.add(bdb11);

                System.out.println(create_date);
			}




			System.out.println("searching BuyDataBeans by USER_ID has been completed");

			return bdbList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
