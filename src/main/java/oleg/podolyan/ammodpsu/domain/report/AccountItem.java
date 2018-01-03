package oleg.podolyan.ammodpsu.domain.report;

import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.JpaAuditable;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
		name = "account_items",
		uniqueConstraints = @UniqueConstraint(
				name = "account_items_account_id_name_uq",
				columnNames = {"account_id", "name"}
		)
)
@SQLDelete(sql = "UPDATE account_items SET deleted = true WHERE account_items_id = ?")
@Data
@NoArgsConstructor
public class AccountItem extends JpaAuditable{

	@Id
	@GeneratedValue
	@Column(name = "account_items_id", insertable = false, updatable = false)
	private Long id;

	@Version
	private long version;

	private String name, description, details, rate;

	@Column(name = "measure_unit")
	private String measureUnit;

	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "received_at", columnDefinition = "DATE", nullable = false)
	private LocalDate received;

	@Column(name = "order_item_id")
	private Long orderItemId;

	private boolean deleted;

	public AccountItem(String name, double price, LocalDate received) {
		this.name = name;
		this.price = new BigDecimal(price);
		this.orderItemId = 2L;
		this.received = received;
	}
}
