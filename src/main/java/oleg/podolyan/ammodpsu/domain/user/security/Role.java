package oleg.podolyan.ammodpsu.domain.user.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role implements Serializable{

	@Transient
	private static final long serialVersionUID = 1514741779451L;

	@Id
	@GeneratedValue
	@Column(name = "role_id", insertable = false, updatable = false)
	private Long id;

	@NaturalId
	private String name;

	public Role(String name) {
		this.name = name;
	}
}
