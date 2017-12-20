package oleg.podolyan.ammodpsu.domain.uniform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    @Column(name = "ration_item_id", nullable = false)
    private Long id;

    @NotNull
    @JsonProperty
    @Column(name = "clothes_type", nullable = false)
    private String clothesType;

    @NaturalId
    @Column(name = "name", nullable = false)
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

    @OneToMany(mappedBy = "ration_item", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JsonIgnore
    private Set<UniformItemStored> storedUniformItems = new HashSet<>();
}
