package com.expressflight.ExpressFlight.serializer;

import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
@NoArgsConstructor
@Setter
@Getter
public class LocalDateSerializer implements JsonSerializer<LocalDate> {
    private DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDateSerializer(DateTimeFormatter defaultFormatter) {
        this.defaultFormatter = defaultFormatter;
    }


    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(defaultFormatter.format(localDate));
    }
}