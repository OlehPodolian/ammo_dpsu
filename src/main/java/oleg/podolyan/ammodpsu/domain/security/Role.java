package oleg.podolyan.ammodpsu.domain.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * CREATE TABLE IF NOT EXISTS roles (
 *  role_id BIGSERIAL NOT NULL,
 *  name VARCHAR(50) NOT NULL,
 *  CONSTRAINT roles_id_pk PRIMARY KEY (role_id),
 *  CONSTRAINT roles_name_uq UNIQUE (name)
 * );
 */
@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

}