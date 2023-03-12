package ifs.edu.br.chatonlinebackend.model.enums;

public enum MessageType {

    CHAT(0),
    CONNECT(1),
    DISCONNECT(2);

    final int value;

    MessageType(int value) {
        this.value = value;
    }

}
