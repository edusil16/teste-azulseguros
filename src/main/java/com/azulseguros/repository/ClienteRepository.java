package com.azulseguros.repository;

import com.azulseguros.domain.dto.ClienteDTO;
import com.azulseguros.domain.dto.EnderecoViaCepDTO;
import com.azulseguros.exception.ApiException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClienteRepository {
    public List<ClienteDTO> obterTodos() throws ApiException;
    public ClienteDTO obterPorId(Long id) throws ApiException;
    public ClienteDTO salvar(ClienteDTO cliente, EnderecoViaCepDTO endereco) throws ApiException;
    public ClienteDTO atualizar(Long id, ClienteDTO cliente) throws ApiException;
    public void excluir(Long id) throws ApiException;
}
