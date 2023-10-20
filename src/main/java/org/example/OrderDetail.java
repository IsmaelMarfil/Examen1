package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderDetail {
    public int quantity;
    public String TaxType;
    public Order order;
    public Item item;

    public OrderDetail(int quantity, String taxType, Order order, Item item) {
        this.quantity = quantity;
        TaxType = taxType;
        this.order = order;
        this.item = item;
    }
    public BigDecimal calcSubTotal(){
        BigDecimal total = item.getPrice().multiply(BigDecimal.valueOf(quantity));
        total = total.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP);
        return total;
    }
    public BigDecimal calcWeight(){
        BigDecimal total = item.getWeight().multiply(BigDecimal.valueOf(quantity));
        total = total.divide(BigDecimal.ONE, 3, RoundingMode.HALF_UP);
        return total;
    }
}
