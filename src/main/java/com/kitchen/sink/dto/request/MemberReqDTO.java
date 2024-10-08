package com.kitchen.sink.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kitchen.sink.aspect.UniqueEmail;
import com.kitchen.sink.aspect.UniquePhoneNumber;
import com.kitchen.sink.enums.MemberStatus;
import com.kitchen.sink.validation.*;
import jakarta.validation.constraints.*;

@UniqueEmail(groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class}, message = "Email already exists in the system")
@UniquePhoneNumber(groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class}, message = "PhoneNumber already exists in the system")
public record MemberReqDTO(

        @NotNull(groups = UpdateGroup.class, message = "Id is mandatory")
        String id,

        @NotBlank(message = "Name is mandatory", groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class})
        @Size(min = 1, max = 25, message = "Name must be between 1 and 25 characters", groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class})
        @Pattern(regexp = "[^0-9]*", message = "Name must not contain numbers", groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class})
        String name,

        @NotBlank(message = "Email is mandatory", groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class})
        @Email(message = "Email should be valid", groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class})
        @JsonSerialize(using = LowerCaseStringSerializer.class)
        @JsonDeserialize(using = LowerCaseStringDeserializer.class)
        String email,

        @NotBlank(message = "Phone number is mandatory", groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class})
        @Size(min = 12, max = 12, message = "Phone number must be between 10 and 12 digits", groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class})
        @Pattern(
                regexp = "^91.*$",
                message = "Phone number must start with '91'.",
                groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class}
        )
        @Pattern(
                regexp = "^[0-9]*$",
                message = "Phone number must contain only numeric characters.",
                groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class}
        )
        @Pattern(
                regexp = "^91[0-9]{10}$",
                message = "Phone number must have exactly 10 digits after '91'.",
                groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class}
        )
        @Pattern(
                regexp = "^91(?!0000000000|1111111111|2222222222|3333333333|4444444444|5555555555)[0-9]{10}$",
                message = "Phone number cannot be all zeros, ones, twos, threes, fours, or fives after '91'.",
                groups = {CreateGroup.class, UpdateGroup.class, RegisterGroup.class}
        )
        String phoneNumber,


        @NotBlank(message = "password  is mandatory", groups = {CreateGroup.class, RegisterGroup.class})
        @Null(message ="password should be null" , groups = {UpdateGroup.class})
        @Size(min = 6, max = 20, message = "password must be between 6 and 20 characters",groups = {CreateGroup.class,RegisterGroup.class})
        String password,

        @Null(message ="memberStatus should be null" ,groups ={ RegisterGroup.class,CreateGroup.class})
        MemberStatus status
) {
}
