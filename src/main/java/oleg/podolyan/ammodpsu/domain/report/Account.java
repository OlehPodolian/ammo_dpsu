package oleg.podolyan.ammodpsu.domain.report;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
		name = "accounts",
		uniqueConstraints = @UniqueConstraint(
				name = "accounts_name_uq",
				columnNames = "name"
		)
)
@SQLDelete(sql = "UPDATE accounts SET deleted = true WHERE account_id = ?")
@Data
@EqualsAndHashCode(exclude = "accountItems")
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "account_id", insertable = false, updatable = false)
	private Long id;

	@NaturalId
	private String name;

	private String description, details;

	private boolean deleted;

	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private Set<AccountItem> accountItems = new HashSet<>();

	public Account(String name) {
		this.name = name;
	}

	public void addAccountItem(AccountItem accountItem){
		accountItem.setAccount(this);
		accountItems.add(accountItem);
	}
}
