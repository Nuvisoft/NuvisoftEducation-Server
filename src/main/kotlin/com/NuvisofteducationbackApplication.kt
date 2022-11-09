package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@SpringBootApplication
@ComponentScan("com")
class NuvisofteducationbackApplication

fun main(args: Array<String>) {
	runApplication<NuvisofteducationbackApplication>(*args)
}
