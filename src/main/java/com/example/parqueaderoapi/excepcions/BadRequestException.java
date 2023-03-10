package com.example.parqueaderoapi.excepcions;

import com.example.parqueaderoapi.responses.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {

    private ErrorResponse errorResponse;
}
