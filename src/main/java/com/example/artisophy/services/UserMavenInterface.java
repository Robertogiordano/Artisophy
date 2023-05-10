package com.example.artisophy.services;

import com.example.artisophy.dao.respuestas.museum.RespuestaMuseumImpl;
import com.example.artisophy.dao.respuestas.user.RespuestaUserImpl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserMavenInterface {

    @POST("CheckCredentials")
    Call<RespuestaUserImpl> checkCredentials(@Query("username") String username, @Query("password") String password);

    @POST("RegisterUser")
    Call<RespuestaUserImpl> registerUser(@Query("name") String name,
                                         @Query("surname") String surname,
                                         @Query("username") String username,
                                         @Query("email") String email,
                                         @Query("password") String password);

    @POST("ModifyUser")
    Call<RespuestaUserImpl> modifyUser(@Query("nameOld") String nameOld,
                                       @Query("surnamesOld") String surnameOld,
                                       @Query("usernameOld") String usernameOld,
                                       @Query("emailOld") String emailOld,
                                       @Query("passwordOld") String passwordOld,
                                         @Query("nameNew") String nameNew,
                                         @Query("surnamesNew") String surnameNew,
                                         @Query("usernameNew") String usernameNew,
                                         @Query("emailNew") String emailNew,
                                         @Query("passwordNew") String passwordNew);
}
