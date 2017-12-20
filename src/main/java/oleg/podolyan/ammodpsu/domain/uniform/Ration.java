package oleg.podolyan.ammodpsu.domain.uniform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.Soldier;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rations", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
@NoArgsConstructor
public class Ration {

    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence")
    @Column(name = "ration_id", nullable = false)
    @JsonProperty
    private Long id;

    @NaturalId
    @NotNull
    @Column(name = "name", nullable = false)
    @JsonProperty
    private String name;

    @Column(name = "description", columnDefinition = "text")
    @JsonProperty
    private String description;

    @OneToMany(mappedBy = "ration", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RationItem> rationItems = new HashSet<>();

    @OneToMany(mappedBy = "ration", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Soldier> soldiers = new HashSet<>();
}
