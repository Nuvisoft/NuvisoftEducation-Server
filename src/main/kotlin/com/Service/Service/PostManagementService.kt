package com.Service.Service

import com.dto.dto.dto_asignatura
import org.springframework.stereotype.Service


@Service
interface PostManagementService {
    fun list(): List<dto_asignatura>?

    fun add(post: dto_asignatura): Boolean?

    fun edit(id: String?, post: dto_asignatura?): Boolean?

    fun delete(id: String?): Boolean?
}