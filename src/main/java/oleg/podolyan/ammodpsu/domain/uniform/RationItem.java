package oleg.podolyan.ammodpsu.domain.uniform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Parameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ration_items", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@SQLDelete(sql = "UPDATE ration_items SET inactive = true WHERE ration_item_id = ?")
@Data
@NoArgsConstructor
public class RationItem {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Ration ration;

    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence")
    @JsonProperty
    @Column(name = "ration_item_id", nullable = false)
    private Long id;

    @NotNull
    @JsonProperty
    @Column(name = "clothes_type", nullable = false)
    private String clothesType;

    @NaturalId
    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 50)
    @JsonProperty
    private String name;

    @JsonProperty
    private int quantity = 1;

    @JsonProperty
    private int term = 1;

    @Column(name = "details", columnDefinition = "TEXT")
    @JsonProperty
    private String details;

    @Column(name = "inactive")
    @JsonProperty
    private boolean inactive;

//    @OneToMany(mappedBy = "ration_item", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
//    @JsonIgnore
//    private Set<UniformItem> uniformItems = new HashSet<>();
}
