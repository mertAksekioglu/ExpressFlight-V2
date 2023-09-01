import React, { useState } from "react";
import "./Home.scss";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
function Home() {
  return (
    <div className="home-page-container">
      <div className="vertical-line" />
      <div className="right-container">
        <h1 className="title">Creating the future of Airline & IT Solutions</h1>
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
        <div>
          <div>
            <label htmlFor="depAirport">Departure Airport:</label>
            <input
              type="text"
              id="depAirport"
              name="depAirport"
              value={formData.depAirport}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label htmlFor="desAirport">Destination Airport:</label>
            <input
              type="text"
              id="desAirport"
              name="desAirport"
              value={formData.desAirport}
              onChange={handleInputChange}
            />
          </div>
          <div></div>
          <div>
            <label htmlFor="depDate">Departure Date:</label>
            <input
              type="text"
              id="depDate"
              name="depDate"
              value={formData.depDate}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label htmlFor="desDate">Arrival Date:</label>
            <input
              type="text"
              id="desDate"
              name="desDate"
              value={formData.desDate}
              onChange={handleInputChange}
            />
          </div>
        </div>

        <button type="submit">Search Flight</button>
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
