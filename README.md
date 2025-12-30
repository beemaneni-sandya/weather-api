# Weather API Application

## Overview
Weather API is a Spring Boot–based backend application that fetches and
returns weather information using an external weather service. The project
demonstrates REST API development and third-party API integration.

## Problem Statement
Many applications require real-time weather data, but integrating external
weather services can introduce complexity. This project provides a clean,
structured backend API to retrieve and expose weather information reliably.

## Solution
The application:
- Accepts location-based requests
- Calls an external weather API
- Processes and formats the response
- Exposes weather data through RESTful endpoints

## Architecture
- **Controller Layer** – Handles incoming API requests
- **Service Layer** – Manages external API communication
- **Integration Layer** – Processes third-party weather data
- **Configuration Layer** – Manages API keys and settings

## Tech Stack
- **Language:** Java
- **Framework:** Spring Boot
- **Backend:** Spring Boot Web (REST APIs)
- **External Integration:** Weather API service
- **Build Tool:** Maven
- **Server:** Embedded Tomcat
- **Version Control:** Git

## Key Features
- External API integration
- RESTful endpoint design
- Clean separation of concerns
- Easy to extend with caching or persistence

## How to Run
1. Clone the repository:
   ```bash
   git clone git@github.com:beemaneni-sandya/weather-api.git
2. Navigate to the project directory:
   ```bash
   cd weather-api
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
4. Access the API at:
   ```bash
   http://localhost:9090

## Future Enhancements
- Caching for API responses
- Error handling and retries
- Support for multiple weather providers
- API authentication
  
