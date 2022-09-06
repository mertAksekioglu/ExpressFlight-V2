import React, { useState, useEffect } from "react";

import logo from "./logo.png";
import wallpaper from "./wallpaper2.jpg";
import "./App.css";
import axios from "axios";

function App() {
  const [flights, setFlights] = useState([]);

  const [addFlightData, setAddFlightData] = useState({
    depAirport: "",
    desAirport: "",
    depDate: "",
    arvDate: "",
  });

  const getFlights = () => {
    axios
      .get("http://localhost:8080/api/v1/search/flight")
      .then((response) => {
        setFlights(response.data);
      })
      .catch((error) => console.error("Error: ${error}"));
  };

  const handleAddFormChange = (event) => {
    event.preventDefault();
    const fieldName = event.target.getAttribute("name");
    const fieldValue = event.target.value;
    const newFlightData = { ...addFlightData };
    newFlightData[fieldName] = fieldValue;
    setAddFlightData(newFlightData);
  };

  const handleAddFormSubmit = (event) => {
    getFlights();
    event.preventDefault();

    axios
      .get("http://localhost:8080/api/v1/flights")
      .then(function(response) {
        console.log(response);
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  return (
    <div className="App">
      <div class="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </div>
      <div class="App-body">
        <div className="App-middle-bar">
          <div>
            <form onSubmit={handleAddFormSubmit}>
              <div>
                <text>From </text>
                <input
                  typle="text"
                  name="depAirport"
                  required="required"
                  placeholder="Enter departure airport..."
                  onChange={handleAddFormChange}
                ></input>
                <text> To </text>
                <input
                  typle="text"
                  name="desAirport"
                  required="required"
                  placeholder="Enter destination airport..."
                  onChange={handleAddFormChange}
                ></input>
              </div>
              <text> Departure Date </text>
              <input
                typle="text"
                name="depDate"
                required="required"
                placeholder="Enter a departure date..."
                onChange={handleAddFormChange}
              ></input>
              <text> Arrival Date </text>
              <input
                typle="text"
                name="arvDate"
                required="required"
                placeholder="Enter a destination date..."
                onChange={handleAddFormChange}
              ></input>
              <button type="submit">Search Flights</button>
            </form>
          </div>

          <div
            style={{
              width: 1097,
              height: 136,
              borderRadius: 48,
              backgroundColor: "#ffffff",
              justifyContent: "center",
            }}
          >
            <img></img>
            <text>18:45 - 19:30</text>
          </div>
          <img src={wallpaper} className="App-wallpaper" alt="wallpaper" />
        </div>
      </div>
    </div>
  );
}

export default App;
