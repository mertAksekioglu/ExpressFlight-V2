package com.lunex.LunEx1.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(defaultFormatter.format(localDateTime));
    }

    public JsonElement serialize(LocalDateTime localDateTime, Type srcType,
                                 JsonSerializationContext context, DateTimeFormatter formatter) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}