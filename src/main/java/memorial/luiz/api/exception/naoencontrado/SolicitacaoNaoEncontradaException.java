package memorial.luiz.api.exception.naoencontrado;

import memorial.luiz.api.exception.naoencontrado.base.NaoEncontradoException;

public class SolicitacaoNaoEncontradaException extends NaoEncontradoException {
    private static final String MENSAGEM = "Usuário não encontrado";
    public SolicitacaoNaoEncontradaException() {
        super(MENSAGEM);
    }
}
