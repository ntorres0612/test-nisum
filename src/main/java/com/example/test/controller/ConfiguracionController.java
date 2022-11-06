package com.example.test.controller;

import com.example.test.dto.ConfigRequest;
import com.example.test.dto.CustomResponse;
import com.example.test.exception.ControllerApplicationException;
import com.example.test.mapper.MapStructMapper;
import com.example.test.model.Configuracion;
import com.example.test.service.IConfiguracionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/configuracion")
public class ConfiguracionController {
	public static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionController.class);

	@Autowired
	private IConfiguracionService valueService;

	@Autowired
	private MapStructMapper mapstructMapper;

	@ApiOperation(value = "Set email pattern", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = CustomResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
	})
	@RequestMapping(value = "/patterEmail", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody ConfigRequest configRequest) {
		try {

			configRequest.setKey("pattern-email");
			Configuracion configuracion = mapstructMapper.configRequestToConfig(configRequest);
			configuracion.setId(1);
			configuracion = valueService.saveOrUpdate(configuracion);

			if( configuracion != null ) {

				return ResponseEntity.ok(configuracion);
			} else {
				return ResponseEntity.ok(new CustomResponse("error", "Error"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
			return ResponseEntity.internalServerError().body(new CustomResponse("Error", "An error occurred"));
		}
	}

}
