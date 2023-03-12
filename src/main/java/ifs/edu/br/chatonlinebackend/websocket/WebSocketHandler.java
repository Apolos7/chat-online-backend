package ifs.edu.br.chatonlinebackend.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessionList = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);

       /* ObjectMapper objectMapper = new ObjectMapper();
        for (WebSocketSession webSocketSession: sessionList) {
            if (webSocketSession.isOpen()) {

            }
        }*/
        logger.info("A new connection was made. ID: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        logger.info("A connection was closed. ID: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        for (WebSocketSession ws: sessionList) {
            ws.sendMessage(message);
        }
    }

}
