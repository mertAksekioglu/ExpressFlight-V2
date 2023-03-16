package com.expressflight.ExpressFlight.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {

    private DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");



    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalTime.parse(json.getAsString(),
                defaultFormatter.withLocale(Locale.ENGLISH));
    }
}