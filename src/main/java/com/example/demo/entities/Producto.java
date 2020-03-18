package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "producto", schema = "DB_Produccion")
@Data
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idproducto")
	private Integer idProducto;

	@Column(name = "idtipoproducto")
	private Integer idTipoProducto;

	@Column(name = "nombreproducto")
	private String NombreProducto;

	@Column(name = "tallosramoestandar")
	private int tallosRamoEstandar;

	@Column(name = "productocodigonegocio")
	private String productoCodigoNegocio;

	@Column(name = "orden")
	private int orden;

	@Column(name = "activo")
	private boolean activo;
}