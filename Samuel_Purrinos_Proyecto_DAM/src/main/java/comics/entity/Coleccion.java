package comics.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Esta clase mapea la tabla colección
 * @author samuel
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Coleccion implements Serializable{
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String editorial;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="coleccion")
	@JsonIgnore
	private Set<Numero> numeros;
	public Coleccion() {
	}
	
	public Coleccion(String nombre, String editorial) {
		super();
		this.nombre = nombre;
		this.editorial = editorial;
	}
	
	public Set<Numero> getNumeros() {
		return numeros;
	}

	public void setNumeros(Set<Numero> numero) {
		this.numeros = numero;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

}
