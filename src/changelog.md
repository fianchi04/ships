# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased] - 2020-15-03
### Added 
- Initialised Spring Boot app with JPA, H2, Web & Actuator dependencies
- Ship class with builder pattern and inbuilt JSON serialisation/deserialisation
- Added initialiseShipInventory() which parses a JSON file at application start up to populate a list of Ships, dependencies: googlecode + google.simple.JSON libraries
- basic @RestController to return information on Ships
- Test to check connection to RestController + basic serialisation/deserialisation of JSON objects
- H2 repository and SPRING JPA interface for saving/retrieving ship info
### Fixed

### Changed

### Removed

[Unreleased]: https://github.com/fianchi04/ships
