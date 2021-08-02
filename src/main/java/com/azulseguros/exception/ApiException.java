package com.azulseguros.exception;

import com.azulseguros.domain.dto.ResponseDTO;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

public class ApiException extends Exception {
    private String codigo;
    private String descricao;

    public ApiException() {
        super();
    }

    public ApiException(Throwable exception) {
        super(exception);
    }

    public ApiException(String mensagem) {
        super(mensagem);
    }

    public ApiException(String mensagem, Throwable exception) {
        super(mensagem, exception);
    }

    public ApiException(String mensagem, String descricao, String codigo) {
        super(mensagem);
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public ApiException(String codigo, String descricao) {
        super();
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getdescricao() {
        return descricao;
    }

    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }

    public Response responseError() {
        ResponseDTO<String> resposta = new ResponseDTO<String>();
        resposta.setCodigo(99);
        if (getMessage() != null) {
            resposta.setresposta(getMessage());
        } else {
            resposta.setresposta("Foi gerado um erro desconhecido ao gerar resposta.");
        }
        GenericEntity<ResponseDTO<String>> respostaGenerica = new GenericEntity<ResponseDTO<String>>(resposta) {
        };
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respostaGenerica).build();
    }

}
