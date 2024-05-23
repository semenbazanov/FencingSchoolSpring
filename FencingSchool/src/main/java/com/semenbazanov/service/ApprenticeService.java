package com.semenbazanov.service;

import com.semenbazanov.model.Apprentice;

import java.util.List;

public interface ApprenticeService {
    void add(Apprentice apprentice);

    List<Apprentice> get();

    Apprentice get(long id);

    Apprentice update(Apprentice apprentice);

    Apprentice delete(long id);
}
