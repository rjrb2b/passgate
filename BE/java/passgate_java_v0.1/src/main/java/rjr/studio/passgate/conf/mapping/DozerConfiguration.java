package rjr.studio.passgate.conf.mapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import com.github.dozermapper.springboot.autoconfigure.DozerProperties;

@Configuration
@EnableConfigurationProperties(DozerProperties.class)
public class DozerConfiguration {

	private DozerProperties properties;
	private List<DozerMapping> builders;

	@Autowired
	public DozerConfiguration(DozerProperties properties, List<DozerMapping> builders) {
		this.properties = properties;
		this.builders = builders;
	}

	@Bean("mapper")
	public DozerBeanMapperFactoryBean dozerMapper() throws Exception {
		DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
		try {
			factoryBean.setMappingFiles(properties.getMappingFiles());
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		// @formatter:off
		List<BeanMappingBuilder> mappings = builders.stream()
				.map(DozerMapping::get)
				.collect(Collectors.toList());
		// @formatter:on

		factoryBean.setMappingBuilders(mappings);
		return factoryBean;
	}

}
