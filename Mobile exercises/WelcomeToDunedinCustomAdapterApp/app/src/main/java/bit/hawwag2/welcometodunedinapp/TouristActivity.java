package bit.hawwag2.welcometodunedinapp;

import android.graphics.drawable.Drawable;

/**
 * Created by USER987 on 2/04/2017.
 */

public class TouristActivity {
    Drawable restaurantImage;
    String restaurantName;

    public TouristActivity(Drawable restaurantImage, String restaurantName)
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
