package book.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hyc
 * @date 2021/6/2
 **/
public class UserOrder{
    private String username;
    private Date createTime;
    private BigDecimal price;
    private Integer status;
    private String orderId;

    public UserOrder(String username, Date createTime, BigDecimal price, Integer status, String orderId) {
        this.username = username;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UserOrder() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserOrder{" +
                "username='" + username + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
