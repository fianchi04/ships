package com.shipping.ships.service.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = Ship.Builder.class)
public class Ship {
    @JsonProperty(value = "id")
    private final String id;
    @JsonProperty(value = "built")
    private final String built;
    @JsonProperty(value = "name")
    private final String name;
    @JsonProperty(value = "lengthMeters")
    private final double lengthMeters;
    @JsonProperty(value = "beamMeters")
    private final double beamMeters;
    @JsonProperty(value = "maxTEU")
    private final int maxTEU;
    @JsonProperty(value = "owner")
    private final String owner;
    @JsonProperty(value = "grossTonnage")
    private final String grossTonnage;

    public Ship(String id, String built, String name, double lengthMeters, double beamMeters, int maxTEU, String owner, String grossTonnage) {
        this.id = id;
        this.built = built;
        this.name = name;
        this.lengthMeters = lengthMeters;
        this.beamMeters = beamMeters;
        this.maxTEU = maxTEU;
        this.owner = owner;
        this.grossTonnage = grossTonnage;
    }


    @JsonCreator
    private Ship(Builder builder) {
        this.id = builder.id;
        this.built = builder.built;
        this.name = builder.name;
        this.lengthMeters = builder.lengthMeters;
        this.beamMeters = builder.beamMeters;
        this.maxTEU = builder.maxTEU;
        this.owner = builder.owner;
        this.grossTonnage = builder.grossTonnage;
    }

    public static Builder builder() { return new Builder();}

    @JsonPOJOBuilder
    public static class Builder {
        private String id;
        private String built;
        private String name;
        private double lengthMeters;
        private double beamMeters;
        private int maxTEU;
        private String owner;
        private String grossTonnage;


        @JsonProperty(value = "id")
        public Builder withId (String id) {
            this.id = id;
            return this;
        }

        @JsonProperty(value = "built")
        public Builder withBuilt (String built ) {
            this.built =built;
            return this;
        }
        @JsonProperty(value = "name")
        public Builder withName (String name) {
            this.name =name;
            return this;
        }
        @JsonProperty(value = "lengthMeters")
        public Builder withLengthMeters (double lengthMeters ) {
            this.lengthMeters = lengthMeters;
            return this;
        }
        @JsonProperty(value = "beamMeters")
        public Builder witBeamMeters (double beamMeters) {
            this.beamMeters = beamMeters;
            return this;
        }
        @JsonProperty(value = "maxTEU")
        public Builder withMaxTEU (int maxTEU ) {
            this.maxTEU = maxTEU;
            return this;
        }
        @JsonProperty(value = "owner")
        public Builder withOwner (String owner ) {
            this.owner = owner;
            return this;
        }
        @JsonProperty(value = "grossTonnage")
        public Builder withGrossTonnage (String grossTonnage ) {
            this.grossTonnage = grossTonnage;
            return this;
        }

        public Ship build() { return new Ship(this);}

    }

    @Override
    public String toString() {
        return "Ship{" +
                "id='" + id + '\'' +
                ", built='" + built + '\'' +
                ", name='" + name + '\'' +
                ", lengthMeters=" + lengthMeters +
                ", beamMeters=" + beamMeters +
                ", maxTEU=" + maxTEU +
                ", owner='" + owner + '\'' +
                ", grossTonnage='" + grossTonnage + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getBuilt() {
        return built;
    }

    public String getName() {
        return name;
    }

    public double getLengthMeters() {
        return lengthMeters;
    }

    public double getBeamMeters() {
        return beamMeters;
    }

    public int getMaxTEU() {
        return maxTEU;
    }

    public String getOwner() {
        return owner;
    }

    public String getGrossTonnage() {
        return grossTonnage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Double.compare(ship.lengthMeters, lengthMeters) == 0 &&
                Double.compare(ship.beamMeters, beamMeters) == 0 &&
                maxTEU == ship.maxTEU &&
                grossTonnage == ship.grossTonnage &&
                id.equals(ship.id) &&
                built.equals(ship.built) &&
                name.equals(ship.name) &&
                owner.equals(ship.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, built, name, lengthMeters, beamMeters, maxTEU, owner, grossTonnage);
    }
}
