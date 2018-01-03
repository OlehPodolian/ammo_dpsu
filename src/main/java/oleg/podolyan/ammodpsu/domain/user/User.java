package oleg.podolyan.ammodpsu.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import oleg.podolyan.ammodpsu.domain.JpaAuditable;
import oleg.podolyan.ammodpsu.domain.military.Soldier;
import oleg.podolyan.ammodpsu.domain.user.security.Authority;
import oleg.podolyan.ammodpsu.domain.user.security.Role;
import oleg.podolyan.ammodpsu.domain.user.security.UserRole;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SQLDelete(sql = "UPDATE users SET enabled = false WHERE user_id = ?")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class User extends JpaAuditable implements UserDetails, Serializable {

	@Transient
	private static final long serialVersionUID = 1514741810080L;

	@Id
	@TableGenerator(
			name = "USER_GEN",
			table = "ID_Generator",
			pkColumnName = "user_id",
			valueColumnName = "sequence",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
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

	@JsonIgnore
	private boolean enabled = true;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Singular
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	@OneToOne
	private Soldier soldier;

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

	public void addRole(Role role){
		for(UserRole userRole : userRoles) {
			if(userRole.getRole().getName().equals(role.getName())){
				return;
			}
		}
		userRoles.add(new UserRole(this, role));
	}


}
