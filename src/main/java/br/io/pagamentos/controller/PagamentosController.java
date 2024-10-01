package br.io.pagamentos.controller;

import br.io.pagamentos.dto.PagamentosDto;
import br.io.pagamentos.service.PagamentosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentosController {

    private PagamentosService service;

    public PagamentosController(PagamentosService service) {
        this.service = service;
    }
    @GetMapping
    public Page<PagamentosDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentosDto> detalhar(@PathVariable @NotNull Long id) {
        PagamentosDto dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<PagamentosDto> cadastrar(@RequestBody @Valid PagamentosDto dto, UriComponentsBuilder uriBuilder) {
        PagamentosDto pagamento = service.criarPagamento(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentosDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentosDto dto) {
        PagamentosDto atualizado = service.atualizarPagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentosDto> remover(@PathVariable @NotNull Long id) {
        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
