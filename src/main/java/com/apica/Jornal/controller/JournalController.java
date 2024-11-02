package com.apica.Jornal.controller;

import com.apica.Jornal.dto.reponse.JournalResponseDTO;
import com.apica.Jornal.service.JournalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JournalController {

    JournalService journalService;

    @GetMapping("/getAllJournals")
    public ResponseEntity<JournalResponseDTO> getAllUsersDetails(){
        try {
            return ResponseEntity.ok(JournalResponseDTO.builder().success(true).data(journalService.getAllJournals()).build());
        } catch(Exception e){
            return ResponseEntity.unprocessableEntity().body(JournalResponseDTO.builder().success(false).data(e.getMessage()).build());
        }
    }

    @GetMapping("/getUserJournals")
    public ResponseEntity<JournalResponseDTO> getUsersEventDetails(@RequestParam String email){
        try {
            return ResponseEntity.ok(JournalResponseDTO.builder().success(true).data(journalService.getJournals(email)).build());
        } catch(Exception e){
            return ResponseEntity.unprocessableEntity().body(JournalResponseDTO.builder().success(false).data(e.getMessage()).build());
        }
    }
}
