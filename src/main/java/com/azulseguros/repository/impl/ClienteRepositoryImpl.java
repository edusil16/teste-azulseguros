package com.azulseguros.repository.impl;

import com.azulseguros.domain.dto.ClienteDTO;
import com.azulseguros.domain.dto.EnderecoViaCepDTO;
import com.azulseguros.domain.model.ClienteModel;
import com.azulseguros.domain.model.EnderecoModel;
import com.azulseguros.exception.ApiException;
import com.azulseguros.repository.ClienteRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClienteRepositoryImpl implements ClienteRepository {

    private static final String MSG_NO_RESULT_EXCEPTION = "Resultado não encontrados.";
    private static final String MSG_EXCEPTION = "Erro desconhecido ao gerar resultado.";

    private static final String PARAM_CODIGO = "email";
    private static final String NAMED_QUERY_BY_FIND_ONE = "ClienteModel.findByEmail";
    private static final String NAMED_QUERY_BY_FIND_ALL = "ClienteModel.findAll";


    @PersistenceContext(unitName = "FormulariosPersistence")
    private EntityManager em;


    @Override
    public List<ClienteDTO> obterTodos() throws ApiException {
        try {
            Query nameQuery = em.createNamedQuery(NAMED_QUERY_BY_FIND_ALL);
            List<ClienteModel> entityResult = nameQuery.getResultList();
            List<ClienteDTO> resultado = null;
            if (entityResult != null && !entityResult.isEmpty()) {
                resultado = new ArrayList<>();
                ClienteDTO dto = null;
                for (ClienteModel e : entityResult) {
                    dto = new ClienteDTO();
                    dto.setCpf(e.getCpf());
                    dto.setNome(e.getNome());
                    dto.setCpf(e.getCpf());
                    resultado.add(dto);
                }
            }
            return resultado;
        } catch (NoResultException e) {
            throw new ApiException(MSG_NO_RESULT_EXCEPTION, e);
        } catch (Exception e) {
            throw new ApiException(MSG_EXCEPTION, e);
        }
    }

    @Override
    public ClienteDTO obterPorId(Long id) throws ApiException {
        try {
            Query nameQuery = em.createNamedQuery(NAMED_QUERY_BY_FIND_ONE);
            nameQuery.setParameter(PARAM_CODIGO, id);
            ClienteModel entityResult = (ClienteModel) nameQuery.getSingleResult();
            ClienteDTO dto = new ClienteDTO();
            dto.setNome(entityResult.getNome());
            dto.setCpf(entityResult.getCpf());
            dto.setEmail(entityResult.getEmail());
            return dto;
        } catch (NoResultException e) {
            throw new ApiException(MSG_NO_RESULT_EXCEPTION, e);
        } catch (Exception e) {
            throw new ApiException(MSG_EXCEPTION, e);
        }
    }

    @Override
    public ClienteDTO salvar(ClienteDTO cliente, EnderecoViaCepDTO endereco) throws ApiException {
        try {
            ClienteModel entity = new ClienteModel();
            entity.setNome(cliente.getNome());
            entity.setCpf(cliente.getCpf());
            entity.setEmail(cliente.getEmail());

            EnderecoModel endEntity = new EnderecoModel();
            endEntity.setCep(endereco.getCep());
            endEntity.setBairro(endereco.getBairro());
            endEntity.setLocalidade(endereco.getLocalidade());
            endEntity.setComplemento(endereco.getComplemento());
            endEntity.setLogradouro(endereco.getLogradouro());
            endEntity.setUf(endereco.getUf());
            entity.setEndereco(endEntity);
            ClienteModel entityResult = em.merge(entity);

            cliente.setId(entityResult.getId());
            return cliente;
        } catch (NoResultException e) {
            throw new ApiException(MSG_NO_RESULT_EXCEPTION, e);
        } catch (Exception e) {
            throw new ApiException(MSG_EXCEPTION, e);
        }
    }

    @Override
    public ClienteDTO atualizar(Long id, ClienteDTO cliente) throws ApiException {
        try {
            ClienteModel entity = new ClienteModel();
            entity.setNome(cliente.getNome());
            entity.setCpf(cliente.getCpf());
            entity.setEmail(cliente.getEmail());
            entity.setEndereco(cliente.getEndereco());
            entity.setId(id);
            ClienteModel entityResult = em.merge(entity);
            em.flush();
            cliente.setNome(entityResult.getNome());
            cliente.setEmail(entityResult.getEmail());
            cliente.setCpf(entityResult.getCpf());
            return cliente;
        } catch (NoResultException e) {
            throw new ApiException(MSG_NO_RESULT_EXCEPTION, e);
        } catch (Exception e) {
            throw new ApiException(MSG_EXCEPTION, e);
        }
    }

    @Override
    public void excluir(Long id) throws ApiException {
        try {
            ClienteModel e = em.find(ClienteModel.class, id);
            if (e != null) {
                em.remove(e);
                em.flush();
            }
        } catch (NoResultException e) {
            throw new ApiException(MSG_NO_RESULT_EXCEPTION, e);
        } catch (Exception e) {
            throw new ApiException(MSG_EXCEPTION, e);
        }
    }
}
