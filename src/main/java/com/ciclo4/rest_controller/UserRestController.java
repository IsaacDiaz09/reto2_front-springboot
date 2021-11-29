package com.ciclo4.rest_controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ciclo4.model.User;
import com.ciclo4.model.dto.UserDTO;
import com.ciclo4.model.request.NewUserRequest;
import com.ciclo4.service.UserServiceImpl;

/**
 * @author CarlinGebyte
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api/user")
public class UserRestController {
	/**
	 * Atributo Service
	 */
	private final UserServiceImpl userServiceImpl;

	/**
	 * Método constructor
	 *
	 * @param userServiceImpl
	 */
	public UserRestController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	/**
	 * Método para obtener todos los usuarios
	 *
	 * @return
	 */
	@GetMapping("/all")
	public List<UserDTO> getAll() {
		return userServiceImpl.getAll();
	}

	/**
	 * Método para crear un usuario
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO newUser(@RequestBody NewUserRequest request) {
		return userServiceImpl.newUser(request);
	}

	/**
	 * Método para verificar si existe un usuario con el Email ingresado
	 *
	 * @param email
	 * @return
	 */
	@GetMapping("/{correoElectronico}")
	public boolean byEmail(@PathVariable("correoElectronico") String email) {
		return userServiceImpl.verifyEmail(email);
	}

	/**
	 * Método para verificar si existe un usuario, Email y Contraseña
	 *
	 * @param email
	 * @param pass
	 * @return
	 */
	@GetMapping("/{email}/{pass}")
	public UserDTO byEmailPass(@PathVariable("email") String email, @PathVariable("pass") String pass) {
		return userServiceImpl.byEmailPass(email, pass);
	}

	/**
	 * Método para actualizar un usuario
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody NewUserRequest request) {
		return userServiceImpl.editUser(request);
	}

	/**
	 * Método para eliminar un usuario
	 * 
	 * @param idUser
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer idUser) {
		userServiceImpl.deleteUser(idUser);
	}
}
