package memorial.luiz.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.luiz.api.entity.Memoria;
import memorial.luiz.api.entity.Solicitacao;
import memorial.luiz.api.entity.Status;
import memorial.luiz.api.entity.Usuario;
import memorial.luiz.api.exception.naoencontrado.MemoriaNaoEncontradoException;
import memorial.luiz.api.repository.SolicitacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final MemoriaService memoriaService;
    private final UsuarioService usuarioService;
    private final StatusService statusService;

    public Solicitacao cadastrar(Solicitacao solicitacao) {
        Usuario usuario = usuarioService.buscarPorId(solicitacao.getUsuario().getId());
        Status status = statusService.buscarPorNome("PENDENTE");

        solicitacao.setUsuario(usuario);
        solicitacao.setStatus(status);

        Solicitacao salva = repository.save(solicitacao);
        log.info("Solicitação {} cadastrada com sucesso!", salva.getId());
        return salva;
    }

    public List<Solicitacao> listarTodas() {
        List<Solicitacao> solicitacoes = repository.findAll();
        log.info("Total de solicitações encontradas: {}", solicitacoes.size());
        return solicitacoes;
    }

    public Solicitacao buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(MemoriaNaoEncontradoException::new);
    }

    public Solicitacao atualizar(Integer id, Solicitacao destino) {
        Solicitacao origem = buscarPorId(id);

        this.atualizarDadosBasicos(origem, destino);
        this.atualizarUsuario(origem, destino);
        this.atualizarStatus(origem, destino);

        Solicitacao salvo = repository.save(origem);
        log.info("Solicitação {} atualizada com sucesso!", salvo.getId());
        return salvo;
    }

    private void atualizarDadosBasicos(Solicitacao origem, Solicitacao destino) {
        origem.setTitulo(destino.getTitulo());
        origem.setDescricao(destino.getDescricao());
    }

    private void atualizarUsuario(Solicitacao origem, Solicitacao destino) {
        if (destino.getUsuario() != null) {
            Usuario usuario = usuarioService.buscarPorId(destino.getUsuario().getId());
            origem.setUsuario(usuario);
        }
    }

    private void atualizarStatus(Solicitacao origem, Solicitacao destino) {
        if (destino.getStatus() != null) {
            Status status = statusService.buscarPorNome(destino.getStatus().getNome());
            origem.setStatus(status);
        }
    }

    public void deletar(Integer id) {
        Solicitacao solicitacao = buscarPorId(id);
        repository.delete(solicitacao);
        log.info("Solicitação {} deletada com sucesso!", solicitacao.getId());
    }

    public void aceitarSolicitacao(Integer id) {
        Solicitacao solicitacao = this.buscarPorId(id);

        if (solicitacao != null) {
            Status recusado = statusService.buscarPorNome("ACEITO");
            solicitacao.setStatus(recusado);
            repository.save(solicitacao);
            this.criarMemoria(solicitacao);
        }
    }

    public void recusarSolicitacao(Integer id) {
        Solicitacao solicitacao = this.buscarPorId(id);
        if (solicitacao != null) {
            Status recusado = statusService.buscarPorNome("RECUSADO");
            solicitacao.setStatus(recusado);
            repository.save(solicitacao);
        }
    }

    private void criarMemoria(Solicitacao solicitacao) {
        Memoria memoria = new Memoria(
                solicitacao.getTitulo(),
                solicitacao.getDescricao()
        );

        memoria.setUsuario(solicitacao.getUsuario());
        memoriaService.cadastrar(memoria);
    }
}