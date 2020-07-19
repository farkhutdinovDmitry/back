package ru.dfarkhutdinov.back.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dfarkhutdinov.back.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
