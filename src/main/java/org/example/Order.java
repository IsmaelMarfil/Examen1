package org.example;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

public class Order {
    public LocalDate date;

    public enum status {PAYMENT_COMPLETE, PENDING}

    ;
    public List<OrderDetail> orderDetails;

    public Order(LocalDate date, List<OrderDetail> orderDetails) {
        this.date = date;
        this.orderDetails = orderDetails;
    }

    public BigDecimal calcNetTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderDetail orderDetail : this.orderDetails
        ) {
            BigDecimal precio = orderDetail.item.getPrice();
            precio = precio.multiply(BigDecimal.valueOf(orderDetail.quantity));
            //Divido el precio para poder redondear a 2 decimales
            precio = precio.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP);
            total = total.add(precio);
        }
        return total;
    }

    public BigDecimal calcGrossTotal() {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal precio = null;
        for (OrderDetail orderDetail : this.orderDetails
        ) {
            String tipo = orderDetail.TaxType;
            if (tipo.equalsIgnoreCase("General")) {
                precio = BigDecimal.valueOf(orderDetail.quantity).multiply((orderDetail.item.getPrice()).multiply(BigDecimal.valueOf(1.21)));
            } else if (tipo.equalsIgnoreCase("Reduced")) {
                precio = BigDecimal.valueOf(orderDetail.quantity).multiply((orderDetail.item.getPrice()).multiply(BigDecimal.valueOf(1.10)));
            } else if (tipo.equalsIgnoreCase("Superreduced")) {
                precio = BigDecimal.valueOf(orderDetail.quantity).multiply((orderDetail.item.getPrice()).multiply(BigDecimal.valueOf(1.04)));
            }
            //Divido para poder redondear
            precio = precio.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP);
            total = total.add(precio);

        }
        return total;
    }

    public BigDecimal calcVAT() {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal impuesto = null;
        BigDecimal impuestoTotal = BigDecimal.ZERO;
        for (OrderDetail orderDetail : this.orderDetails
        ) {
            String tipo = orderDetail.TaxType;
            if (tipo.equalsIgnoreCase("General")) {
                impuesto = BigDecimal.valueOf(orderDetail.quantity).multiply((orderDetail.item.getPrice()).multiply(BigDecimal.valueOf(0.21)));
            } else if (tipo.equalsIgnoreCase("Reduced")) {
                impuesto = BigDecimal.valueOf(orderDetail.quantity).multiply((orderDetail.item.getPrice()).multiply(BigDecimal.valueOf(0.10)));
            } else if (tipo.equalsIgnoreCase("Superreduced")) {
                impuesto = BigDecimal.valueOf(orderDetail.quantity).multiply((orderDetail.item.getPrice()).multiply(BigDecimal.valueOf(0.04)));
            } else {
                System.out.println("Error en el tipo de VAT");
            }
            impuesto.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP);
            impuestoTotal = impuestoTotal.add(impuesto);
        }
        return impuestoTotal;
    }
}
