package com.Service.Service;

import com.dto.dto.dto_tarea;

import java.util.List;

public interface Post_tarea_Service {
    List<dto_tarea> list();

    Boolean add(dto_tarea post);

    Boolean edit(String id,dto_tarea post);

    Boolean delete(String id);

}
