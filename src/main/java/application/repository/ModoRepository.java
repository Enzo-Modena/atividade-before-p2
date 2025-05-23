package application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import application.model.Modo;

public interface ModoRepository extends CrudRepository<Modo, Long> {
    public List<Modo> findByDescricao(String descricao);
}
