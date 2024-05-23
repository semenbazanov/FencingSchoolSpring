package com.semenbazanov.service;

import com.semenbazanov.model.Apprentice;
import com.semenbazanov.repository.ApprenticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenticeServiceImpl implements ApprenticeService {

    private ApprenticeRepository apprenticeRepository;

    @Autowired
    public void setApprenticeRepository(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    @Override
    public void add(Apprentice apprentice) {
        try {
            this.apprenticeRepository.save(apprentice);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice already added");
        }
    }

    @Override
    public List<Apprentice> get() {
        return this.apprenticeRepository.findAll();
    }

    @Override
    public Apprentice get(long id) {
        return this.apprenticeRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Apprentice doesn't exist"));
    }

    @Override
    public Apprentice update(Apprentice apprentice) {
        Apprentice oldApprentice = this.get(apprentice.getId());
        oldApprentice.setName(apprentice.getName());
        oldApprentice.setSurname(apprentice.getSurname());
        oldApprentice.setPatronymic(apprentice.getPatronymic());
        oldApprentice.setPhoneNumber(apprentice.getPhoneNumber());
        try {
            this.apprenticeRepository.save(oldApprentice);
            return oldApprentice;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice already exists");
        }
    }

    @Override
    public Apprentice delete(long id) {
        Apprentice apprentice = this.get(id);
        this.apprenticeRepository.deleteById(id);
        return apprentice;
    }
}
