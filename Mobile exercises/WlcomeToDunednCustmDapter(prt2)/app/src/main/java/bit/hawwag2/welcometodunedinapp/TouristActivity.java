package bit.hawwag2.welcometodunedinapp;

import android.graphics.drawable.Drawable;

/**
 * Created by USER987 on 2/04/2017.
 */

public class TouristActivity {
    int restaurantImage;
    String restaurantName;
    String imageFileName;



    public int getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(int restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public TouristActivity(int restaurantImage, String restaurantName)
    {
        this.restaurantImage = restaurantImage;
        this.restaurantName = restaurantName;
    }


    @Override
    public String toString()
    {
        return restaurantImage + restaurantName;

    }


}

