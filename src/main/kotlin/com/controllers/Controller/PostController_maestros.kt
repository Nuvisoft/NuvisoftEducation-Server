package com.controllers.Controller

import com.Service.Service.PostManagementService
import com.dto.dto.dto_asignatura
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class PostController_maestros {
    @Autowired
    private val service: PostManagementService? = null

    @GetMapping(value = ["/greet/{name}"])
    fun greet(@PathVariable(value = "name") name: String): String? {
        return "Hello, $name"
    }

    @GetMapping(value = ["/list"])
    fun list(): ResponseEntity<*>? {
        return ResponseEntity<Any?>(service!!.list(), HttpStatus.OK)
    }

    @PostMapping("/add")
    fun add(@RequestBody post: dto_asignatura?): ResponseEntity<*>? {
        return ResponseEntity<Any?>(service!!.add(post!!), HttpStatus.OK)
    }

    @PutMapping(value = ["/{id}/update"])
    fun edit(@PathVariable(value = "id") id: String?, @RequestBody post: dto_asignatura?): ResponseEntity<*>? {
        return ResponseEntity<Any?>(service!!.edit(id, post), HttpStatus.OK)
    }

    @DeleteMapping(value = ["/{id}/delete"])
    fun delete(@PathVariable(value = "id") id: String?): ResponseEntity<*>? {
        return ResponseEntity<Any?>(service!!.delete(id), HttpStatus.OK)
    }

}