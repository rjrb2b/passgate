package rjr.studio.passgate.dao.repository.type;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import rjr.studio.passgate.dao.entity.type.TypeBaseEntity;

@NoRepositoryBean
public interface TypeBaseRepository<E extends TypeBaseEntity, CODE extends Serializable> extends JpaRepository<E, CODE>  {
	
	Optional<E> findByName(String name);

}
