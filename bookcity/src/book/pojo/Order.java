package book.pojo;

import java.math.BigDecimal;
import java.util.Date;

/** 订单类
 * @author hyc
 * @date 2021/5/8
 **/
public class Order {
    //订单号
    private String orderId;
    //订单创建时间
    private Date createTime;
    //订单金额
    private BigDecimal price;
    //订单状态:0表示未发货,1表示已发货,2表示已签收
    private Integer status = 0;//订单状态默认就是0
    //用户id,要知道订单属于哪一个用户
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
