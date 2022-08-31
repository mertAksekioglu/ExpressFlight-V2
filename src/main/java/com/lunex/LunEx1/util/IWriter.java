package com.lunex.LunEx1.util;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IWriter {

    public void write(JpaRepository repository, String path);
}
