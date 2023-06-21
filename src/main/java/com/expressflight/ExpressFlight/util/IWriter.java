package com.expressflight.ExpressFlight.util;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IWriter {

    public void write(JpaRepository repository, String path);

    public void write(JpaRepository repository, String path, Boolean write);

}
