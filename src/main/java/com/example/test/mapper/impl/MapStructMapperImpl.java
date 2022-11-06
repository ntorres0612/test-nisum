package com.example.test.mapper.impl;

import com.example.test.dto.*;
import com.example.test.mapper.MapStructMapper;
import com.example.test.model.Configuracion;
import com.example.test.model.Phone;
import com.example.test.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-03-11T19:21:44+0100",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {
    @Override
    public Configuracion configRequestToConfig(ConfigRequest configRequest) {
        if( configRequest == null) {
            return null;
        }
        Configuracion config = new Configuracion();
        config.setLlave(configRequest.getKey());
        config.setValor(configRequest.getValue());
        return config;
    }

    @Override
    public UserRequest userToUserResquest(User user) {
        if ( user == null ) {
            return null;
        }
        UserRequest userRequest = new UserRequest();

        userRequest.setId(user.getId());
        userRequest.setName(user.getName());
        userRequest.setEmail(user.getEmail());
        userRequest.setPassword(user.getPassword());
        userRequest.setToken(user.getToken());
        userRequest.setIs_active(user.getIs_active());
        userRequest.setPhones(phonesToPhonesRequest(user.getPhones()));

        return userRequest;
    }

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }
        UserResponse userResponse = new UserResponse();

        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreated(user.getCreated());
        userResponse.setModified(user.getModified());
        userResponse.setLast_login(user.getLast_login());
        userResponse.setToken(user.getToken());
        userResponse.setIs_active(user.getIs_active());
        userResponse.setPhones(phonesToPhonesResponse(user.getPhones()));

        return userResponse;
    }

    @Override
    public User userRequestToUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }
        User user = new User();

        user.setId(userRequest.getId());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setToken(userRequest.getToken());
        user.setIs_active(userRequest.getIs_active());
        user.setPhones(phonesRequestToPhones(userRequest.getPhones()));

        return user;
    }

    @Override
    public List<UserRequest> usersToUsersRequest(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserRequest> list = new ArrayList<UserRequest>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResquest( user ) );
        }

        return list;
    }

    @Override
    public List<UserResponse> usersToUsersResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponse( user ) );
        }

        return list;
    }

    @Override
    public List<User> usersRequestToUsers(List<UserRequest> usersRequest) {
        if ( usersRequest == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( usersRequest.size() );
        for ( UserRequest userRequest : usersRequest ) {
            list.add( userRequestToUser( userRequest ) );
        }

        return list;
    }

    @Override
    public PhoneRequest phoneToPhoneRequest(Phone phone) {
        if ( phone == null ) {
            return null;
        }
        PhoneRequest phoneDto = new PhoneRequest();

        phoneDto.setId(phone.getId());
        phoneDto.setNumber(phone.getNumber());
        phoneDto.setCitycode(phone.getCitycode());
        phoneDto.setContrycode(phone.getContrycode());

        return phoneDto;
    }

    @Override
    public Phone phoneRequestToPhone(PhoneRequest phoneDto) {
        if ( phoneDto == null ) {
            return null;
        }
        Phone phone = new Phone();

        phone.setId(phoneDto.getId());
        phone.setNumber(phoneDto.getNumber());
        phone.setCitycode(phoneDto.getCitycode());
        phone.setContrycode(phoneDto.getContrycode());

        return phone;
    }

    @Override
    public List<PhoneRequest> phonesToPhonesRequest(List<Phone> phones) {
        if ( phones == null ) {
            return null;
        }

        List<PhoneRequest> list = new ArrayList<PhoneRequest>( phones.size() );
        for ( Phone phone : phones ) {
            list.add( phoneToPhoneRequest( phone ) );
        }

        return list;
    }

    @Override
    public List<Phone> phonesRequestToPhones(List<PhoneRequest> phonesDto) {
        if ( phonesDto == null ) {
            return null;
        }
        List<Phone> list = new ArrayList<Phone>( phonesDto.size() );
        for ( PhoneRequest phoneDto : phonesDto ) {
            list.add( phoneRequestToPhone( phoneDto ) );
        }

        return list;
    }

    @Override
    public PhoneResponse phoneToPhoneResponse(Phone phone) {
        if ( phone == null ) {
            return null;
        }
        PhoneResponse phoneResponse = new PhoneResponse();

        phoneResponse.setNumber(phone.getNumber());
        phoneResponse.setCitycode(phone.getCitycode());
        phoneResponse.setContrycode(phone.getContrycode());

        return phoneResponse;
    }



    @Override
    public List<PhoneResponse> phonesToPhonesResponse(List<Phone> phones) {
        if ( phones == null ) {
            return null;
        }
        List<PhoneResponse> list = new ArrayList<PhoneResponse>( phones.size() );
        for ( Phone phone : phones ) {
            list.add( phoneToPhoneResponse( phone ) );
        }

        return list;
    }


}
