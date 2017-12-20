package oleg.podolyan.ammodpsu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.uniform.Ration;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "soldiers", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@SQLDelete(sql = "UPDATE soldiers SET deleted = true WHERE soldier_id = ?")
@Loader(namedQuery = "findALL")
@NamedQuery(name = "findAll", query = "SELECT s FROM Soldier s WHERE deleted = false")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
public class Soldier extends AbstractAuditor {

    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence")
    @Column(name = "soldier_id", nullable = false)
    private Long id;

    @NaturalId
    @Column(name = "username")
    private String username;

    @NotNull
    @JsonProperty
    private String rank;

    @NotNull
    @JsonProperty
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @NotNull
    @JsonProperty
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @JsonProperty
    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Ration ration;
}
