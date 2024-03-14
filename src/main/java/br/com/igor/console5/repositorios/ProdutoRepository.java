package br.com.igor.console5.repositorios;

import br.com.igor.console5.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
