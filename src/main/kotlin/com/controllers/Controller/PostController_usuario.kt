package com.controllers.Controller

import com.Service.Service.Post_usuario_Service
import com.dto.dto.dto_asignaturas
import com.dto.dto.dto_usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuario")

class PostController_usuario {

        @Autowired
        private val service: Post_usuario_Service? = null


        @GetMapping(value = ["/list"])
        fun list(): ResponseEntity<*> {
            return ResponseEntity<Any?>(service!!.list(), HttpStatus.OK)
        }

        @PostMapping("/add")
        fun add(@RequestBody post: dto_usuario?): ResponseEntity<*> {
            return ResponseEntity<Any?>(service!!.add(post!!), HttpStatus.OK)
        }

        @PutMapping(value = ["/{id}/update"])
        fun edit(@PathVariable(value = "id") id: String?, @RequestBody post: dto_usuario?): ResponseEntity<*> {
            return ResponseEntity<Any?>(service!!.edit(id, post), HttpStatus.OK)
        }

        @DeleteMapping(value = ["/{id}/delete"])
        fun delete(@PathVariable(value = "id") id: String?): ResponseEntity<*> {
            return ResponseEntity<Any?>(service!!.delete(id), HttpStatus.OK)
        }
    }


