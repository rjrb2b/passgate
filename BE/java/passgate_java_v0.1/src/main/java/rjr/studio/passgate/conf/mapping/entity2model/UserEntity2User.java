package rjr.studio.passgate.conf.mapping.entity2model;

import org.springframework.stereotype.Component;

import com.github.dozermapper.core.loader.DozerBuilder;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;

import rjr.studio.passgate.api.view.User;
import rjr.studio.passgate.conf.mapping.DozerMapping;
import rjr.studio.passgate.conf.mapping.converter.ObscurePasswordConverter;
import rjr.studio.passgate.dao.entity.UserEntity;

@Component
public class UserEntity2User implements DozerMapping {
	
	@Override
	public BeanMappingBuilder get() {
		return new BeanMappingBuilder() {

			@Override
			protected void configure() {
				mapping(UserEntity.class, User.class)
				.fields("password", "password", FieldsMappingOptions.oneWay(), this::applyObscurePassword)
				;
			}
			private void applyObscurePassword(DozerBuilder.FieldMappingBuilder b) {
				b.customConverter(ObscurePasswordConverter.class);
			}
		};
	}

}
