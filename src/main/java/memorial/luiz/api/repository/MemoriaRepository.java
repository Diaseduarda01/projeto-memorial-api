package memorial.luiz.api.repository;

import memorial.luiz.api.entity.Memoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoriaRepository extends JpaRepository<Memoria, Integer> {
}
