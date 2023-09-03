import React, { useState } from "react";
import "./Checkout.css"; // You can create a Checkout.css file for styling
import { useLocation, useNavigate } from "react-router-dom";

function Checkout() {
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const { flight, passenger, contact } = location.state;
  const handlePaymentMethodChange = (e) => {
    setSelectedPaymentMethod(e.target.value);
  };

  const handleCheckout = () => {
    // You can perform the checkout logic here, including sending the selectedPaymentMethod to the server.
    console.log("Payment method selected:", selectedPaymentMethod);
    navigate("/");
  };

  const getPassengerAgeCategory = () => {
    const dobParts = passenger.dateOfBirth.split("-");
    const birthYear = parseInt(dobParts[2]);
    const currentYear = new Date().getFullYear();
    const age = currentYear - birthYear;

    return age > 7 ? "adult" : "child";
  };

  return (
    <div className="checkout-container">
      <div className="checkout-div">
        <h2>Flight Information</h2>
        <p>Airline: {flight.airline}</p>
        <p>
          Departure Airport: {flight.depAirport.codeIATA} -{" "}
          {flight.depAirport.name}
        </p>
        <p>
          Arrival Airport: {flight.arvAirport.codeIATA} -{" "}
          {flight.arvAirport.name}
        </p>
        <p>Departure Date/Time: {flight.depDateTime}</p>
        <p>Arrival Date/Time: {flight.arvDateTime}</p>
        <p>Flight Code: {flight.flightCode}</p>
        <p>Price: {flight.price}</p>
      </div>

      <div className="checkout-div">
        <h2>Passenger Information</h2>
        <p>Suffix: {passenger.suffix}</p>
        <p>
          Name: {passenger.name} {passenger.surname}
        </p>
        <p>Age Category: {getPassengerAgeCategory()}</p>
      </div>

      <div className="checkout-div">
        <h2>Contact Information</h2>
        <p>Suffix: {contact.suffix}</p>
        <p>
          Name: {contact.name} {contact.surname}
        </p>
        <p>Email: {contact.email}</p>
        <p>Phone Number: {contact.phoneNumber}</p>
      </div>

      <form className="payment-form" onSubmit={handleCheckout}>
        <h2>Payment Information</h2>
        <select
          value={selectedPaymentMethod}
          onChange={handlePaymentMethodChange}
          required
        >
          <option value="">Select Payment Method</option>
          <option value="Visa">Visa</option>
          <option value="MasterCard">MasterCard</option>
          <option value="ExpressPay">ExpressPay</option>
        </select>
        <button type="submit" className="checkout-button">
          Checkout
        </button>
      </form>
    </div>
  );
}

export default Checkout;
