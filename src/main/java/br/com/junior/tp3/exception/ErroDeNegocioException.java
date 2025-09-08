package br.com.junior.tp3.exception;

public class ErroDeNegocioException extends RuntimeException {
    public ErroDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
