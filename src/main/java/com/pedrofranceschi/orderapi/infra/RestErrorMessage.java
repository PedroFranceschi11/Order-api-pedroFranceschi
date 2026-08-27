package com.pedrofranceschi.orderapi.infra;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    private Integer status;
    private String message;


}
