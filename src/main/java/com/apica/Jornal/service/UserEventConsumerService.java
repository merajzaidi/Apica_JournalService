package com.apica.Jornal.service;

import com.apica.UserEventData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserEventConsumerService {

    JournalService journalService;

    @KafkaListener(topics = "user-events", groupId = "user-group")
    public void consume(UserEventData data){
        System.out.println("Data Entered");
        journalService.createUserEvent(data);
    }
}
