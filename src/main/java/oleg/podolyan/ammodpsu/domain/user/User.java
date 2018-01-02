package oleg.podolyan.ammodpsu.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.JpaAuditable;
import oleg.podolyan.ammodpsu.domain.user.security.Authority;
import oleg.podolyan.ammodpsu.domain.user.security.UserRole;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.NotAudited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET enable = false WHERE user_id = ?")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class User extends JpaAuditable implements UserDetails, Serializable {

	@Transient
	private static final long serialVersionUID = 1514741810080L;

	@Id
	@GeneratedValue
	@Column(name = "user_id", insertable = false, updatable = false)
	private Long id;

	@NaturalId
	@JsonProperty
	private String username;

	@JsonIgnore
	private String password;
	@Column(name = "phone_number")
	@JsonProperty
	private String phoneNumber;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "father_name", nullable = false)
	private String fatherName;

	private String position;

	@JsonIgnore
	private boolean enabled = true;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@NotAudited
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	@JsonIgnore
//	private Set<InventoryItem> inventoryItems = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		userRoles.forEach(userRole -> authorities.add(new Authority(userRole.getRole().getName())));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
