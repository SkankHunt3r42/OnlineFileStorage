package onlineFileStorage.root.app.FileStorage.repo;

import onlineFileStorage.root.app.FileStorage.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<UserEntity,Integer> {
    Boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
