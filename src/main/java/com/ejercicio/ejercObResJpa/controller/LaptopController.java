package com.ejercicio.ejercObResJpa.controller;

import com.ejercicio.ejercObResJpa.entities.Laptop;
import com.ejercicio.ejercObResJpa.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    // Atributos
    private final LaptopRepository laptopRepository;


    // Constructores

    public LaptopController(LaptopRepository laptopRepository) {

        this.laptopRepository = laptopRepository;
    }

    /**
     * Buscar todos los libros que hay en base de datos (ArrayList de libros)
     * <a href="http://localhost:8080/api/laptops">...</a>
     */


    @GetMapping("/api/laptops")
    public List<Laptop> findAll() {
        //Recuperar y devolver los laptop de base de datos
        return laptopRepository.findAll();

    }

    /**
     * <a href="http://localhost:8080/api/books/1">...</a>
     * <a href="http://localhost:8080/api/books/2">...</a>
     * Request
     * Response
     */

    // Buscar un solo laptop en base de datos según su id
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar un libro por clave primaria id Long")

    public ResponseEntity<Laptop> findOneById(@ApiParam("clave primaria tipo Long") @PathVariable Long id) {

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        //Opcion 1
        return laptopOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        //Opcion 2// return laptopOpt.orElse(null);
        // return laptopOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    /**
     * Crear un nuevo libro en base de datos
     * Método POST, no colisiona con findAll porque son diferentes métodos HTTP: GET vs. POST
     */

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));

        //  Guardar el laptop recibido por parámetro en la base de datos

        if (Laptop.getId() != null) {  //Quiere decir que existe id y, por tanto, no es una creación
            log.warn("trying to create a laptop with id");
            System.out.println("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();

        }
        Laptop result = laptopRepository.save(laptop);

        return ResponseEntity.ok(result);  //El laptop devuelto tiene una clave primaria

    }

    /**
     * Actualizar un laptop existente en base de datos
     */
    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {


        if (Laptop.getId() == null) { //Sí no tiene Id quiere decir que si es una creación
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }
        if (!laptopRepository.existsById(Laptop.getId())) {
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }


        //El proceso de actualizacion
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria

    }
    @ApiIgnore
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @ApiIgnore // ignorar este método para que no aparezca en la documentación de la api Swagger
    @DeleteMapping("/api/books")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Request for delete all books");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
