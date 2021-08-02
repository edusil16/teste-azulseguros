package com.azulseguros.service;

import com.azulseguros.domain.dto.EnderecoViaCepDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

public class BuscarEnderecoService {
    public ArrayList<EnderecoViaCepDTO> obterEndereco(String cep){

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("https://viacep.com.br/ws/"+ cep +"/json/");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        EnderecoViaCepDTO response = invocationBuilder.get(EnderecoViaCepDTO.class);

        ArrayList<EnderecoViaCepDTO> endereco = new ArrayList<>();
        endereco.add(response);

        return endereco;
    }

}
