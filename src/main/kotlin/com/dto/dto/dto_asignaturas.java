package com.dto.dto;

import lombok.Data;

@Data //Genera todos los get y set al momento de la compilación
public class dto_asignaturas {
    private String id_asignaturas;
    private String Titulo;
    private String descripcion;
    private String estudiante;
    private String profesor;
    private String tarea;

}
