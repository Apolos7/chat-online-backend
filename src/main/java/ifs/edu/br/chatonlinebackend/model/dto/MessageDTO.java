package ifs.edu.br.chatonlinebackend.model.dto;

import ifs.edu.br.chatonlinebackend.model.enums.MessageType;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class MessageDTO {

    String from;
    MessageType type;
    String content;
    Instant date;

}
