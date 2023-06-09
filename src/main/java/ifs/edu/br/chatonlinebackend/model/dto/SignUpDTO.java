package ifs.edu.br.chatonlinebackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

}
