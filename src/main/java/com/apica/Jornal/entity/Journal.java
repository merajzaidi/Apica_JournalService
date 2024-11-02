package com.apica.Jornal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity(name = "journal")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Journal {

    @Id
    @GeneratedValue
    int _id;

    String userName;

    String userEvent;

    @CreatedDate
    LocalDateTime eventDate;
}
