package com.example.test.mapper;

import com.example.test.dto.*;
import com.example.test.model.Configuracion;
import com.example.test.model.Phone;
import com.example.test.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
   componentModel = "spring"
)
public interface MapStructMapper {

    Configuracion configRequestToConfig(ConfigRequest configRequest);
    UserRequest userToUserResquest(User user);

    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest userRequest);

    List<UserRequest> usersToUsersRequest(List<User> users);


    List<UserResponse> usersToUsersResponse(List<User> users);

    List<User> usersRequestToUsers(List<UserRequest> usersRequest);

    PhoneRequest phoneToPhoneRequest(Phone phone);

    Phone phoneRequestToPhone(PhoneRequest phoneDto);

    List<PhoneRequest> phonesToPhonesRequest(List<Phone> phones);

    List<Phone> phonesRequestToPhones(List<PhoneRequest> phonesDto);


    PhoneResponse phoneToPhoneResponse(Phone phone);

    List<PhoneResponse> phonesToPhonesResponse(List<Phone> phones);

}