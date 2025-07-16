package com.mahas.ghazal.domain.user.favorite;

import java.io.Serializable;
import java.util.Objects;

import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.User;

public class FavoriteId implements Serializable {
    
    private User user;
    private Furniture furniture;

    public FavoriteId() {}

    public FavoriteId(User user, Furniture furniture){
        this.user = user;
        this.furniture = furniture;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof FavoriteId)) return false;

        FavoriteId f = (FavoriteId) o;
        
        return Objects.equals(user, f.user) && 
                Objects.equals(furniture, f.furniture);
    }

    @Override
    public int hashCode(){
        return Objects.hash(user, furniture);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}
