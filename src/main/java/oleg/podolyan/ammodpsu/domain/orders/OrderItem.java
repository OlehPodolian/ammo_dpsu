package oleg.podolyan.ammodpsu.domain.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
@SQLDelete(sql = "UPDATE order_items SET deleted = true WHERE order_item_id = ?")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Order order;

    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence")
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @NotNull
    private String title;
    private int quantity = 1;
    private double cost;
    private boolean deleted;
}
