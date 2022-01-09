package comics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Esta clase se encarga de mostrar las páginas html de ayuda
 * @author samuel
 *
 */
@Controller
@RequestMapping("/ayuda")
public class ayudaController {
	@GetMapping("/index")
	public String getIndex(Model modelo) {
		return "ayudaindex";
	}
	
	@GetMapping("/xestion")
	public String getXestion(Model modelo) {
		return "xestion";
	}
	
	@GetMapping("/galegoinformes")
	public String getGalegoInformes(Model modelo) {
		return "galegoinformes";
	}
	
	@GetMapping("/galegoindex")
	public String getGalegoIndex(Model modelo) {
		return "galegoindex";
	}
	
	@GetMapping("/gestion")
	public String getGestion(Model modelo) {
		return "gestion";
	}
	
	@GetMapping("/informes")
	public String getInformes(Model modelo) {
		return "informes";
	}


}
