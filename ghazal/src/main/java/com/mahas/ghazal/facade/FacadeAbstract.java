package com.mahas.ghazal.facade;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mahas.ghazal.command.Command;
import com.mahas.ghazal.command.ICommand;
import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.dao.furniture.FurnitureDAO;
import com.mahas.ghazal.dao.user.FavoriteDAO;
import com.mahas.ghazal.dao.user.ReviewDAO;
import com.mahas.ghazal.dao.user.UserDAO;
import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.TypeResponse;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.domain.user.favorite.Favorite;
import com.mahas.ghazal.domain.user.review.Review;

import jakarta.annotation.PostConstruct;

public abstract class FacadeAbstract {

    @Autowired
    private FurnitureDAO furnitureDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FavoriteDAO favoriteDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    protected final Map<String, IDAO> daos = new HashMap<>();

    @PostConstruct
    public void initDaos(){
        daos.put(Furniture.class.getName(), furnitureDAO);
        daos.put(User.class.getName(), userDAO);
        daos.put(Favorite.class.getName(), favoriteDAO);
        daos.put(Review.class.getName(), reviewDAO);
    }

    protected FacadeResponse runRules(FacadeRequest request, FacadeResponse response){
        ICommand[] commands = request.getCommands(); 
        if(commands == null || commands.length == 0) return response;
        
        for(ICommand c : commands){
            Command command = new Command();
            command.setCommand(c);
            response = command.execute(request, response);
            if(!Optional.ofNullable(response.getMessage()).orElse("").isBlank()) {
                response.setTypeResponse(TypeResponse.VARIABLE_ERROR);
                break;
            }
        }

        return response;
    }
}
