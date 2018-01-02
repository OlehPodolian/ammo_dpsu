package oleg.podolyan.ammodpsu.domain.lex;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rations")
public class Ration {

	@Id
	@GeneratedValue
	@Column(name = "ration_id", insertable = true, updatable = true)
	private Long id;
}
