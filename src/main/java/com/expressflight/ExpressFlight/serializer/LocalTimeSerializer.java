package com.expressflight.ExpressFlight.serializer;

import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LocalTimeSerializer implements JsonSerializer<LocalTime> {

    private DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");



    @Override
    public JsonElement serialize(LocalTime localTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(defaultFormatter.format(localTime));
    }
}