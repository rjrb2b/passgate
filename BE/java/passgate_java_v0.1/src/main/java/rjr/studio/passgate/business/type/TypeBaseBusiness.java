package rjr.studio.passgate.business.type;

import java.io.Serializable;
import java.util.List;

import rjr.studio.passgate.api.view.model.type.TypeBaseModel;
import rjr.studio.passgate.dao.entity.type.TypeBaseEntity;

public interface TypeBaseBusiness<E extends TypeBaseEntity, M extends TypeBaseModel, C extends Serializable> {
	
	List<M> findAll();
	
	M findByCode(C code);
	
	M findByName(String name);
	
	C save(M model);
	
	C updateByCode(C code, M model) throws Exception;
	
	C deleteByCode(C code);

}
