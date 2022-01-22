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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class LibreriaController {
	
	/**
	 * Objetos que se encargan de hacer las consultas a la base de datos
	 */
	
	@Autowired
	private ComicRepository repository;
	@Autowired
	private ColeccionRepository coleccionRepository;
	/**
	 * Devuelve todos los cómics
	 * @return una lista de todos los números de la base de datos
	 */
	@GetMapping("/findAll")
	public Iterable<Numero> findAll() {
		Iterable<Numero> todos=repository.findAll();
		/*for(Numero n:todos) {
			n.setImagen();
			System.out.println(n.getImagen());;
		}*/
		return todos;
	}
	/**
	 * Encuentra todas las colecciones
	 * @return una lista con todas las colecciones
	 */
	@GetMapping("/findAllCollections")
	public Iterable<Coleccion> findAllCollections() {

		return coleccionRepository.findAll();
	}
	/**
	 * Encuentra en número cuyo volumen y nombre de colección coinciden con las pasados por parámetro
	 * @param numero
	 * @return devuelve un número
	 */
	@PostMapping(value = "/findbynombreandvolumen", consumes = "application/json", produces = "application/json")
	public Numero findByNombreAndVolumen(@RequestBody Numero numero) {
		System.out.println("Coleccion : "+numero.getColeccion().getNombre()+" Volumen : "+numero.getVolumen());
		Numero resultado=repository.findByVolumenAndColeccionNombre(numero.getVolumen(), numero.getColeccion().getNombre());
		if(resultado!=null)System.out.println("Econtrado : "+resultado.getId());
		return resultado;
	}
	/**
	 * Devuelve los número cuyo nombre de editorial y colección coincidan con los pasados por parámetro
	 * @param coleccion
	 * @return una lista de números
	 */
	@GetMapping(path = "/findbycoleccion/{nombre}", produces = "application/json")
	public Iterable<Numero> findByColeccion(@PathVariable("nombre") String nombre){
		Iterable<Numero> resultado=repository.findByColeccionNombre(nombre);
		return resultado;
	}
	/**
	 * Inserta el número y la colección pasados por parámetro en la base de datos
	 * @param numero
	 * @return
	 */
	@PostMapping(value = "/insertarnumero", consumes = "application/json", produces = "application/json")
	public Numero insertNumero(@RequestBody Numero numero) {
		System.out.println(numero);
		coleccionRepository.save(numero.getColeccion());
		return repository.save(numero);
	}
	/**
	 * Elimina el número y la colección que se pasan por parámetro de la base de datos
	 * @param numero
	 * @return
	 */
	@GetMapping(path = "/eliminanumero/{id}", produces = "application/json")
	public ResponseEntity<String> eliminaNumero(@PathVariable("id") int id) {
		System.out.println("Vamos a eliminar : "+id);
		repository.deleteById(id);
		return new ResponseEntity<>("Se ha borrado correctamente",HttpStatus.OK);
	}
	
	/**
	 * Modifica el número cuya id coincida con la del número pasado por parámetro en la base de datos
	 * @param numero
	 * @return
	 */
	@PostMapping(value = "/updatenumero", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateNumero(@RequestBody Numero numero) {
		System.out.println("Numero : "+numero.getId()+" Coleccion : "+numero.getColeccion().getId());
		Numero temporal =repository.findById(numero.getId());
		temporal.setAutor(numero.getAutor());
		temporal.setColeccion(numero.getColeccion());
		temporal.setFecha_compra(numero.getFecha_compra());
		temporal.setPortada(numero.getPortada());
		temporal.setFecha_publicacion(numero.getFecha_publicacion());
		temporal.setEstado(numero.getEstado());
		temporal.setPrecio(numero.getPrecio());
		temporal.setVolumen(numero.getVolumen());
		repository.save(temporal);
		return new ResponseEntity<>("Se ha actualizado correctamente",HttpStatus.OK);
	}
	
	@GetMapping(value="/index",produces = "text/html")
	public String getIndexayuda(Model modelo) {
		return "indexayuda";
	}
	
	
}
