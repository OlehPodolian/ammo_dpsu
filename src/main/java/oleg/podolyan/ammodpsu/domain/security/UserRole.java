package oleg.podolyan.ammodpsu.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import oleg.podolyan.ammodpsu.domain.security.Role;
import oleg.podolyan.ammodpsu.domain.security.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * CREATE TABLE IF NOT EXISTS user_roles (
 *  user_role_id BIGSERIAL NOT NULL,
 *  user_id BIGINT NOT NULL,
 *  role_id BIGINT NOT NULL,
 *  CONSTRAINT user_role_id_pk PRIMARY KEY (user_role_id),
 *  CONSTRAINT ser_role_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE  CASCADE,
 *  CONSTRAINT ser_role_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE  CASCADE
 * );
 */
@Entity
@Table(name = "user_roles")
@Data
@AllArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id", nullable = false)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
}
