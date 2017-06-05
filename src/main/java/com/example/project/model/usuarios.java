package com.example.project.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import com.example.project.model.equiposinformaticos;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.ManyToMany;

@Entity
public class usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String nombre;

	@Column
	private String contraseña;

	@Column
	private String perfil;

	@Column
	private String grupo;

	@ManyToMany
	private Set<equiposinformaticos> equipos = new HashSet<equiposinformaticos>();

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
		if (!(obj instanceof usuarios)) {
			return false;
		}
		usuarios other = (usuarios) obj;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nombre != null && !nombre.trim().isEmpty())
			result += "nombre: " + nombre;
		if (contraseña != null && !contraseña.trim().isEmpty())
			result += ", contraseña: " + contraseña;
		if (perfil != null && !perfil.trim().isEmpty())
			result += ", perfil: " + perfil;
		if (grupo != null && !grupo.trim().isEmpty())
			result += ", grupo: " + grupo;
		return result;
	}

	public Set<equiposinformaticos> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(final Set<equiposinformaticos> equipos) {
		this.equipos = equipos;
	}
}