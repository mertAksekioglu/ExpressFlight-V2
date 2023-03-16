package com.expressflight.ExpressFlight.util;

import com.google.gson.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


@Configurable
@NoArgsConstructor
@Service
public class JPARepoPopulator implements IRepoPopulator {


    private Gson gson;

    public JPARepoPopulator(Gson gson) {
        this.gson = gson;
    }

    Object[] objects;

    @Override
    public void populateRepo(JpaRepository repository, String data_path, Type objectClass, Gson gson) {

        gson = gson;
        if(gson != null) {

            {
                try (FileReader reader = new FileReader(data_path)) {
                    objects = gson.fromJson(reader, objectClass);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            repository.saveAll(List.of(objects));
        }



    }
}
