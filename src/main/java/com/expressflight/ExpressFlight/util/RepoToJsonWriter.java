package com.expressflight.ExpressFlight.util;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;


@NoArgsConstructor
@Configurable
@Service
public class RepoToJsonWriter implements IWriter {

    @Autowired
    private Gson gson;



    public void write(JpaRepository repository,String path) {

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(repository.findAll(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(JpaRepository repository,String path, Boolean write) {
        if(write) {
            try (FileWriter writer = new FileWriter(path)) {
                gson.toJson(repository.findAll(), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
