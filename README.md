
# Simple RESTful API Application WeatherMeter

Useful API to interact weather sensor, which set in some place and generated measurements by this sensor.

#### _Attention! This app uses random generated values for measurements._

## Weather Meter contains two divisions:

- Service part (Java) 
- Client part (Java or Python)

## API Reference (Service part)

#### **Get** all sensors
```
  GET /sensors
```
#### **Create** a new sensor
```
  POST /sensors/registration
```
#### _Usage/Example of creating a new sensor_
```
{
    "name": "sensor123"
}
```
#### **Get** all measurements
```
  GET /measurements
```
#### **Create** a new measurement
```
  POST /measurements/add
```
#### _Usage/Example of creating a new sensor_
```
{
    "value": 23.6,
    "raining": true,
    "sensor": {
        "name": "sensor123"
    }
}
```
#### **Get** count of rainy days
```
  GET /rainyDaysCount
```
## Validation
There are some constraints in project models - validation on Database level and Hibernate level (also custom exceptions)

## Client part
- ### Python code (allows only POST method)
- ### Java code (allows both GET and POST methods)

## Tech Stack

**Application:** Java 11, Spring (Boot, Data, Core), ORM (Hibernate), REST API, Thymeleaf

**Database:** PostgreSQL
