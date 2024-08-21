package rjr.studio.passgate.conf.mapping.entity2model.type;

import org.springframework.stereotype.Component;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;

import rjr.studio.passgate.api.view.type.TypeApp;
import rjr.studio.passgate.conf.mapping.DozerMapping;
import rjr.studio.passgate.dao.entity.type.TypeAppEntity;

@Component
public class TypeAppEntity2TypeApp implements DozerMapping {
	
	@Override
	public BeanMappingBuilder get() {
		return new BeanMappingBuilder() {

			@Override
			protected void configure() {
				mapping(TypeAppEntity.class, TypeApp.class)
				;
			}
		};
	}

}
