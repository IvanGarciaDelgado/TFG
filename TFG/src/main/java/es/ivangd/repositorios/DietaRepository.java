package es.ivangd.repositorios;

import es.ivangd.entidades.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {

    List<Dieta> findByCalorias(int calorias);

}
