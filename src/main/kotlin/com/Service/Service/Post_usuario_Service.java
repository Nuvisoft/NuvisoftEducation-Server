package com.Service.Service;

import com.dto.dto.dto_trabajos;
import com.dto.dto.dto_usuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface Post_usuario_Service {
    List<dto_usuario> list();

    Boolean add(dto_usuario post);

    Boolean edit(String id,dto_usuario post);

    Boolean delete(String id);
}
