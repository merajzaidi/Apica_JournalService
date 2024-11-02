package com.apica.Jornal.service;

import com.apica.Jornal.dao.JournalDAO;
import com.apica.Jornal.dto.reponse.AuthenticationResponseDTO;
import com.apica.Jornal.dto.reponse.JournalMapperDTO;
import com.apica.Jornal.entity.Journal;
import com.apica.Jornal.exception.UnAuthorizedException;
import com.apica.UserEventData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class JournalServiceImpl implements JournalService{
    RestTemplate restTemplate;

    JournalDAO journalDAO;
    @Override
    public List<JournalMapperDTO> getAllJournals() {
        try {
            if (isAdminUser()) {
                return journalDAO.findAll(Sort.by(Sort.Direction.DESC, "eventDate")).stream().map(journal -> JournalMapperDTO.builder().eventDate(journal.getEventDate()).username(journal.getUserName()).event(journal.getUserEvent()).build()).collect(Collectors.toList());
            }
            throw new AuthenticationException("Request Un Authorized");
        } catch (AuthenticationException e) {
            throw new UnAuthorizedException(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }



    @Override
    public List<JournalMapperDTO> getJournals(String email) {
        try {
            if (isAdminUser()) {
                return journalDAO.findByUserNameOrderByEventDateDesc(email).stream().map(journal -> JournalMapperDTO.builder().eventDate(journal.getEventDate()).username(journal.getUserName()).event(journal.getUserEvent()).build()).collect(Collectors.toList());
            }
            throw new AuthenticationException("Request Un Authorized");
        } catch (AuthenticationException e) {
            throw new UnAuthorizedException(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void createUserEvent(UserEventData data) {
        journalDAO.saveAndFlush(Journal.builder().userEvent(data.getEvent()).userName(data.getUser()).build());
    }

    private boolean isAdminUser() {
        String token = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getHeader("Authorization");
        if(token == null){
            throw new RuntimeException("Authentication required");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<AuthenticationResponseDTO> response = restTemplate.exchange("http://localhost:8080" + "/isAdmin", HttpMethod.GET, entity, AuthenticationResponseDTO.class);

        return response.getStatusCode().isSameCodeAs(HttpStatus.OK) && Objects.requireNonNull(response.getBody()).isData();
    }
}
