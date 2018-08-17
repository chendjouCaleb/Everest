package org.everest.mvc.binding;


import javax.validation.constraints.*;

public class Luminary {
    @Min(10)
    private int distance;

    @NotNull
    @Size(min = 3, max = 12)
    private String name;

    @AssertTrue(message = "temps à perdre")
    boolean isValid(){
        return distance == name.length();
    }

    @AssertTrue
    boolean isLuminary(){
        return name == "x";
    }

    @Override
    public String toString() {
        return "Luminary{" +
                "distance=" + distance +
                ", name='" + name + '\'' +
                '}';
    }



    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
