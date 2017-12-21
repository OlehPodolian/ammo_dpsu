package oleg.podolyan.ammodpsu.domain.libs;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "clothes_types", uniqueConstraints = {@UniqueConstraint(columnNames = {"_value"})})
@Data
@NoArgsConstructor
public class ClothesType implements Serializable {

    private static final long serialVersionUID = 6284242841283452L;

    @Id
    @NotNull
    @Column(name = "_value", length = 50, unique = true)
    private String value;

    public ClothesType(String value) {
        this.value = value;
    }

    @ElementCollection
    @CollectionTable(
            name = "clothes_types_sizes",
            joinColumns = @JoinColumn(name = "_value", referencedColumnName = "_value")
    )
    @Column(name="size")
    private Set<String> sizes = new TreeSet<>();
}
