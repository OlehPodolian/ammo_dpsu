package oleg.podolyan.ammodpsu.domain.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE order_id = ?")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue()
    @Column(name = "order_id", nullable = false)
    private Long id;
    private boolean received;

    @NotNull
    @Column(name = "order_no", nullable = false)
    private String number;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    private String description;
    @Column(name = "order_date")
    private LocalDate date;
    private boolean deleted;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public double getTotalCost(){
        final BigDecimal cost = new BigDecimal(0);
        orderItems.forEach(orderItem -> cost.add(new BigDecimal(orderItem.getCost())));
        return cost.doubleValue();
    }
}
