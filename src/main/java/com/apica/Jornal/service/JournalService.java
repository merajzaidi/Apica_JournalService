package com.apica.Jornal.service;

import com.apica.Jornal.dto.reponse.JournalMapperDTO;
import com.apica.UserEventData;

import java.util.List;

public interface JournalService {
    List<JournalMapperDTO> getAllJournals();

    List<JournalMapperDTO> getJournals(String email);

    void createUserEvent(UserEventData data);
}
