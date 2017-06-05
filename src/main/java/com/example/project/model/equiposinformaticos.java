package com.example.project.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

@Entity
public class equiposinformaticos implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String serie;

	@Column
	private String marca;

	@Column
	private String color;

	@Column
	private String estatus;

	@Column
	private String modelo;

	@Column
	private String interfaz;

	@Column
	private String fechamantto;

	@Column
	private String observaciones;

	@Column
	private String fechabaja;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof equiposinformaticos)) {
			return false;
		}
		equiposinformaticos other = (equiposinformaticos) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(String interfaz) {
		this.interfaz = interfaz;
	}

	public String getFechamantto() {
		return fechamantto;
	}

	public void setFechamantto(String fechamantto) {
		this.fechamantto = fechamantto;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFechabaja() {
		return fechabaja;
	}

	public void setFechabaja(String fechabaja) {
		this.fechabaja = fechabaja;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (serie != null && !serie.trim().isEmpty())
			result += "serie: " + serie;
		if (marca != null && !marca.trim().isEmpty())
			result += ", marca: " + marca;
		if (color != null && !color.trim().isEmpty())
			result += ", color: " + color;
		if (estatus != null && !estatus.trim().isEmpty())
			result += ", estatus: " + estatus;
		if (modelo != null && !modelo.trim().isEmpty())
			result += ", modelo: " + modelo;
		if (interfaz != null && !interfaz.trim().isEmpty())
			result += ", interfaz: " + interfaz;
		if (fechamantto != null && !fechamantto.trim().isEmpty())
			result += ", fechamantto: " + fechamantto;
		if (observaciones != null && !observaciones.trim().isEmpty())
			result += ", observaciones: " + observaciones;
		if (fechabaja != null && !fechabaja.trim().isEmpty())
			result += ", fechabaja: " + fechabaja;
		return result;
	}
}