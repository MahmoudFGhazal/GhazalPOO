package com.mahas.ghazal.domain.user.review;

import java.io.Serializable;
import java.util.Objects;

import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.User;

public class ReviewId implements Serializable {
    
    private User user;
    private Furniture furniture;

    public ReviewId(){}

    public ReviewId(User user, Furniture furniture){
        this.user = user;
        this.furniture = furniture;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ReviewId)) return false;

        ReviewId r = (ReviewId) o;
        
        return Objects.equals(user, r.user) && 
                Objects.equals(furniture, r.furniture);
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
