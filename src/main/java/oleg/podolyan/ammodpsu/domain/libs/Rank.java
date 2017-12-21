package oleg.podolyan.ammodpsu.domain.libs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Loader;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ranks", uniqueConstraints = {@UniqueConstraint(columnNames = {"_value"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rank {

    @Id
    @NotNull
    @Column(name = "_value", length = 50)
    @Size(min = 2, max = 50)
    @JsonProperty
    private String value;

    @Column(name = "ordinal")
    @JsonProperty
    private int ordinal;
}
