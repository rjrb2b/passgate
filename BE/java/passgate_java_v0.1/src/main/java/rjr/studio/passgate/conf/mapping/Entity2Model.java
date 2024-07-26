package rjr.studio.passgate.conf.mapping;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dozermapper.core.Mapper;

@Component
public class Entity2Model {
	
	private Mapper mapper;
	
	@Autowired
	public Entity2Model(Mapper mapper) {
		this.mapper = mapper;
	}
	
	public <D> D mapper(Object source, Class<D> destination) {
		
		return mapper.map(source, destination);
	}
	
	public <S, D> List<D> mapperList(Collection<S> sourceList, Class<D> destinationClass) {
        return sourceList.stream()
                .map(source -> this.mapper(source, destinationClass))
                .collect(Collectors.toList());
    }

}
