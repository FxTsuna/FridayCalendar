package fr.umlv.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EventRepo extends JpaRepository<Event, UUID> {
}
