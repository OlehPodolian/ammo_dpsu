package oleg.podolyan.ammodpsu.domain.report;

import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.warehouse.Warehouse;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name = "accounts_warehouses",
		uniqueConstraints = @UniqueConstraint(
				name = "accounts_warehouses_account_warehouse_uq",
				columnNames = {}
		)
)
@Data
@NoArgsConstructor
public class AccountWarehouse {

	@Id
	@GeneratedValue
	@Column(name = "accounts_warehouses_id", insertable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Warehouse warehouse;

	public AccountWarehouse(Account account, Warehouse warehouse) {
		this.account = account;
		this.warehouse = warehouse;
	}
}
