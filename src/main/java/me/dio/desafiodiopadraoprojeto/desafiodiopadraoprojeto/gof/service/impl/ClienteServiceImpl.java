package me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto.gof.service.impl;

import me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto.gof.model.Cliente;
import me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto.gof.model.ClienteRepository;
import me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto.gof.model.Endereco;
import me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto.gof.model.EnderecoRepository;
import me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto.gof.service.ClienteService;
import me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.isPresent() ? clienteOptional.get() : null;
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteAtualizandoEndereco(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            salvarClienteAtualizandoEndereco(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
       clienteRepository.deleteById(id);
    }

    private void salvarClienteAtualizandoEndereco(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
