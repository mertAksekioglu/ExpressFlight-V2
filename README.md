# ExpressFlight-V2

<br>
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



<br>
<h3 align="left">Backwards Integration</h3>

<br>
<h3 align="left">Demo Videos</h3>

<br>
<h3 align="left">Future Plans</h3>
