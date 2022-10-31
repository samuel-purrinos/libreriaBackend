package comics.entity;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.BlobType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Esta clase mapea la tabla número
 * @author samuel
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Numero implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int volumen;
	private Date fecha_publicacion;
	private Date fecha_compra;
	private String autor;
	private double precio;
	@Lob
	private byte[] portada;
	@Transient
	private String imagen;
	@ManyToOne(fetch = FetchType.EAGER, optional = false,cascade=  CascadeType.MERGE)
    @JoinColumn(name = "coleccion_id",nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Coleccion coleccion;
	@Enumerated(EnumType.STRING)
	@JsonProperty("estado")
	private Estado estado;
	
	
	

	public Numero(int volumen, Date fecha_publicacion, Date fecha_compra, String autor, double precio,
			Coleccion coleccion) {
		this.volumen = volumen;
		this.fecha_publicacion = fecha_publicacion;
		this.fecha_compra = fecha_compra;
		this.autor = autor;
		this.precio = precio;
		this.coleccion = coleccion;
	}

	public Numero() {
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_compra() {
		return fecha_compra;
	}
	public void setFecha_compra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Coleccion getColeccion() {
		return coleccion;
	}
	public void setColeccion(Coleccion coleccion) {
		this.coleccion = coleccion;
	}
	
	public byte[] getPortada() {
		return portada;
	}
	public void setPortada(byte[] portada) {
		this.portada = portada;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}
	
	/**public void setImagen() {
		imagen = Base64.getEncoder().encodeToString(portada);
	}*/
	public String getImagen() {
		return imagen;
	}
	public enum Estado {
	    malo, roto, bueno, excelente
	}
}
