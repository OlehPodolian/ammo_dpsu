package oleg.podolyan.ammodpsu.domain.lex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oleg.podolyan.ammodpsu.domain.military.Soldier;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rations")
@SQLDelete(sql = "UPDATE rations SET deleted = true WHERE ration_id = ?")
@Getter
@Setter
@EqualsAndHashCode(exclude = "rationItems")
@NoArgsConstructor
public class Ration {

	@Id
	@GeneratedValue
	@Column(name = "ration_id", insertable = true, updatable = true)
	private Long id;

	@NaturalId
	private String name;

	private String description;

	private String details;

	@OneToMany(mappedBy = "ration", fetch = FetchType.LAZY)
	private Set<Soldier> soldiers = new HashSet<>();

	@OneToMany(mappedBy = "ration", fetch = FetchType.EAGER)
	private Set<RationItem> rationItems = new HashSet<>();

	private boolean deleted;

	public void addRationItem(RationItem rationItem){
		rationItem.setRation(this);
		rationItems.add(rationItem);
	}
}
