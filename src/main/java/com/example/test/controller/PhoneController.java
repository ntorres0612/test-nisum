package com.example.test.controller;

import com.example.test.dto.PhoneRequest;
import com.example.test.dto.PhoneResponse;
import com.example.test.dto.CustomResponse;
import com.example.test.dto.UserRequest;
import com.example.test.exception.ControllerApplicationException;
import com.example.test.mapper.MapStructMapper;
import com.example.test.model.Phone;
import com.example.test.model.User;
import com.example.test.service.IPhoneService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    public static final Logger LOGGER = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private IPhoneService phoneService;

    @Autowired
    private MapStructMapper mapstructMapper;

    @ApiOperation(value = "List all phones", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, responseContainer = "List", message = "OK", response = PhoneResponse.class),
    })
    @GetMapping
    public ResponseEntity<?> listAll() {
        try {
            return ResponseEntity.ok(phoneService.findAll());
        }catch (Exception e) {
             new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
             return ResponseEntity.ok(new CustomResponse("Error", "An error occurred"));
        }
    }


    @ApiOperation(value = "Get phone by id", notes = "Phone id must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200,  message = "OK", response = PhoneResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> listById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(mapstructMapper.phoneToPhoneResponse(phoneService.findById(id)));
        } catch (Exception e) {
            new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
            return ResponseEntity.internalServerError().body(new CustomResponse("Error", "An error occurred"));
        }
    }


    @ApiOperation(value = "Update phone", notes = "Phone id must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PhoneResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PhoneRequest phoneRequest) {
        try {
            Phone phoneUpdate = phoneService.saveOrUpdate( mapstructMapper.phoneRequestToPhone(phoneRequest));
            return ResponseEntity.ok(mapstructMapper.phoneToPhoneResponse(phoneUpdate));
        } catch (Exception e) {
            new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
            return ResponseEntity.internalServerError().body(new CustomResponse("Error", "An error occurred"));
        }
    }

    @ApiOperation(value = "Create phone", notes = "User id must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PhoneResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = CustomResponse.class),
    })
    @PostMapping
    public ResponseEntity<?> register(@RequestBody PhoneRequest phoneDto) {
        try {
            Phone phone = mapstructMapper.phoneRequestToPhone(phoneDto);
            User user = new User();
            user.setId(phoneDto.getUser_id());
            phone.setUser( user );
            Phone phoneSave = phoneService.saveOrUpdate( phone );
            return ResponseEntity.ok(mapstructMapper.phoneToPhoneResponse(phoneSave));

        } catch (Exception e) {
            new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
            return  ResponseEntity.ok(new CustomResponse("Error", "An error occurred"));
        }
    }

    @ApiOperation(value = "Delete phone", notes = "Phone id must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomResponse.class),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        try {
            phoneService.deleteById(id);
            return ResponseEntity.ok( new CustomResponse("Success", "Phone " + id + " deleted"));
        } catch (Exception e) {
            new ControllerApplicationException(LOGGER, e.getMessage(), Response.Status.BAD_REQUEST);
            return  ResponseEntity.ok(new CustomResponse("Error", "An error occurred"));
        }

    }
}
