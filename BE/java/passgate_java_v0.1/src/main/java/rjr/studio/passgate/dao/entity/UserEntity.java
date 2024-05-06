package rjr.studio.passgate.dao.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ACCOUNTS")
public class UserEntity extends BaseEntity {

	@Column(name = "NAME", nullable = false, length = 255)
	@Size(min = 2, message = "The name length must be at least 2 characters")
	private String name;
	@Column(name = "SURNAME", nullable = false, length = 255)
	@Size(min = 2, message = "The surname length must be at least 2 characters")
	private String surname;
	@Column(name = "USERNAME", nullable = false, length = 255)
	@Size(min = 2, message = "The username length must be at least 2 characters")
	private String username;
	@Column(name = "PASSWORD", nullable = false, length = 255)
	@Size(min = 8, message = "The password length must be at least 8 characters")
	private String password;
	@ManyToMany
    @JoinTable(
        name = "ACCOUNTS_ROLES",
        joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_CODE")
    )
    private Set<TypeRoleEntity> roles;

}
