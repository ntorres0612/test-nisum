package com.example.test.controller;

import com.example.test.constant.Constant;
import com.example.test.dto.CustomResponse;
import com.example.test.dto.PhoneResponse;
import com.example.test.dto.UserRequest;
import com.example.test.dto.UserResponse;
import com.example.test.exception.ControllerApplicationException;
import com.example.test.mapper.MapStructMapper;
import com.example.test.model.Configuracion;
import com.example.test.model.Phone;
import com.example.test.model.User;
import com.example.test.service.IConfiguracionService;
import com.example.test.service.IPhoneService;
import com.example.test.service.IUserService;
import com.example.test.service.impl.ConfiguracionServiceImpl;
import com.example.test.utils.JwtTokenUtil;
import com.example.test.utils.UtilsFn;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger LOGGER = LoggerFactory.getLogger(PhoneController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IPhoneService phoneService;

	@Autowired
	private MapStructMapper mapstructMapper;

	@Autowired
	private IConfiguracionService configurationService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@ApiOperation(value = "List all users", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, responseContainer = "List", message = "OK", response = UserResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
	})
	@GetMapping
	public ResponseEntity<?> listAll() {
		try {
			return ResponseEntity.ok(mapstructMapper.usersToUsersResponse(userService.findAll()));
		} catch (Exception e) {
			new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
			return ResponseEntity.internalServerError().body(new CustomResponse("Error", "An error occurred"));
		}
	}


	@ApiOperation(value = "Get user by id", notes = "User id must exist")
	@ApiResponses(value = {
			@ApiResponse(code = 200,  message = "OK", response = UserResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> listById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(mapstructMapper.userToUserResponse(userService.findById(id)));
		}catch (Exception e) {
			new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
			return ResponseEntity.internalServerError().body(new CustomResponse("Error", "An error occurred"));
		}
	}


	@ApiOperation(value = "Update user", notes = "User must exist<br /> " +
			"Password must contain at least one digit [0-9]. <br />\n" +
			"Password must contain at least one lowercase Latin character [a-z]. <br />\n" +
			"Password must contain at least one uppercase Latin character [A-Z]. <br />\n" +
			"Password must contain at least one special character like ! @ # & ( ). <br />\n" +
			"Password must contain a length of at least 8 characters and a maximum of 20 characters.<br />")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = UserResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
	})
	@PutMapping
	public ResponseEntity<?> update( @RequestBody UserRequest userRequest) {
		try {
			String strPatter = Constant.emailPatter;
			Configuracion configuracion = configurationService.findByKey("pattern-email");
			if( configuracion != null) {
				strPatter = configuracion.getValor();
			}
			if( !UtilsFn.getInstance().validateEmail(userRequest.getEmail(), strPatter)) {
				return  ResponseEntity.ok(new CustomResponse("error", "Invalid email"));
			}
			if( !UtilsFn.getInstance().validatePassword(userRequest.getPassword())) {
				return  ResponseEntity.ok(new CustomResponse("error", "Password must contain at least one digit [0-9]. <br />\n" +
						"Password must contain at least one lowercase Latin character [a-z]. <br />\n" +
						"Password must contain at least one uppercase Latin character [A-Z]. <br />\n" +
						"Password must contain at least one special character like ! @ # & ( ). <br />\n" +
						"Password must contain a length of at least 8 characters and a maximum of 20 characters.<br />"));

			}
			User userToSave = mapstructMapper.userRequestToUser(userRequest);

			return ResponseEntity.ok(mapstructMapper.userToUserResponse(userService.save(userToSave)));

		} catch (Exception e) {
			new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
			return ResponseEntity.internalServerError().body(new CustomResponse("Error", "An error occurred"));
		}
	}


	@ApiOperation(value = "Create user", notes = "Password must contain at least one digit [0-9]. <br />\n" +
			"Password must contain at least one lowercase Latin character [a-z]. <br />\n" +
			"Password must contain at least one uppercase Latin character [A-Z]. <br />\n" +
			"Password must contain at least one special character like ! @ # & ( ). <br />\n" +
			"Password must contain a length of at least 8 characters and a maximum of 20 characters.<br />")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = UserResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
	})
	@PostMapping
	public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
		try {
			String strPatter = Constant.emailPatter;
			Configuracion configuracion = configurationService.findByKey("pattern-email");
			if( configuracion != null) {
				strPatter = configuracion.getValor();
			}
			if( !UtilsFn.getInstance().validateEmail(userRequest.getEmail(), strPatter)) {
				return  ResponseEntity.ok(new CustomResponse("error", "Invalid email"));
			}
			if( !UtilsFn.getInstance().validatePassword(userRequest.getPassword())) {
				return  ResponseEntity.ok(new CustomResponse("error", "Password must contain at least one digit [0-9]. <br />\n" +
						"Password must contain at least one lowercase Latin character [a-z]. <br />\n" +
						"Password must contain at least one uppercase Latin character [A-Z]. <br />\n" +
						"Password must contain at least one special character like ! @ # & ( ). <br />\n" +
						"Password must contain a length of at least 8 characters and a maximum of 20 characters.<br />"));

			}
			User foundUser = userService.findByEmail(userRequest.getEmail());

			if( foundUser != null) {
				return  ResponseEntity.ok(new CustomResponse("User found", "The email already exists"));
			}
			User userToSave = mapstructMapper.userRequestToUser(userRequest);
			userToSave.setCreated(new Date());
			userToSave.setModified(new Date());
			userToSave.setLast_login(new Date());
																																													userToSave.setToken( jwtTokenUtil.generateToken(userToSave));
			User userSaved = userService.save(userToSave);

			for (Phone phone : userSaved.getPhones()) {
				phone.setUser(userSaved);
				phoneService.saveOrUpdate(phone);
			}
			return ResponseEntity.ok(mapstructMapper.userToUserResponse(userSaved));

		}catch (Exception e) {
			new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
			return  ResponseEntity.status(200).body(new CustomResponse("Error", "An error occurred"));
		}
	}

	@ApiOperation(value = "Delete user", notes = "User id must exist")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = CustomResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		try {
			userService.deleteById(id);
			return ResponseEntity.ok( new CustomResponse("Success", "User " + id + " deleted"));
		} catch (Exception e) {
			new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
			return  ResponseEntity.status(200).body(new CustomResponse("Error", "An error occurred"));
		}
	}
}
