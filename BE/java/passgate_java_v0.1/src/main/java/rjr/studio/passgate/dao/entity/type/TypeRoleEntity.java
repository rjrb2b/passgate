package rjr.studio.passgate.dao.entity.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TYPES_ROLE")
public class TypeRoleEntity {

	@Id
	@Column(name = "CODE", nullable = false, length = 5)
	private String code;
	@Column(name = "NAME", nullable = false, length = 25)
	private String name;
	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;

}
