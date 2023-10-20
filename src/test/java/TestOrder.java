import org.example.Item;
import org.example.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import  org.example.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import  java.util.List;

import java.time.LocalDate;

public class TestOrder {
    @Test
    public void testOrder(){
        List <OrderDetail> ListaOrdenesDetail1 = new ArrayList<>();

        Order o1  = new Order(LocalDate.now(), ListaOrdenesDetail1 );

        Item i1 = new Item("Western Digital Unidad interna de estado sólido SSD WD SN580 NVMe azul de 1 TB", BigDecimal.valueOf(0.395), BigDecimal.valueOf(52.99), ListaOrdenesDetail1);
        Item i2 = new Item("Apple MacBook Pro M1 Pro", BigDecimal.valueOf(1.300), BigDecimal.valueOf(1758.95), ListaOrdenesDetail1);
        OrderDetail od1 = new OrderDetail(3, "General", o1, i1);
        OrderDetail od2 = new OrderDetail(2, "Reduced", o1, i2);
        o1.orderDetails.add(od1);
        o1.orderDetails.add(od2);
        Assertions.assertEquals(BigDecimal.valueOf(3676.87), o1.calcNetTotal() );
        Assertions.assertEquals(BigDecimal.valueOf(4062.04), o1.calcGrossTotal());
        Assertions.assertEquals(BigDecimal.valueOf(385.1737), o1.calcVAT());
        Assertions.assertEquals(BigDecimal.valueOf(1.185), od1.calcWeight());
        Assertions.assertEquals(BigDecimal.valueOf(2.600), od2.calcWeight());



    }
}
