import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./PersonalInformation.scss";

function PassengerForm() {
  const navigate = useNavigate();
  const location = useLocation();
  const { flight } = location.state;
  const [passenger, setPassenger] = useState({
    suffix: "Mr",
    name: "",
    surname: "",
    dateOfBirth: "",
    country: "",
  });

  const [contact, setContact] = useState({
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
    setPassenger({ ...passenger, [name]: value });
  };

  const handleContactChange = (e) => {
    const { name, value } = e.target;
    setContact({ ...contact, [name]: value });
  };

  const setJuliusData = () => {
    setPassenger({});
    setContact({});
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // You can handle form submission here

    navigate("/checkout", { state: { flight, passenger, contact } });
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
              value={passenger.suffix}
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
              value={passenger.name}
              onChange={handlePassengerChange}
            />
          </label>
          <br />
          <label>
            Surname:
            <input
              type="text"
              name="surname"
              value={passenger.surname}
              onChange={handlePassengerChange}
            />
          </label>
          <br />
          <label>
            Date of Birth:
            <input
              type="text"
              name="dateOfBirth"
              value={passenger.dateOfBirth}
              onChange={handlePassengerChange}
            />
          </label>
          <br />
          <label>
            Country of Residence:
            <input
              type="text"
              name="country"
              value={passenger.country}
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
              value={contact.suffix}
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
              value={contact.name}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Surname:
            <input
              type="text"
              name="surname"
              value={contact.surname}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Email:
            <input
              type="text"
              name="email"
              value={contact.email}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Phone Number:
            <input
              type="text"
              name="phoneNumber"
              value={contact.phoneNumber}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Street Address:
            <input
              type="text"
              name="streetAddress"
              value={contact.streetAddress}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Zip Code:
            <input
              type="text"
              name="zipCode"
              value={contact.zipCode}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            City:
            <input
              type="text"
              name="city"
              value={contact.city}
              onChange={handleContactChange}
            />
          </label>
          <br />
          <label>
            Country:
            <input
              type="text"
              name="country"
              value={contact.country}
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
