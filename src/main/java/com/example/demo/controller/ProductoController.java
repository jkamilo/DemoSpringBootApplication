package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Producto;
import com.example.demo.repository.ProductoRepository;

@RestController()
@RequestMapping("")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@GetMapping("/v1/productos")
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@GetMapping("/v1/tipoproducto/{idTipoProducto}/productos")
	public List<Producto> findByIdTipoProducto(@PathVariable(value = "idTipoProducto") int idTipoProducto) {
		return productoRepository.findByIdTipoProducto(idTipoProducto);
	}
}