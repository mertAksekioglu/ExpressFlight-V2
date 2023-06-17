# ExpressFlight-V2

<h3 align="left">Project Description</h3>


- ExpressFlight is a Passenger Booking System written in Java and Spring Framework. Its a personal project with the aim of providing flight searching, seat availability checking and seat booking functions. ExpressFlight uses N-Tier architecture as its main architecture.

- ExpressFlight has two versions ExpressFlight V1 and ExpressFlight V2. The ExpressFlight V1 only had the capabilities of Flight, Airport and Plane CRUD operations and searching of flights. It has approximately 25 classes. ExpressFlight V2 is a much comprehensive project that includes Ticket, Seat, Passenger and their respective operations for a functional passenger booking website. ExpressFlight V2 has 93 classes and over 3000 lines of functional code. 

- The web application is inspired by real life flight providing systems such as Hitit and SanFlight.

- ExpressFlight is still currently in development See more at Future Plans
<br>
<h3 align="left">Technologies Used</h3>


This project was developed with:

* **Java 11**
* **Spring Boot 2.7.3**
* **Spring Boot Devtools**
* **Lombok**
* **Gson 2.9.1**
* **Gradle**
* **PostgreSQL**
* **Model Mapper 2.1.1**
<br>
<h3 align="left">Automatic Seat Configuration</h3>


- ExpressFlight has the ability to configure seats automatically for flights entities. Each flight entity has a seat configuration entity. Each seat configuration has a 2D Array of Seat entities that represent the possible seats a passenger can buy tickets for.

- When a new flight and a subsequent new seat configuration is created the user does not need to send the 2D Seat Array to the system. Given the name of the seat configuration such as "SunExpress7378HC" or "PegasusA320251N" the program is able to create the complete seats for the seat configuration of the flight. As of right now only 2 seat configurations mentioned above support auto-configuration.

![seat config diagram2](https://github.com/mertAksekioglu/ExpressFlight-V2/assets/40835236/71d7873c-4f98-4e26-838d-e7f1e06ba232)

As seen by the diagram. Once a flight and its seat configuration is created. The system uses seat configurations unique name to create a seat map for the seat configuration. This is the automatic seat configuration of the project.

<br>
<h3 align="left">Backwards Integration</h3>

- ExpressFlight has the ability to integrate flight classes from Version 1.0. This means the the new program is capable of using older flight data which has different attributes in between the versions. Currently only SunExpress flight data integration is supported.   

- As the project was getting reworked, some classes had their attributes and their class strucure changed. This made the program no longer being able to accept older flight data. Previously it had 16 mock SunExpress Flights. Instead of changing the flight_data.json file, the application is made to be capable of integrating and converting the older flight entity of version 1.0 to the new flight entity of version 2.0.  

The Integration process consists of multiple classes and objects. Here are these objects and their purposes.
- mockService: Reads the old_flight_data.json line by line and sends it as a String.
- mockController: Is a mock endpoint for mockService.
- provider: has the endPoint link. Gets data and stores it in String format.
- sunExpressFlightDTO: is a DTO for mapping the String data to a Java object.
- mapper: Maps an object into another in this case maps "SunExpressFlightDTO" to "FlightDTO".
- integration: Takes String data from provider, converts it into SunExpressFlightDTO, maps it to a FlightDTO, then adds the FlightDTO to the database via - flightService.
- integrationService: calls the integration for demonstration purposes.
<br>

![integrationDiag2](https://github.com/mertAksekioglu/ExpressFlight-V2/assets/40835236/f262e93c-6de8-471a-b7f8-08e5b8a5483a)

- As seen by the above diagram. The older data is first taken by the mockService then it goes through mockController, provider, integration, mapper, back to integration and finally the service. The data is shown taking various forms including Strings, DTO's and as an Entity. 

<br>
<h3 align="left">Demo Videos</h3>

<h4>Booking a Ticket</h4>

https://github.com/mertAksekioglu/ExpressFlight-V2/assets/40835236/244480f5-14b9-4cae-9ddd-2dd9c0672c37

<br>
<h4>Automatically Configuring Seats</h4>

https://github.com/mertAksekioglu/ExpressFlight-V2/assets/40835236/6928e2cb-f4af-4b46-898c-ca0b3e203ac2

<br>
<h4>Integreate Old SunExpress Flight Data</h4>

https://github.com/mertAksekioglu/ExpressFlight-V2/assets/40835236/95a3f277-2131-48ab-92ae-78b55fbb7fac

<br>
<h3 align="left">Future Plans</h3>

The future plans of the project include in order of importance

- Code Cleanup, Field Injection Removal (40%)
- Custom DTOMappers for Entities
- Tests with JUnit and Mockito (Learning)
- Spring Security Implementation (Learning)
- Microservices Implementation (Learning)
- UI/UX Design (70%)
![image](https://github.com/mertAksekioglu/ExpressFlight-V2/assets/40835236/0f17ec4d-8ce4-4e47-81e3-984ebf6d2cdb)
- Front-End with React
- Production Release
