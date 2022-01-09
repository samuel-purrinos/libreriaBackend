package comics.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comics.entity.Coleccion;

@Repository
public interface ColeccionRepository extends CrudRepository<Coleccion,Integer>{

		public Optional<Coleccion> findByNombre(String nombre);
}
