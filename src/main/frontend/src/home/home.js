import React, { useState, useEffect } from "react";
import wallpaper from "../wallpaper2.jpg";
import logo from "../logo.png";
import "./home.scss";
import axios from "axios";
const Home = () => {
  const [departureFlights, setDepartureFlights] = useState([]);
  const [returnFlights, setReturnFlights] = useState([]);
  const [showFlights, setShowFlights] = useState(false);
  const [addFlightData, setAddFlightData] = useState({
    depAirport: "AYT",
    desAirport: "ADB",
    depDate: "31-08-2022",
    retDate: "07-09-2022",
  });

  const getFlights = () => {
    axios
      .post("http://localhost:8080/api/v1/flight/search-flight", {
        depAirport: "AYT",
        desAirport: "ADB",
        depDate: "31-08-2022",
      })
      .then((response) => {
        setDepartureFlights(response.data);
      })
      .catch((error) => console.error("Error: ${error}"));
  };

  useEffect(() => {
    // console.log(departureflights);
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
        depAirport: addFlightData.depAirport,
        desAirport: addFlightData.desAirport,
        depDate: addFlightData.depDate,
      })
      .then(function (response) {
        setDepartureFlights(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    axios
      .post("http://localhost:8080/api/v1/flight/search-flight", {
        depAirport: addFlightData.desAirport,
        desAirport: addFlightData.depAirport,
        depDate: addFlightData.retDate,
      })
      .then(function (response) {
        setReturnFlights(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    setShowFlights(true);
  };

  const handleResponseAirline = (flight) => {
    let airline = flight.flightSegments[0].airline;
    if (airline == "Sunexpress") {
      return require("../assets/logos/sunex-logo.png");
    }
    if (airline == "Turkish Airlines") {
      return require("../assets/logos/thy-logo.png");
    }
    if (airline == "AnadoluJet") {
      return require("../assets/logos/andjet-logo.png");
    }
  };

  return (
    <div className="body">
      <div className="background-container">
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
            <form className="search-form" onSubmit={handleAddFormSubmit}>
              <div className="search-column">
                <text className="search-text">From </text>
                <input
                  type="text"
                  name="depAirport"
                  required="required"
                  placeholder="Enter departure airport..."
                  className="text-input"
                  onChange={handleAddFormChange}
                ></input>
                <text className="search-text"> Departure Date </text>

                <input
                  type="text"
                  name="depDate"
                  required="required"
                  placeholder="Enter a departure date..."
                  className="text-input"
                  onChange={handleAddFormChange}
                ></input>
              </div>
              <div className="search-column">
                <text className="search-text"> To </text>
                <input
                  type="text"
                  name="desAirport"
                  required="required"
                  placeholder="Enter destination airport..."
                  className="text-input"
                  onChange={handleAddFormChange}
                ></input>

                <text className="search-text"> Arrival Date </text>

                <input
                  typle="text"
                  name="arvDate"
                  required="required"
                  placeholder="Enter a destination date..."
                  className="text-input"
                  onChange={handleAddFormChange}
                ></input>
              </div>
              <button className="search-button" type="submit">
                Search Flights
              </button>
            </form>
          </div>
          <div className="flights-container">
            <text className="result-header-text">
              {" "}
              <b>{showFlights ? "Departures" : ""}</b>
            </text>
            {showFlights ? <div className="horizontal-white-line"></div> : null}

            {departureFlights.map((flight, index) => (
              <div
                className="flight-card"
                onClick={() => {
                  console.log("Nice");
                }}
              >
                <img
                  src={handleResponseAirline(flight)}
                  className="flight-card-image"
                />
                <text className="card-text">
                  {flight.flightSegments[0].airline}
                </text>
                <text className="card-time-text">
                  {flight.flightSegments[0].depTime} -{" "}
                  {
                    flight.flightSegments[flight.flightSegments.length - 1]
                      .arvTime
                  }
                </text>
                <text className="card-text">
                  {flight.flightSegments[0].depAirport} -{" "}
                  {
                    flight.flightSegments[flight.flightSegments.length - 1]
                      .desAirport
                  }
                </text>

                <text className="card-text">
                  <b>{flight.isConnected ? "Connecting" : "Nonstop"}</b>
                </text>

                <text className="card-large-text">{flight.price} TRY</text>
              </div>
            ))}
            <text className="result-header-text">
              <b>{showFlights ? "Return Flights" : ""}</b>
            </text>
            {showFlights ? <div className="horizontal-white-line"></div> : null}
            {returnFlights.map((flight, index) => (
              <div
                className="flight-card"
                onClick={() => {
                  console.log("Nice");
                }}
              >
                <img
                  src={handleResponseAirline(flight)}
                  className="flight-card-image"
                />
                <text className="card-text">
                  {flight.flightSegments[0].airline}
                </text>
                <text className="card-time-text">
                  {flight.flightSegments[0].depTime} -{" "}
                  {
                    flight.flightSegments[flight.flightSegments.length - 1]
                      .arvTime
                  }
                </text>
                <text className="card-text">
                  {flight.flightSegments[0].depAirport} -{" "}
                  {
                    flight.flightSegments[flight.flightSegments.length - 1]
                      .desAirport
                  }
                </text>

                <text className="card-text">
                  <b>{flight.isConnected ? "Connecting" : "Nonstop"}</b>
                </text>

                <text className="card-large-text">{flight.price} TRY</text>
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
