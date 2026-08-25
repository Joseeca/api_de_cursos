package br.com.joseoliveira.api_de_cursos.repository;

import br.com.joseoliveira.api_de_cursos.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

    // Query que filtra pelos campos SE eles não forem nulos (ignorando maiúsculas/minúsculas)
    @Query("SELECT c FROM CursoEntity c WHERE " +
            "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:category IS NULL OR LOWER(c.category) LIKE LOWER(CONCAT('%', :category, '%')))")
    List<CursoEntity> findByNameAndCategory(@Param("name") String name, @Param("category") String category);
}