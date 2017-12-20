package oleg.podolyan.ammodpsu.domain.libs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * CREATE TABLE IF NOT EXISTS ranks (
 *  _value VARCHAR(50) NOT NULL,
 *  ordinal INT DEFAULT 0,
 *  CONSTRAINT ranks_value_pk PRIMARY KEY (_value)
 * );
 */
@Entity
@Table(name = "ranks", uniqueConstraints = {@UniqueConstraint(columnNames = {"_value"})})
@Data
@NoArgsConstructor
public class Rank {

    @Id
    @NotNull
    @Min(2)
    @Column(name = "_value", length = 50)
    @JsonProperty
    private String value;

    @Column(name = "ordinal")
    @JsonProperty
    private int ordinal;
}
