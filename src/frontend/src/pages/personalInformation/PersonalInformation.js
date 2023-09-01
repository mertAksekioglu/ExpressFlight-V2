import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./PersonalInformation.scss";

function PassengerForm() {
  const navigate = useNavigate();
  const location = useLocation();
  const { flight } = location.state;
  const [passengerData, setPassengerData] = useState({
    suffix: "Mr",
    name: "",
    surname: "",
    dob: "",
    residenceCountry: "",
  });

  const [contactData, setContactData] = useState({
    suffix: "Mr",
    name: "",
    surname: "",
    email: "",
    phoneNumber: "",
    streetAddress: "",
    zipCode: "",
    city: "",
    country: "",
  });

  const handlePassengerChange = (e) => {
    const { name, value } = e.target;
    setPassengerData({ ...passengerData, [name]: value });
  };

  const handleContactChange = (e) => {
    const { name, value } = e.target;
    setContactData({ ...contactData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // You can handle form submission here

    navigate("/checkout", { state: { flight, passengerData, contactData } });
  };

  return (
    <div>
      <div style={{ backgroundColor: "gray", padding: "20px" }}>
        <h2>Passenger Information</h2>
        <form onSubmit={handleSubmit}>
          <label>
            Suffix:
            <select
              name="suffix"
              value={passengerData.suffix}
              onChange={handlePassengerChange}
            >
              <option value="Mr">Mr</option>
              <option value="Mrs">Mrs</option>
              <option value="other">Other</option>
            </select>
          </label>
          <br />
          <label>
            Name:
            <input
              type="text"
              name="name"
              value={passengerData.name}
              onChange={handlePassengerChange}
            />
          </label>
          <br />
          <label>
            Surname:
            <input
              type="text"
              name="surname"
              value={passengerData.surname}
              onChange={handlePassengerChange}
            />
          </label>
          <br />
          <label>
            Date of Birth:
            <input
              type="text"
              name="dob"
              value={passengerData.dob}
              onChange={handlePassengerChange}
            />
          </label>
          <br />
          <label>
            Country of Residence:
            <input
              type="text"
              name="residenceCountry"
              value={passengerData.residenceCountry}
              onChange={handlePassengerChange}
            />
          </label>
          <br />
        </form>
      </div>

      <div style={{ backgroundColor: "darkblue", padding: "20px" }}>
        <h2>Contact Information</h2>
        <form onSubmit={handleSubmit}>
          <label>
            Suffix:
            <select
              name="suffix"
              value={contactData.suffix}
              onChange={handleContactChange}
            >
              <option value="Mr">Mr</option>
              <option value="Mrs">Mrs</option>
              <option value="other">Other</option>
            </select>
          </label>
          <br />
          <label>
            Name:
            <input
              type="text"
              name="name"
              value={contactData.name}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Surname:
            <input
              type="text"
              name="surname"
              value={contactData.surname}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Email:
            <input
              type="text"
              name="email"
              value={contactData.email}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Phone Number:
            <input
              type="text"
              name="phoneNumber"
              value={contactData.phoneNumber}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Street Address:
            <input
              type="text"
              name="streetAddress"
              value={contactData.streetAddress}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Zip Code:
            <input
              type="text"
              name="zipCode"
              value={contactData.zipCode}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            City:
            <input
              type="text"
              name="city"
              value={contactData.city}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Country:
            <input
              type="text"
              name="country"
              value={contactData.country}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
}

export default PassengerForm;
