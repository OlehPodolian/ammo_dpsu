package oleg.podolyan.ammodpsu.domain.military;

import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.lex.Ration;
import oleg.podolyan.ammodpsu.domain.user.PersonalInfo;
import oleg.podolyan.ammodpsu.domain.user.User;
import oleg.podolyan.ammodpsu.domain.warehouse.SizeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Entity
@Table(name = "soldiers")
@AttributeOverrides({
		@AttributeOverride(name = "user_id", column = @Column(name = "soldier_id")),
		@AttributeOverride(name = "username", column = @Column(name = "username"))
})
@Data
@NoArgsConstructor
public class Soldier extends User{

	private String rank;

	@Embedded
	private PersonalInfo personalInfo;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Ration ration;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Department department;

	@ElementCollection(targetClass = String.class)
	@CollectionTable(name = "soldiers_sizes")
	@MapKeyEnumerated(value = EnumType.STRING)
	@Column(name = "size", nullable = false)
	private Map<SizeType, String> sizeMap = new HashMap<>(4);

	// TODO
//	private Set<UniformItemIssued> issuedItems = new HashSet<>();

	// TODO
//	private Set<InventoryItem> inventoryItems = new HashSet<>();
}
