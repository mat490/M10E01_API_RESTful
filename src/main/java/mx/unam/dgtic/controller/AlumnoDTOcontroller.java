package mx.unam.dgtic.controller;

import jdk.dynalink.Operation;
import mx.unam.dgtic.dto.AlumnoDto;
import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.service.IAlumnoDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v2/alumnos")
public class AlumnoDTOcontroller {

    @Autowired
    IAlumnoDtoService alumnoDtoService;

    @GetMapping(path = "/")
    public List<AlumnoDto> getAllDto(){
        return alumnoDtoService.getAlumnoList();
    }

    @GetMapping(path = "/{matricula}")
    public ResponseEntity<AlumnoDto> getByIdDto(@PathVariable String matricula){
        AlumnoDto alumnoDto = alumnoDtoService.getAlumnoById(matricula);
        if (alumnoDto != null){
            return ResponseEntity.ok(alumnoDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/estados/{estado}")
    public ResponseEntity getByEstadiDto(@PathVariable String edo){
        List<AlumnoDto> alumnoDtos = alumnoDtoService.findAlumnosByEstado(edo);
        if (alumnoDtos != null){
            return ResponseEntity.ok(alumnoDtos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/paginado")
    public ResponseEntity<List<Alumno>> getAlumnosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "asc") String dirSort,
            @RequestParam(defaultValue = "matricula") String sort
    ){
        return ResponseEntity.ok(alumnoDtoService.getAlumnosPageable(page, size, dirSort, sort));

    }

    @PostMapping(path = "/")
    public ResponseEntity<AlumnoDto> createDto(@RequestBody AlumnoDto alumnoDto) throws ParseException,
            URISyntaxException {
        AlumnoDto alumnoDtoSalvado = alumnoDtoService.createAlumno(alumnoDto);
        URI location = new URI("/api/v2/alumnos"+alumnoDto.getMatricula());
        if (alumnoDtoSalvado != null){
            return ResponseEntity.created(location).body(alumnoDtoSalvado);
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping(path = "/{matricula}")
    public ResponseEntity<AlumnoDto> updateAlumnoDto(@PathVariable String matricula ,
                                                     @RequestBody AlumnoDto alumnoDto) throws ParseException {
        AlumnoDto alumnoDtoSalvado = alumnoDtoService.updateAlumno(alumnoDto);
        if (alumnoDtoSalvado != null){
            return ResponseEntity.ok(alumnoDtoSalvado);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping(path = "/{matricula}")
    public ResponseEntity<AlumnoDto> partialUpdate(@PathVariable String matricula,
                                                   @RequestBody AlumnoDto alumnoDto) throws ParseException {
        AlumnoDto alumnoDtoDb = alumnoDtoService.getAlumnoById(matricula);
        if (alumnoDtoDb != null){
            if (alumnoDto.getNombre() != null) alumnoDtoDb.setNombre(alumnoDtoDb.getNombre());
            if (alumnoDto.getPaterno() != null) alumnoDtoDb.setPaterno(alumnoDtoDb.getPaterno());
            if (alumnoDto.getFnac() != null) alumnoDtoDb.setFnac(alumnoDtoDb.getFnac());
            if (alumnoDto.getEstatura() != 0.0) alumnoDtoDb.setEstatura(alumnoDtoDb.getEstatura());
            if (alumnoDto.getEstado() != null) alumnoDtoDb.setEstado(alumnoDtoDb.getEstado());
            return ResponseEntity.ok(alumnoDtoService.updateAlumno(alumnoDtoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{matricula}")
    public ResponseEntity<AlumnoDto> deleteAlumno(@PathVariable String matricula){
        return alumnoDtoService.deleteAlumno(matricula) ?
                    ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
