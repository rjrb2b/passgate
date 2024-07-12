package rjr.studio.passgate.dao.entity.type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class TypeBaseEntity {

	@Id
	@Column(name = "CODE", nullable = false, length = 7)
	private String code;
	@Column(name = "NAME", nullable = false, length = 25)
	private String name;
	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;

}
