package rjr.studio.passgate.business.type;

import java.io.Serializable;
import java.util.Set;

import rjr.studio.passgate.api.view.type.TypeBaseModel;
import rjr.studio.passgate.dao.entity.type.TypeBaseEntity;

public interface TypeBaseBusiness<E extends TypeBaseEntity, M extends TypeBaseModel, C extends Serializable> {
	
	Set<M> findAll();
	
	M findByCode(C code);
	
	M findByName(String name);
	
	C save(M model);
	
	C updateByCode(C code, M model) throws Exception;
	
	C deleteByCode(C code);

}
