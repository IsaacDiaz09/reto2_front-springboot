package com.ciclo4.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author CarlinGebyte
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Document(collection = "usuarios")
public class User {
	/**
	 * Atributo ID
	 */
	@Id
	private Integer id;
	/**
	 * Atributo identificación
	 */
	private String identification;
	/**
	 * Atributo dirección
	 */
	private String address;
	/**
	 * Atributo teléfono
	 */
	private String cellPhone;
	/**
	 * Atributo Email
	 */
	private String email;
	/**
	 * Atributo Password
	 */
	private String password;
	/**
	 * Atributo Name
	 */
	private String name;
	/**
	 * Atributo zone
	 */
	private String zone;
	/**
	 * Atributo type
	 */
	private String type;
}
