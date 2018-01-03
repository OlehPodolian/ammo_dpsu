package oleg.podolyan.ammodpsu.domain.warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import oleg.podolyan.ammodpsu.domain.report.Account;
import oleg.podolyan.ammodpsu.domain.report.AccountWarehouse;
import oleg.podolyan.ammodpsu.domain.user.User;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.CascadeType;
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
@Table(name = "warehouses")
@SQLDelete(sql = "UPDATE warehouses SET deleted = true WHERE warehouse_id = ?")
@Data
@NoArgsConstructor
public class Warehouse {

	@Id
	@GeneratedValue
	@Column(name = "warehouse_id", insertable = false, updatable = false)
	private Long id;

	@NaturalId
	private String name;

	private String description, details;

	private boolean deleted;

	@OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER)
	private Set<User> managers = new HashSet<>();

	@OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Singular
	@JsonIgnore
	private Set<AccountWarehouse> accountWarehouses = new HashSet<>();

	public Warehouse(String name) {
		this.name = name;
	}
}
