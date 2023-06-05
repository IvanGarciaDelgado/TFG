package es.ivangd.repositorios;

import es.ivangd.entidades.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.nombre = ?1")
    Role findByName(String nombre);

    Role findFirstByNombre(String nombre);

    boolean existsByNombre(String role_user);
}
