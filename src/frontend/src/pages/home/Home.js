import React, { useState } from "react";
import "./Home.scss";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import PlaneDepartureIcon from "../../assets/vectors/plane-departure-solid.svg";
import PlaneArrivalIcon from "../../assets/vectors/plane-arrival-solid.svg";
import CalendarIcon from "../../assets/vectors/calendar-days-solid.svg";
function Home() {
  return (
    <div className="home-page-container">
      <div className="vertical-line" />
      <div className="right-container">
        <h1 className="title">A Hobby project on Passenger Ticket Systems</h1>
        <FlightCard />
      </div>
    </div>
  );
}

export function FlightCard() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    depAirport: "",
    desAirport: "",
    depDate: "",
    desDate: "",
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    // Create a payload with the form data
    const payload = {
      depAirport: formData.depAirport,
      desAirport: formData.desAirport,
      depDate: formData.depDate,
      desDate: formData.desDate,
    };

    // You can replace 'your_api_endpoint' with the actual API endpoint
    axios
      .post("http://localhost:8080/api/v1/flight/search-flight", payload)
      .then((response) => {
        // Handle the API response here
        const flightData = response.data;

        // Navigate to the FlightsScreen component with the flight data
        navigate("/flights", { state: { flightData } });
        console.log("API Response:", response.data);
      })
      .catch((error) => {
        // Handle any errors here
        console.error("Error:", error);
      });
  };

  return (
    <div className="search-flight-card">
      <h2 className="search-flight-card-title">Search Flight</h2>
      <form onSubmit={handleFormSubmit}>
        <div className="submit-row">
          <div className="flight-search-input-container">
            <img
              className="flight-search-input-icon"
              src={PlaneDepartureIcon}
            />
            <input
              className="flight-search-input"
              type="text"
              id="depAirport"
              name="depAirport"
              placeholder="Departure Airport"
              value={formData.depAirport}
              onChange={handleInputChange}
            />
          </div>
          <div className="flight-search-input-container">
            <img className="flight-search-input-icon" src={PlaneArrivalIcon} />
            <input
              className="flight-search-input"
              type="text"
              id="desAirport"
              name="desAirport"
              placeholder="Destination Airport"
              value={formData.desAirport}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="submit-row">
          <div className="flight-search-input-container">
            <img className="flight-search-input-icon" src={CalendarIcon} />
            <input
              className="flight-search-input"
              type="text"
              id="depDate"
              name="depDate"
              placeholder="Departure Date"
              value={formData.depDate}
              onChange={handleInputChange}
            />
          </div>
          <div className="flight-search-input-container">
            <img className="flight-search-input-icon" src={CalendarIcon} />
            <input
              className="flight-search-input"
              type="text"
              id="desDate"
              name="desDate"
              placeholder="Arrival Date"
              value={formData.desDate}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="submit-row">
          <button className="flight-search-button" type="submit">
            Search Flight
          </button>
        </div>
      </form>
    </div>
  );
}

export async function loadFlightData(response) {
  return await response.data;
}

export function FlightTextBox() {
  return <h1>no</h1>;
}

export default Home;
