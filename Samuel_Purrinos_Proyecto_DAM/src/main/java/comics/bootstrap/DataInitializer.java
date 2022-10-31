package comics.bootstrap;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import comics.dao.ColeccionRepository;
import comics.dao.ComicRepository;
import comics.entity.Coleccion;
import comics.entity.Numero;

@Component
public class DataInitializer implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ColeccionRepository coleccionRepository;
    private final ComicRepository comicRepository;

    public DataInitializer(ColeccionRepository coleccionRepository,ComicRepository comicRepository) {
        this.coleccionRepository= coleccionRepository;
        this.comicRepository = comicRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Coleccion spiderman = new Coleccion();
        spiderman.setNombre("Spiderman");
        spiderman.setEditorial("Marvel");
        Coleccion vengadores = new Coleccion();
        vengadores.setNombre("vengadores");
        vengadores.setEditorial("Marvel");
        Coleccion drSlump = new Coleccion();
        drSlump.setNombre("Dr Slump");
        drSlump.setEditorial("Tokio");
        
        Numero spiderman1 = new Numero();
        spiderman1.setAutor("Stan Lee");
        spiderman1.setEstado(Numero.Estado.bueno);
        spiderman1.setFecha_compra(new Date());
        spiderman1.setFecha_publicacion(new Date());
        spiderman1.setPortada(leerPortada("spiderman.jpg"));
        spiderman1.setPrecio(200);
        spiderman1.setVolumen(1);
        spiderman1.setColeccion(spiderman);
        Numero vengadores1 = new Numero();
        vengadores1.setAutor("Stan Lee");
        vengadores1.setEstado(Numero.Estado.bueno);
        vengadores1.setFecha_compra(new Date());
        vengadores1.setFecha_publicacion(new Date());
        vengadores1.setPortada(leerPortada("vengadores.jpg"));
        vengadores1.setPrecio(200);
        vengadores1.setVolumen(1);
        vengadores1.setColeccion(vengadores);
        Numero slump1 = new Numero();
        slump1.setAutor("Akira Toriyama");
        slump1.setEstado(Numero.Estado.bueno);
        slump1.setFecha_compra(new Date());
        slump1.setFecha_publicacion(new Date());
        slump1.setPortada(leerPortada("drSlump.jpg"));
        slump1.setPrecio(200);
        slump1.setVolumen(1);
        slump1.setColeccion(drSlump);
        coleccionRepository.save(spiderman);
        coleccionRepository.save(vengadores);
        coleccionRepository.save(drSlump);
        comicRepository.save(spiderman1);
        comicRepository.save(vengadores1);
        comicRepository.save(slump1);
        logger.info("Guardados "+comicRepository.count()+" números y "+coleccionRepository.count()+" colecciones");
    }
    
    private byte[] leerPortada(String archivo) {
        BufferedImage bufferimage ;
        ByteArrayOutputStream output = null;
        try {
        	bufferimage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource(archivo));
        	output = new ByteArrayOutputStream();
			ImageIO.write(bufferimage, "jpg", output );
		} catch (IOException e) {
			logger.debug("No se ha podido leer la portada {},",e.getMessage());
			e.printStackTrace();
		}
        return  output.toByteArray();
    }
}