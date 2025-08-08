package com.oasb.literatura.API.Service;

public interface IConvierteDatos {
    <T> T obtenerDatos (String json, Class <T> clase);
}
