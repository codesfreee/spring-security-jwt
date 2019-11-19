package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.jwt.domain.Member;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdentifier(String identifier);
}
