package com.example.artisophy.adapters.artworks;

import com.example.artisophy.dao.elements.Artwork;
import com.example.artisophy.dao.elements.ArtworkInterface;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ArtworksTypeAdapter implements JsonSerializer<ArtworkInterface>, JsonDeserializer<ArtworkInterface> {
    @Override
    public ArtworkInterface deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        String imgPath = jsonObject.get("imgPath").getAsString();
        String name = jsonObject.get("name").getAsString();
        int year = jsonObject.get("year").getAsInt();
        int author_id = jsonObject.get("author_id").getAsInt();
        int museum_id = jsonObject.get("museum_id").getAsInt();
        String description = jsonObject.get("description").getAsString();
        String wiki = jsonObject.get("wiki").getAsString();


        return new Artwork( id,  imgPath,  name,  year,author_id,museum_id,description,wiki);
    }

    @Override
    public JsonElement serialize(ArtworkInterface src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("imgPath", src.getImgPath());
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("year", src.getYear());
        jsonObject.addProperty("author_id", src.getAuthor_id());
        jsonObject.addProperty("museum_id", src.getMuseum_id());
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.addProperty("wiki", src.getWiki());

        return jsonObject;
    }
}
