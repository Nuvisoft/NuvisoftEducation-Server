package com.Service.Service;

import com.dto.dto.dto_asignaturas;


import java.util.List;

public interface Post_asignaturas_Service {
    List<dto_asignaturas> list();

    Boolean add(dto_asignaturas post);

    Boolean edit(String id,dto_asignaturas post);

    Boolean delete(String id);
}
