package rjr.studio.passgate.api.view.model;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseModel {

	private Integer id;

}
