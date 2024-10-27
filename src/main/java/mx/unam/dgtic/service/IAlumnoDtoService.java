package mx.unam.dgtic.service;

import mx.unam.dgtic.dto.AlumnoDto;
import mx.unam.dgtic.model.Alumno;

import java.text.ParseException;
import java.util.List;

public interface IAlumnoDtoService {
    List<AlumnoDto> getAlumnoList();
    List<Alumno> getAlumnosPageable(int page,
                                    int size,
                                    String dirSort,
                                    String sort);
    AlumnoDto getAlumnoById(String matricula);
    AlumnoDto updateAlumno(AlumnoDto alumno) throws ParseException;
    List<AlumnoDto> findAlumnosByEstado(String estado);
    AlumnoDto createAlumno(AlumnoDto alumno) throws ParseException;
    boolean deleteAlumno(String matricula);
}
