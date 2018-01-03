package oleg.podolyan.ammodpsu.domain.lex;

import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.warehouse.UniformItem;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ration_items")
@SQLDelete(sql = "UPDATE ration_items SET deleted = true WHERE ration_item_id = ?")
@Data
@NoArgsConstructor
public class RationItem {

	@ManyToOne(fetch = FetchType.EAGER)
	private Ration ration;

	@Id
	@GeneratedValue
	@Column(name = "ration_item_id", nullable = false, insertable = false, updatable = false)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private ClothesType clothesType = ClothesType.UNIFORM;

	@Column(nullable = false)
	private String name;

	private int quantity, term;

	private String details;

	private boolean deleted;

//	@OneToMany(mappedBy = "ration_item")
//	private Set<UniformItem> unifromItems = new HashSet<>();


	public Ration getRation() {
		return ration.isDeleted() ? null : ration;
	}
}
