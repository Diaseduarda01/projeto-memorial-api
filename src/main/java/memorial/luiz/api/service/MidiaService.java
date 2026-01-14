package memorial.luiz.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.luiz.api.entity.Memoria;
import memorial.luiz.api.entity.Midia;
import memorial.luiz.api.entity.Solicitacao;
import memorial.luiz.api.entity.TipoMidia;
import memorial.luiz.api.repository.MidiaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MidiaService {

    private final MidiaRepository repository;
    private final StorageService storageService;

    public Midia salvarArquivo(MultipartFile file, TipoMidia tipo,
                               Solicitacao solicitacao, Memoria memoria) {

        String nomeArquivo = storageService.salvar(file);

        Midia midia = new Midia();
        midia.setNome(file.getOriginalFilename());
        midia.setCaminho("https://projeto-memorial-api.onrender.com/upload/" + nomeArquivo);
        midia.setTipo(tipo);
        midia.setSolicitacao(solicitacao);
        midia.setMemoria(memoria);

        return repository.save(midia);
    }

    public List<Midia> buscarPorSolicitacao(Integer solicitacaoId) {
        return repository.findBySolicitacaoId(solicitacaoId);
    }

    public List<Midia> buscarPorMemoria(Integer memoriaId) {
        return repository.findByMemoriaId(memoriaId);
    }

    public void atualizarFk (Integer id, String nomeFk, Memoria memoria) {
        if (nomeFk.equals("solicitacao")){
            List<Midia> midias = this.buscarPorSolicitacao(id);
            for (int i = 0; i < midias.size(); i++) {
                midias.get(i).setMemoria(memoria);
                repository.save(midias.get(i));
            }
        }
    }
}
