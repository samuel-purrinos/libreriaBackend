package comics.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comics.entity.Coleccion;
import comics.entity.Numero;
/**
 * Esta clase realiza consultas a la base de datos
 * @author samuel
 *
 */

@Repository
public interface ComicRepository extends CrudRepository<Numero,Integer>{
	List<Numero> findByColeccionNombreAndColeccionEditorial(String nombre,String editorial);
	Numero findByVolumenAndColeccionNombre(int volumen,String nombre);
	Numero findById(int id);
	
}
