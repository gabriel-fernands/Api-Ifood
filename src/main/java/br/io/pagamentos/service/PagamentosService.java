package br.io.pagamentos.service;

import br.io.pagamentos.dto.PagamentosDto;
import br.io.pagamentos.enus.Status;
import br.io.pagamentos.model.Pagamentos;
import br.io.pagamentos.repository.PagamentosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentosService {

private PagamentosRepository repository;
private ModelMapper modelMapper;

    public PagamentosService(PagamentosRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
    public Page<PagamentosDto> obterTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentosDto.class) );
    }
    public PagamentosDto obterPorId(Long id){
        Pagamentos pagamentos = repository.findById(id)
                .orElseThrow(RuntimeException::new);
        return modelMapper.map(pagamentos, PagamentosDto.class);
    }
    public PagamentosDto criarPagamento(PagamentosDto dto){
        Pagamentos pagamentos = modelMapper.map(dto, Pagamentos.class);
        pagamentos.setStatus(Status.devolverStatus(0));
        repository.save(pagamentos);
        return modelMapper.map(pagamentos, PagamentosDto.class);
    }
    private Pagamentos setStatus(Pagamentos payment, int statusValue) {
        payment.setStatus(Status.devolverStatus(statusValue));
        return payment;
    }

    public PagamentosDto atualizarPagamento(Long id, PagamentosDto dto){
        Pagamentos pagamentos = modelMapper.map(dto, Pagamentos.class);
        pagamentos.setId(id);
        pagamentos = repository.save(pagamentos);
        return modelMapper.map(pagamentos, PagamentosDto.class);
    }
    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }

}
