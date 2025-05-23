package application.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import application.model.Plataforma;

public interface PlataformaRepository extends CrudRepository<Plataforma, Long> {
    public List<Plataforma> findByDescricao(String descricao);
}
