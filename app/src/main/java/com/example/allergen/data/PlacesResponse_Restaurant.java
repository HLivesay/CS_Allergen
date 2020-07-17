package com.example.allergen.data;

import java.util.ArrayList;

public class PlacesResponse_Restaurant {
    Geometry GeometryObject;
    private String icon;
    private String id;
    private String name;
    Opening_hours Opening_hoursObject;
    ArrayList< Object > photos = new ArrayList < Object > ();
    private String place_id;
    private String reference;
    ArrayList < Object > types = new ArrayList < Object > ();
    private String vicinity;
    // Getter Methods

    public Geometry getGeometry() {
        return GeometryObject;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Opening_hours getOpening_hours() {
        return Opening_hoursObject;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getReference() {
        return reference;
    }

    public String getVicinity() {
        return vicinity;
    }

    // Setter Methods

    public void setGeometry(Geometry geometryObject) {
        this.GeometryObject = geometryObject;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpening_hours(Opening_hours opening_hoursObject) {
        this.Opening_hoursObject = opening_hoursObject;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}

