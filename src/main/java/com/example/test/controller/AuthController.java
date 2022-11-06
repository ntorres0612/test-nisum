package com.example.test.controller;

import com.example.test.dto.CustomResponse;
import com.example.test.dto.UserLoginRequest;
import com.example.test.exception.ControllerApplicationException;
import com.example.test.model.User;
import com.example.test.service.IUserService;
import com.example.test.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Date;

@RestController
@CrossOrigin
public class AuthController {
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@ApiOperation(value = "Login user", notes = "User must exist")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = CustomResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
	})
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
		try {
			User user = userService.findByEmail(userLoginRequest.getEmail());
			if( user != null ) {

				final String token = jwtTokenUtil.generateToken(user);
				user.setLast_login(new Date());
				userService.save(user);

				return ResponseEntity.ok(new CustomResponse("success", "Bearer " + token));
			} else {
				return ResponseEntity.ok(new CustomResponse("error", "User not found"));
			}

		} catch (Exception e) {
			new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
			return ResponseEntity.internalServerError().body(new CustomResponse("Error", "An error occurred"));
		}
	}

}
