package com.springapp.mvc.service;

import com.springapp.mvc.dao.SearchCriteria;
import com.springapp.mvc.entity.Game;
import com.springapp.mvc.entity.Message;
import com.springapp.mvc.service.converter.GameConverter;
import com.springapp.mvc.service.enums.MessageType;
import com.springapp.mvc.service.form.GameForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o.lutsevich on 25.1.16.
 */
@Service("gameServiceImpl")
public class GameServiceImpl extends BaseServiceImpl<Game, String> implements GameService {
    private static final String WHITE_USER = "member1";
    private static final String BLACK_USER = "member2";

    @Autowired
    private GameConverter converter;

    public Game toEntity(GameForm form) {
        return converter.toEntity(form);
    }

    public GameForm toForm(Game game) {
        return converter.toForm(game);
    }

    public List<Message> getStrokes(Game game) {
        List<Message> result = new ArrayList<Message>();
        for (Message message : game.getMessageList()) {
            if (message.getType().equals(MessageType.STROKE.getType())) {
                result.add(message);
            }
        }
        return result;
    }

    public List<Game> byUsername(String username) {
        List<Game> result = new ArrayList<Game>();
        result.addAll(super.findAllByCriteria(new SearchCriteria(WHITE_USER, username)));
        result.addAll(super.findAllByCriteria(new SearchCriteria(BLACK_USER, username)));
        return result;
    }
}
