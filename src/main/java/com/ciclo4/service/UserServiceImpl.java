package com.ciclo4.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ciclo4.exception.BaseCustomException;
import com.ciclo4.model.User;
import com.ciclo4.model.dto.UserDTO;
import com.ciclo4.model.request.NewUserRequest;
import com.ciclo4.repository.UserRepository;

/**
 * @author CarlinGebyte
 */
@Service
public class UserServiceImpl {

	/**
	 * Atributo Repositorio
	 */
	private final UserRepository userRepository;

	/**
	 * Constructor
	 *
	 * @param userRepository
	 */
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Método para obtener todos los usuarios
	 *
	 * @return
	 */
	public List<UserDTO> getAll() {
		return userRepository.findAll().stream()
				.map(user -> UserDTO.builder().id(user.getId()).identification(user.getIdentification())
						.address(user.getAddress()).password(user.getPassword()).cellPhone(user.getCellPhone())
						.name(user.getName()).email(user.getEmail()).type(user.getType()).zone(user.getZone()).build())
				.collect(Collectors.toList());
	}

	/**
	 * Método para crear un usuario
	 *
	 * @param user
	 * @return
	 */
	public UserDTO newUser(NewUserRequest user) {
		userRepository.findByEmail(user.getEmail()).ifPresent(e -> {
			throw new BaseCustomException("El correo ya existe", HttpStatus.BAD_REQUEST.value());
		});

		User savedUser = userRepository.save(User.builder().id(user.getId()).identification(user.getIdentification())
				.email(user.getEmail()).name(user.getName()).cellPhone(user.getCellPhone()).address(user.getAddress())
				.password(user.getPassword())// Eliminar por seguridad
				.zone(user.getZone()).type(user.getType()).build());

		return UserDTO.builder().id(savedUser.getId()).identification(savedUser.getIdentification())
				.email(savedUser.getEmail()).name(savedUser.getName()).cellPhone(savedUser.getCellPhone())
				.address(savedUser.getAddress()).password(savedUser.getPassword())// Eliminar por seguridad
				.zone(savedUser.getZone()).type(savedUser.getType()).build();
	}

	/**
	 * Método para verificar si existe un usuario con el Email ingresado
	 *
	 * @param email
	 * @return
	 */
	public boolean verifyEmail(String email) {
		List<User> users = userRepository.findAll();
		boolean flag = false;
		for (User user : users) {
			if (email.equals(user.getEmail())) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * Método para verificar si existe un usuario, Email y Contraseña
	 *
	 * @param email
	 * @param pass
	 * @return UserDTO
	 */
	public UserDTO byEmailPass(String email, String pass) {
		List<UserDTO> users = getAll();
		UserDTO notExist = UserDTO.builder().build();
		for (UserDTO user : users) {
			if (email.equals(user.getEmail()) && pass.equals(user.getPassword())) {
				return user;
			}
		}
		return notExist;
	}

	/**
	 * Método para actualizar un usuario
	 * 
	 * @param user
	 * @return User
	 */
	public User editUser(NewUserRequest user) {
		if (user.getId() != null) {
			Optional<User> exist = userRepository.findById(user.getId());
			if (exist.isPresent()) {
				if (user.getName() != null) {
					exist.get().setName(user.getName());
				}
				if (user.getIdentification() != null) {
					exist.get().setIdentification(user.getIdentification());
				}
				if (user.getEmail() != null) {
					exist.get().setEmail(user.getEmail());
				}
				if (user.getAddress() != null) {
					exist.get().setAddress(user.getAddress());
				}
				if (user.getCellPhone() != null) {
					exist.get().setCellPhone(user.getCellPhone());
				}
				if (user.getPassword() != null) {
					exist.get().setPassword(user.getPassword());
				}
				if (user.getZone() != null) {
					exist.get().setZone(user.getZone());
				}

				return userRepository.save(exist.get());
			} else {
				return new User();
			}
		} else {
			return new User();
		}
	}

	public void deleteUser(Integer idUser) {
		Optional<User> user = userRepository.findById(idUser);
		if (user.isPresent()) {
			userRepository.deleteById(idUser);
		}
	}
	
	public Map<String,String> getUserRoles(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("COORD", "Coordinador de zona");
		map.put("ASE", "Asesor comercial");
		map.put("ADM", "Administrador");
		return map;
	}
}