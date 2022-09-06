package com.lunex.LunEx1.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Type;

public interface IRepoPopulator {


    public void populateRepo(JpaRepository repository, String data_path, Type objectClass);


    }
