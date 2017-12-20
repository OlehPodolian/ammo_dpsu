package oleg.podolyan.ammodpsu.domain.libs;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * CREATE TABLE IF NOT EXISTS clothes_types (
 *  _value VARCHAR(100) NOT NULL,
 *  CONSTRAINT clothes_types_value_pk PRIMARY KEY (_value)
 * );
 *
 */
@Entity
@Table(name = "clothes_types", uniqueConstraints = {@UniqueConstraint(columnNames = {"_value"})})
@Data
@NoArgsConstructor
public class ClothesType implements Serializable {

    private static final long serialVersionUID = 6284242841283452L;

    @Id
    @NotNull
    @Min(2)
    @Column(name = "_value", length = 100, updatable = false, insertable = false, unique = true)
    private String value;

    public ClothesType(String value) {
        this.value = value;
    }

    @ElementCollection
    @CollectionTable(name = "clothes_type_sizes",
            joinColumns = @JoinColumn(name = "_value"))
    private Set<String> sizes = new TreeSet<>();
}
