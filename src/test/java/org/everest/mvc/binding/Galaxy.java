package org.everest.mvc.binding;

import org.joda.time.DateTime;

import java.util.Arrays;

public class Galaxy extends Luminary{
    private int id;
    private DateTime discoveryDate;
    private String[] stars;


    @Override
    public String toString() {

        return super.toString() + "\nGalaxy{" +
                "id=" + id +
                "discoveryDate=" + discoveryDate +
                ", stars=" + Arrays.toString(stars) +
                '}';
    }

    public DateTime getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(DateTime discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public String[] getStars() {
        return stars;
    }

    public void setStars(String[] stars) {
        this.stars = stars;
    }
}
