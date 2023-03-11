package ifs.edu.br.chatonlinebackend.websocket;

import ifs.edu.br.chatonlinebackend.config.WebSocketConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;


public class WebSocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessionList = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        logger.info("Temos uma nova conexão");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        logger.info("Foi embora");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        // Recebe as msg
        // valida a mensagem pra ver se tá OK
        // Só então envia para os outros usuário conectados
        System.out.println(message.getPayload());
        for (WebSocketSession ws: sessionList) {
            ws.sendMessage(message);
        }

    }

}
