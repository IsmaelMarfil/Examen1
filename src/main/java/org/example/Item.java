package org.example;

import java.math.BigDecimal;
import java.util.List;

public class Item {
    public String description;
    private BigDecimal weight;
    private BigDecimal price;
    public List<OrderDetail> orderDetails;

    public Item(String description, BigDecimal weight, BigDecimal price, List<OrderDetail> orderDetails) {
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.orderDetails = orderDetails;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
