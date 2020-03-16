package com.shipping.ships.repository.domain;

import com.shipping.ships.service.domain.Ship;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShipEntity {

    @Id
    private int id;

    private String built;
    private String name;
    private Double lengthMeters;
    private Double beamMeters;
    private int maxTEU;
    private String owner;
    private String grossTonnage;

    protected ShipEntity() {}

    public ShipEntity(int id, String built, String name,
                      Double lengthMeters, Double beamMeters,
                      int maxTEU, String owner, String grossTonnage) {
        this.id = id;
        this.built = built;
        this.name = name;
        this.lengthMeters = lengthMeters;
        this.beamMeters = beamMeters;
        this.maxTEU = maxTEU;
        this.owner = owner;
        this.grossTonnage = grossTonnage;
    }

    @Override
    public String toString() {
        return "ShipEntity{" +
                "id=" + id +
                ", built='" + built + '\'' +
                ", name='" + name + '\'' +
                ", lengthMeters=" + lengthMeters +
                ", beamMeters=" + beamMeters +
                ", maxTEU=" + maxTEU +
                ", owner='" + owner + '\'' +
                ", grossTonnage='" + grossTonnage + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getBuilt() {
        return built;
    }

    public String getName() {
        return name;
    }

    public Double getLengthMeters() {
        return lengthMeters;
    }

    public Double getBeamMeters() {
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
}
