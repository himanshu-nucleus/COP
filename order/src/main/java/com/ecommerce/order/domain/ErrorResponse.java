package com.ecommerce.order.domain;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /**
     * The errorCode - stores response error code.
     */
    private Integer errorCode;

    /**
     * The errorMessages - stores list of error messages.
     */
    private List<String> errorMessages;

    /**
     * The hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorMessages);
    }

    /**
     * The equals.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ErrorResponse other = (ErrorResponse) obj;
        return Objects.equals(errorCode, other.errorCode) && Objects.equals(errorMessages, other.errorMessages);
    }
}

