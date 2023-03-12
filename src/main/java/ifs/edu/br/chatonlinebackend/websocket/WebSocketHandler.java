package ifs.edu.br.chatonlinebackend.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ifs.edu.br.chatonlinebackend.model.dto.MessageDTO;
import ifs.edu.br.chatonlinebackend.model.enums.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public final class WebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessionList = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    private final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        MessageDTO msgDTO = MessageDTO.builder()
                .from(Objects.requireNonNull(session.getPrincipal()).getName())
                .type(MessageType.CONNECT)
                .date(new Date())
                .build();
        String msgJson = gson.toJson(msgDTO, MessageDTO.class);
        TextMessage txtMessage = new TextMessage(msgJson);
        for (WebSocketSession webSocketSession : sessionList) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(txtMessage);
            }
        }
        logger.info("A new connection was made. ID: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        MessageDTO msgDTO = MessageDTO.builder()
                .from(Objects.requireNonNull(session.getPrincipal()).getName())
                .type(MessageType.DISCONNECT)
                .date(new Date())
                .build();
        String msgJson = gson.toJson(msgDTO, MessageDTO.class);
        TextMessage txtMessage = new TextMessage(msgJson);
        for (WebSocketSession webSocketSession : sessionList) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(txtMessage);
            }
        }
        logger.info("A connection was closed. ID: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        MessageDTO msgDTO = MessageDTO.builder()
                .from(Objects.requireNonNull(session.getPrincipal()).getName())
                .type(MessageType.CHAT)
                .date(new Date())
                .content(message.getPayload())
                .build();
        String msgJson = gson.toJson(msgDTO, MessageDTO.class);
        TextMessage txtMessage = new TextMessage(msgJson);
        for (WebSocketSession webSocketSession : sessionList) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(txtMessage);
            }
        }
    }

}
