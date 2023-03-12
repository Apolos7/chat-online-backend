package ifs.edu.br.chatonlinebackend.model.dto;

import ifs.edu.br.chatonlinebackend.model.enums.MessageType;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String from;
    private MessageType type;
    private String content;
    private Date date;

}
