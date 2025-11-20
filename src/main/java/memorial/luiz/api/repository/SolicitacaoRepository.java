package memorial.luiz.api.repository;

import memorial.luiz.api.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {
    List<Solicitacao> findAllByTituloIgnoreCase(String nome);

    List<Solicitacao> findAllByStatusNomeIgnoreCase(String status);
}
