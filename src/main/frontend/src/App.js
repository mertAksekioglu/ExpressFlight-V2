import React, { useState, useEffect } from "react";

import logo from "./logo.png";
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
      .get("http://localhost:8080/api/v1/flights")
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
      .get("http://localhost:8080/api/v1/flight")
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />

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

        <table>
          <thead>
            <tr>
              {/* <th>airline</th>
              <th>depAirport</th>
              <th>desAirport</th>
              <th>depDate</th>
              <th>arvDate</th> */}
            </tr>
          </thead>
          <tbody>
            {flights.map((flight, index) => (
              <tr key={index}>
                <td>{flight.airline}</td>
                <td>{flight.depAirport}</td>
                <td>{flight.desAirport}</td>
                <td>{flight.depDate}</td>
                <td>{flight.arvDate}</td>
                <td>
                  {/*<button
                    type="button"
                    onClick={() => handleDeleteFilmSubmit(flight.id)}
                  >
                    Delete
            </button>*/}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </header>
    </div>
  );
}

export default App;
