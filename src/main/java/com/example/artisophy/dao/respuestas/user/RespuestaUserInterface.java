package com.example.artisophy.dao.respuestas.user;

import com.example.artisophy.dao.elements.Museum;
import com.example.artisophy.dao.elements.User;

import java.util.List;

public interface RespuestaUserInterface {
    List<User> getResult();

    void setResult(List<User> result);

    String getCode();

    void setCode(String code);

    String getMessage();

    void setMessage(String message);
}
