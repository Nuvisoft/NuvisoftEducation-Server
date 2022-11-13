package com.Service.Service;

import com.dto.dto.dto_tarea;
import com.dto.dto.dto_trabajos;

import java.util.List;

public interface Post_trabajos_Service {

    List<dto_trabajos> list();

    Boolean add(dto_trabajos post);

    Boolean edit(String id,dto_trabajos post);

    Boolean delete(String id);
}
