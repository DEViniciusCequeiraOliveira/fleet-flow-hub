package com.vinicius.fleet_service.application.utility;

public interface Mapper {
    <T> T convert(Object object, Class<T> destinationType);
}