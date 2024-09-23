package com.virtualdressingroom.payment.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}
