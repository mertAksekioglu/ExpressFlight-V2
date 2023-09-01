import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

function Checkout() {
  const navigate = useNavigate();
  const location = useLocation();
  const { flight, passengerData, contactData } = location.state;
  console.log(flight);
  console.log(passengerData);
  console.log(contactData);
  return <div>Checkout</div>;
}

export default Checkout;
