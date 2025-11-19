package memorial.luiz.api.exception.naoencontrado;

import memorial.luiz.api.exception.naoencontrado.base.NaoEncontradoException;

public class MemoriaNaoEncontradoException extends NaoEncontradoException {
    private static final String MENSAGEM = "Memória não encontrado";
    public MemoriaNaoEncontradoException() {
        super(MENSAGEM);
    }
}
