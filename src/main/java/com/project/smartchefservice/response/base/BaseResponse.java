package com.project.smartchefservice.response.base;

import com.project.smartchefservice.exception.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private BaseBody<T> body;
    private Status status;

}
