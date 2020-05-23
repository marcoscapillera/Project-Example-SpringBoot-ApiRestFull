package ejemplo.api.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Usuario implements UserDetails{
	
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	

	@Column(unique = true)
	private String login;
	
	private String password;
	
	
	private String nombre;
	
	
	
	@OneToMany(mappedBy =  "usuario", orphanRemoval = true, cascade=CascadeType.ALL, fetch =FetchType.LAZY)
	
	private List<Telefono> telefonos= new ArrayList<Telefono>();
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable
	(name = "usuarios_role",
	uniqueConstraints = @UniqueConstraint (
			columnNames = {"usuario_id","role_id"},
			name = "unique_role_user"), 
			joinColumns = @JoinColumn(
					name = "usuario_id",
					referencedColumnName = "id",
					table = "usuario",
					unique = false,
					foreignKey = @ForeignKey(
							name = "usuario_fk",
							value = ConstraintMode.CONSTRAINT)), 
	
			inverseJoinColumns = @JoinColumn (
					name = "role_id",
					referencedColumnName = "id",
					table = "role",
					unique = false,
					updatable = false,
					foreignKey = @ForeignKey (
							name="role_fk",
							value = ConstraintMode.CONSTRAINT)))
	private List<Role> roles = new ArrayList<Role>(); /*Los papeles de cada usuario o acesso*/
					
	
	public List<Telefono> getTelefonos() {
		return telefonos;
	}
	
	
	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
	

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String gePassword() {
		return password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	/*Autorizaciones acceso de los usuarios ROL_ADMIN, ROL_VISIT*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return roles;
	}
	
	//@JsonIgnore
	@Override
	public String getPassword() {
		
		return this.password;
	}
	
	@JsonIgnore
	@Override
	public String getUsername() {
		
		return this.login;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	

}