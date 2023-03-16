package com.expressflight.ExpressFlight.util;

import com.google.gson.Gson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Type;

public interface IRepoPopulator {


    public void populateRepo(JpaRepository repository, String data_path, Type objectClass, Gson gson);


}
