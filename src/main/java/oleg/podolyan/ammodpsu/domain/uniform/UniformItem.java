package oleg.podolyan.ammodpsu.domain.uniform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.AbstractAuditor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@MappedSuperclass
@Data
@NoArgsConstructor
public class UniformItem extends AbstractAuditor {

    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence")
    @Column(name = "item_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private RationItem rationItem;

    @NotNull
    @JsonProperty
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "price")
    @JsonProperty
    private double price;

    @NotNull
    @JsonProperty
    private String size;

    @NotNull
    @JsonProperty
    private String category;

    @Column(name = "received_at", columnDefinition = "DATE", nullable = false)
    @JsonProperty(value = "receivedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate received;

    @Column(name = "deleted")
    @JsonProperty
    private boolean deleted;
}
