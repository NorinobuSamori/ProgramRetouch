package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyDataBeans  implements Serializable {
	private int id;
	private int userId;
	private int totalPrice;
	private int delivertMethodId;
	private Date buyDate;
	private String strDate;
	private int id_1;
	private String deliveryMethodName;
	private int deliveryMethodPrice;


	public BuyDataBeans(int id, int userId, int totalPrice, int delivertMethodId, Date buyDate,
			int id_1, String deliveryMethodName, int deliveryMethodPrice) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.delivertMethodId = delivertMethodId;


		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");

		this.strDate = sdf1.format(buyDate);
		this.buyDate = buyDate;


		this.id_1 = id_1;
		this.deliveryMethodName = deliveryMethodName;
		this.deliveryMethodPrice = deliveryMethodPrice;
	}
	public BuyDataBeans() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getDelivertMethodId() {
		return delivertMethodId;
	}
	public void setDelivertMethodId(int delivertMethodId) {
		this.delivertMethodId = delivertMethodId;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public String getDeliveryMethodName() {
		return deliveryMethodName;
	}
	public void setDeliveryMethodName(String deliveryMethodName) {
		this.deliveryMethodName = deliveryMethodName;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buyDate);
	}
	public int getDeliveryMethodPrice() {
		return deliveryMethodPrice;
	}
	public void setDeliveryMethodPrice(int deliveryMethodPrice) {
		this.deliveryMethodPrice = deliveryMethodPrice;
	}
	public String getStrDate() {

		return strDate ;
	}


}
