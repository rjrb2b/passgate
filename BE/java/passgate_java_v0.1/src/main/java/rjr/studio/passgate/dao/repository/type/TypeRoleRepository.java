package rjr.studio.passgate.dao.repository.type;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;

@Repository
public interface TypeRoleRepository extends JpaRepository<TypeRoleEntity, String> {

	Optional<TypeRoleEntity> findByName(String name);

}
