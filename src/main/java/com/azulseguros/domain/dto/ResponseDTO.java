package com.azulseguros.domain.dto;

public class ResponseDTO<T> {
    private int codigo;
    private String mensagem;
    private T resposta;


    public ResponseDTO() {
        super();
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public T getresposta() {
        return resposta;
    }
    public void setresposta(T resposta) {
        this.resposta = resposta;
    }
    public String getmensagem() {
        return mensagem;
    }
    public void setmensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
