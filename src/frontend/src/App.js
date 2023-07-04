import logo from "./logo.svg";
import { nanoid } from "nanoid";
import "./App.css";
import data from "./mock-data.json";
import { useState } from "react";
import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
  RouterProvider,
} from "react-router-dom";
import RootLayout from "./layouts/RootLayout";
import Home from "./pages/home/Home";
import Login from "./pages/login/Login";

import AdminLayout from "./layouts/AdminLayout";
import AdminPanel from "./pages/adminPanel/AdminPanel";

import AirportTable from "./pages/tables/airportTable/AirportTable";
import ConnectedFlightTable from "./pages/tables/connectedFlightTable/ConnectedFlightTable";
import CoordinateTable from "./pages/tables/coordinateTable/CoordinateTable";
import FlightTable from "./pages/tables/flightTable/FlightTable";
import PassengerTable from "./pages/tables/passengerTable/PassengerTable";
import PlaneTable from "./pages/tables/planeTable/PlaneTable";
import SeatConfigurationTable from "./pages/tables/seatConfigurationTable/SeatConfigurationTable";
import SeatTable from "./pages/tables/seatTable/SeatTable";
import TicketTable from "./pages/tables/ticketTable/TicketTable";

import SearchFlight from "./pages/searchFlight/SearchFlight";
import Flights from "./pages/Flights/Flights";
import PersonalInformation from "./pages/personalInformation/PersonalInformation";
import SeatSelection from "./pages/seatSelection/SeatSelection";
import Checkout from "./pages/checkout/Checkout";
const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<RootLayout />}>
      <Route index element={<Home />} />
      <Route path="login" element={<Login />} />
      <Route path="admin" element={<AdminLayout />}>
        <Route path="adminPanel" element={<AdminPanel />} />
        <Route path="airport" element={<AirportTable />} />
        <Route path="connectedFlight" element={<ConnectedFlightTable />} />
        <Route path="coordinate" element={<CoordinateTable />} />
        <Route path="flight" element={<FlightTable />} />
        <Route path="passenger" element={<PassengerTable />} />
        <Route path="plane" element={<PlaneTable />} />
        <Route path="seatConfig" element={<SeatConfigurationTable />} />
        <Route path="seat" element={<SeatTable />} />
        <Route path="ticket" element={<TicketTable />} />
      </Route>
      <Route path="searchFlight" element={<SearchFlight />} />
      <Route path="Flights" element={<Flights />} />
      <Route path="personalInformation" element={<PersonalInformation />} />
      <Route path="seatSelection" element={<SeatSelection />} />
      <Route path="checkout" element={<Checkout />} />
    </Route>
  )
);

const App = () => {
  return <RouterProvider router={router} />;
};

export default App;
