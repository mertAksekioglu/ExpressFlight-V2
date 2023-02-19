package com.lunex.LunEx1.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    private DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(),
                defaultFormatter.withLocale(Locale.ENGLISH));
    }
}