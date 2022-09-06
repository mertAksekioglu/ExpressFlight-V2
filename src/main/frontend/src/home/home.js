import React, { useState, useEffect } from "react";
import wallpaper from "../wallpaper2.jpg";
import logo from "../logo.png";
import "./home.scss";
import axios from "axios";
const Home = () => {
  const [flights, setFlights] = useState([]);
  const [showFlights, setShowFlights] = useState(false);
  const [addFlightData, setAddFlightData] = useState({
    depAirport: "AYT",
    desAirport: "ADB",
    depDate: "31-08-2022",
  });

  const getFlights = () => {
    axios
      .post("http://localhost:8080/api/v1/flight/search-flight", {
        depAirport: "AYT",
        desAirport: "ADB",
        depDate: "31-08-2022",
      })
      .then((response) => {
        setFlights(response.data);
      })
      .catch((error) => console.error("Error: ${error}"));
  };

  useEffect(() => {
    console.log(flights);
  });

  const handleAddFormChange = (event) => {
    event.preventDefault();
    const fieldName = event.target.getAttribute("name");
    const fieldValue = event.target.value;
    const newFlightData = { ...addFlightData };
    newFlightData[fieldName] = fieldValue;
    setAddFlightData(newFlightData);
  };

  const handleAddFormSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/v1/flight/search-flight", {
        depAirport: "AYT",
        desAirport: "ADB",
        depDate: "31-08-2022",
      })
      .then(function (response) {
        setFlights(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    setShowFlights(true);
  };

  return (
    <div className="body">
      <div>
        <div className="app-container">
          <div className="top-container">
            <div className="logo-container">
              <img src={logo} className="logo" />
            </div>

            <div className="top-tabs-container">
              <text className="header-text"> Booking </text>
              <text className="header-text"> About Us </text>
              <text className="header-text"> Inspirations </text>
            </div>
          </div>
          <div className="search-container">
            <form onSubmit={handleAddFormSubmit}>
              <div>
                <text className="search-text">From </text>
                <input
                  typle="text"
                  name="depAirport"
                  required="required"
                  placeholder="Enter departure airport..."
                  onChange={handleAddFormChange}
                ></input>
                <text className="search-text"> To </text>
                <input
                  typle="text"
                  name="desAirport"
                  required="required"
                  placeholder="Enter destination airport..."
                  onChange={handleAddFormChange}
                ></input>
              </div>
              <text className="search-text"> Departure Date </text>
              <input
                typle="text"
                name="depDate"
                required="required"
                placeholder="Enter a departure date..."
                onChange={handleAddFormChange}
              ></input>

              {/* <text className="search-text"> Arrival Date </text>
              <input
                typle="text"
                name="arvDate"
                required="required"
                placeholder="Enter a destination date..."
                onChange={handleAddFormChange}
              ></input> */}
              <button type="submit">Search Flights</button>
            </form>
          </div>
          <div className="flights-container">
            <text className="result-header-text">Departures:</text>
            {flights.map((flight, index) => (
              <div style={{ backgro }} className="flight-card">
                <text className="header-text">{flight.airline}</text>
                <text className="header-text">
                  {flight.desAirport} - {flight.depAirport}
                </text>
                <text className="header-text">
                  {flight.depTime} - {flight.arvTime}
                </text>
              </div>
            ))}
          </div>
        </div>
        <img src={wallpaper} className="background-image" />
      </div>
    </div>
  );
};

export default Home;
