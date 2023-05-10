package com.example.artisophy.adapters.users;

import com.example.artisophy.dao.elements.Artwork;
import com.example.artisophy.dao.elements.ArtworkInterface;
import com.example.artisophy.dao.elements.User;
import com.example.artisophy.dao.elements.UserInterface;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class UsersTypeAdapter implements JsonSerializer<UserInterface>, JsonDeserializer<UserInterface> {
    @Override
    public UserInterface deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String surnames = jsonObject.get("surnames").getAsString();
        String username = jsonObject.get("username").getAsString();
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();


        return new User( name, surnames,username,email,password);
    }

    @Override
    public JsonElement serialize(UserInterface src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("surnames", src.getSurnames());
        jsonObject.addProperty("username", src.getUsername());
        jsonObject.addProperty("email", src.getEmail());
        jsonObject.addProperty("password", src.getPassword());

        return jsonObject;
    }
}
