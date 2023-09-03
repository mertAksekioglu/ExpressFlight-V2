import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./Flights.scss";
import { nanoid } from "nanoid";
import SunExpressSymbol from "../../assets/vectors/SunExpress_Symbol.svg";
import SunExpressLogo from "../../assets/vectors/SunExpress_Full_Logo.svg";

function Flights() {
  const navigate = useNavigate();
  const location = useLocation();
  const { flightData } = location.state;
  const [selectedFlight, setSelectedFlight] = useState(null);

  const handleFlightSelection = (flight) => {
    setSelectedFlight(flight);
    navigate("/personalInformation", { state: { flight } });
  };

  const flightDataWithIds = flightData.map((item) => ({
    id: nanoid(),
    ...item,
  }));

  return (
    <div className="flights-page-container">
      <h3 className="flights-subtitle">Flights from</h3>
      <h1 className="flights-title">Antalya(AYT) to İzmir(ADB)</h1>

      {flightData.map((flight) => (
        <FlightCard
          flight={flight}
          sendSelectedFlight={handleFlightSelection}
        />
      ))}
    </div>
  );
}

export function FlightCard(props) {
  const { flight, sendSelectedFlight } = props;
  return (
    <div
      className="flight-card"
      onClick={() => {
        console.log(flight);
        sendSelectedFlight(flight);
      }}
    >
      <FlightLogoCard props={props} />
    </div>
  );
}

export function FlightLogoCard(props) {
  return (
    <div className="flight-logo-card">
      <h1>{props.airline}</h1>
      {true ? (
        <img className="flight-company-icon" src={SunExpressSymbol} />
      ) : null}
    </div>
  );
}

export default Flights;
