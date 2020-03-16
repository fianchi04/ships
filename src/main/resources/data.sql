DROP TABLE IF EXISTS ships;

CREATE TABLE ships (
    id INT PRIMARY KEY,
    built VARCHAR(50) NOT NULL,
    name VARCHAR(250) NOT NULL,
    lengthMeters DOUBLE NOT NULL,
    beamMeters DOUBLE NOT NULL,
    maxTEU INT NOT NULL,
    owner VARCHAR(250) NOT NULL,
    grossTonnage int NOT NULL
)