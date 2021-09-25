/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.reins.bookstore.websocketbot;

import com.reins.bookstore.websocketbot.decoders.MessageDecoder;
import com.reins.bookstore.websocketbot.encoders.ChatMessageEncoder;
import com.reins.bookstore.websocketbot.encoders.InfoMessageEncoder;
import com.reins.bookstore.websocketbot.encoders.JoinMessageEncoder;
import com.reins.bookstore.websocketbot.encoders.UsersMessageEncoder;
import com.reins.bookstore.websocketbot.messages.*;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


/* Websocket endpoint */

@ServerEndpoint(
        value = "/websocketbot",
        decoders = { MessageDecoder.class },
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class,
                     InfoMessageEncoder.class, UsersMessageEncoder.class }
        )
@Component
/* There is a BotEndpoint instance per connetion */
public class BotEndpoint {
    private static final Logger logger = Logger.getLogger("BotEndpoint");
    private static Queue<Session> mySession = new ConcurrentLinkedQueue<>();
    
    @OnOpen
    public void openConnection(Session session) {
        mySession.add(session);
        logger.log(Level.INFO, "Connection opened.");
    }
    
    @OnMessage
    public void message(final Session session, Message msg) {
        logger.log(Level.INFO, "Received: {0}", msg.toString());
        
        if (msg instanceof JoinMessage) {
            /* Add the new user and notify everybody */
            com.reins.bookstore.websocketbot.messages.JoinMessage jmsg = (JoinMessage) msg;
            session.getUserProperties().put("name", jmsg.getName());
            session.getUserProperties().put("active", true);
            logger.log(Level.INFO, "Received: {0}", jmsg.toString());
            sendAll(session, new com.reins.bookstore.websocketbot.messages.InfoMessage(jmsg.getName() + " has joined the chat"));
            sendAll(session, new com.reins.bookstore.websocketbot.messages.ChatMessage("Duke", jmsg.getName(), "Hi there!!"));
            sendAll(session, new com.reins.bookstore.websocketbot.messages.UsersMessage(this.getUserList(session)));
            
        } else if (msg instanceof com.reins.bookstore.websocketbot.messages.ChatMessage) {
            /* Forward the message to everybody */
            final com.reins.bookstore.websocketbot.messages.ChatMessage cmsg = (com.reins.bookstore.websocketbot.messages.ChatMessage) msg;
            logger.log(Level.INFO, "Received: {0}", cmsg.toString());
            sendAll(session, cmsg);
        }
    }
    
    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        session.getUserProperties().put("active", false);
        if (session.getUserProperties().containsKey("name")) {
            String name = session.getUserProperties().get("name").toString();
            sendAll(session, new com.reins.bookstore.websocketbot.messages.InfoMessage(name + " has left the chat"));
//            sendAll(session, new com.reins.bookstore.websocketbot.messages.UsersMessage(this.getUserList(session)));
        }
        logger.log(Level.INFO, "Connection closed.");
    }
    
    @OnError
    public void error(Session session, Throwable t) {
        logger.log(Level.INFO, "Connection error ({0})", t.toString());
    }
    
    /* Forward a message to all connected clients
     * The endpoint figures what encoder to use based on the message type */
    public synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : mySession){//session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }   
    }
    
    /* Returns the list of users from the properties of all open sessions */
    public List<String> getUserList(Session session) {
        List<String> users = new ArrayList<>();
        //users.add("Duke");
        for (Session s : mySession){//session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }
}
