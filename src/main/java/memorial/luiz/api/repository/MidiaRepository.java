package memorial.luiz.api.repository;

import memorial.luiz.api.entity.Midia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MidiaRepository extends JpaRepository<Midia, Integer> {
    List<Midia> findBySolicitacaoId(Integer solicitacaoId);

    List<Midia> findByMemoriaId(Integer memoriaId);
}
