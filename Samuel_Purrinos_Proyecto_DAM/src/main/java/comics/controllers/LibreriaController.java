package comics.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import comics.dao.ColeccionRepository;
import comics.dao.ComicRepository;
import comics.entity.Coleccion;
import comics.entity.Numero;
/**
 * Esta clase se encarga de hacer las consultas contra la base de datos y devolver los resultados
 * @author samuel
 * @version 1.0
 * @since 9/10/21
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/numeros")
public class LibreriaController {
	
	/**
	 * Objetos que se encargan de hacer las consultas a la base de datos
	 */
	
	private ComicRepository repository;
	private ColeccionRepository coleccionRepository;
	
	public LibreriaController(ComicRepository repository, ColeccionRepository coleccionRepository) {
		this.repository = repository;
		this.coleccionRepository = coleccionRepository;
	}
	/**
	 * Devuelve todos los cómics
	 * @return una lista de todos los números de la base de datos
	 */
	@GetMapping()
	public Iterable<Numero> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Devuelve los número cuyo nombre de editorial y colección coincidan con los pasados por parámetro
	 * @param coleccion
	 * @return una lista de números
	 */
	@GetMapping(path = "/{nombre}")
	public Iterable<Numero> findByColeccion(@PathVariable("nombre") String nombre){
		return repository.findByColeccionNombre(nombre);
	}
	/**
	 * Inserta el número y la colección pasados por parámetro en la base de datos
	 * @param numero
	 * @return
	 */
	@PostMapping()
	public Numero insertNumero(@RequestBody Numero numero) {
		coleccionRepository.save(numero.getColeccion());
		return repository.save(numero);
	}
	/**
	 * Elimina el número y la colección que se pasan por parámetro de la base de datos
	 * @param numero
	 * @return
	 */
	@DeleteMapping("/{id}")
	public void eliminaNumero(@PathVariable("id") int id) {
		repository.deleteById(id);
	}
	
	/**
	 * Modifica el número cuya id coincida con la del número pasado por parámetro en la base de datos
	 * @param numero
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Numero> updateNumero(@PathVariable int id,@RequestBody Numero numero) {
		return (!repository.existsById(id)) ?
				new ResponseEntity<>(repository.save(numero),HttpStatus.CREATED) :
				new ResponseEntity<>(repository.save(numero), HttpStatus.OK)	;
	}
	
	@GetMapping(value="/index",produces = "text/html")
	public String getIndexayuda(Model modelo) {
		return "indexayuda";
	}
	
	
}
