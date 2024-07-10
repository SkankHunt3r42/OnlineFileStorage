package onlineFileStorage.root.app.FileStorage.repo;

import onlineFileStorage.root.app.FileStorage.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    Optional<RoleEntity> findByRole(String role);
}
