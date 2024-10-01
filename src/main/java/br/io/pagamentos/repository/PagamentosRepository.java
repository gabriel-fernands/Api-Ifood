package br.io.pagamentos.repository;

import br.io.pagamentos.model.Pagamentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentosRepository extends JpaRepository<Pagamentos,Long> {
}
