package bg.jug.repository;


import bg.jug.model.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional

public interface UserRepository extends JpaRepository<MyUser, Integer> {


    Optional<MyUser> findByUsername(String username);
}
