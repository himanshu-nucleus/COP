package com.ecommerce.user.dto;

import java.util.Arrays;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginInDTO {

    /**
     * The email id of user.
     */
    private String email;

    /**
     * The user password.
     */
    private char[] password;

    @Override
    public final int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoginInDTO)) {
            return false;
        }
        LoginInDTO other = (LoginInDTO) obj;
        return Objects.equals(email, other.email) && Arrays.equals(password, other.password);
    }

}
