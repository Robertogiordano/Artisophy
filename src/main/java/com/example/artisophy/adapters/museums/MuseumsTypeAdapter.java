package com.example.artisophy.adapters.museums;



import com.example.artisophy.dao.elements.Museum;
import com.example.artisophy.dao.elements.MuseumInterface;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MuseumsTypeAdapter implements JsonSerializer<MuseumInterface>, JsonDeserializer<MuseumInterface> {
    @Override
    public MuseumInterface deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

         Integer id=jsonObject.get("id").getAsInt();;
         String name= jsonObject.get("name").getAsString();
         String street= jsonObject.get("street").getAsString();
         String openingHour= jsonObject.get("openingHour").getAsString();
         String closingHour= jsonObject.get("closingHour").getAsString();
         String phone= jsonObject.get("phone").getAsString();
         String description= jsonObject.get("description").getAsString();
         Double price= jsonObject.get("price").getAsDouble();
         String webpageUrl= jsonObject.get("webpageUrl").getAsString();
         String wiki= jsonObject.get("wiki").getAsString();
         String googleMaps= jsonObject.get("googleMaps").getAsString();


        return new Museum(  id,  name,  street,  openingHour,  closingHour,  phone,  description,  price,  webpageUrl,  wiki,  googleMaps);
    }

    @Override
    public JsonElement serialize(MuseumInterface src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("street", src.getStreet());
        jsonObject.addProperty("openingHour", src.getOpeningHour());
        jsonObject.addProperty("closingHour", src.getClosingHour());
        jsonObject.addProperty("phone", src.getPhone());
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.addProperty("price", src.getPrice());
        jsonObject.addProperty("webpageUrl", src.getWebpageUrl());
        jsonObject.addProperty("wiki", src.getWiki());
        jsonObject.addProperty("googleMaps", src.getGoogleMaps());

        return jsonObject;
    }
}
