package com.apica.Jornal.dto.reponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponseDTO {
    String status;
    boolean data;
}
