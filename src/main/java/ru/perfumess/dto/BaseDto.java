package ru.perfumess.dto;

import lombok.Builder;
import ru.perfumess.model.Status;

import java.util.Date;

public class BaseDto {

    private Long id;
    private Date created;
    private Date updated;
    private Status status;
}
