import { Outlet, NavLink, ScrollRestoration } from "react-router-dom";
import React from "react";
import ".RootLayout.css";

function RootLayout() {
  return (
    <div className="background-container">
      <div className="app-container">
        <ScrollRestoration />
        <header>
          <nav>
            <h1>Lufthansa Lufthansa</h1>
            <NavLink to="/">Home</NavLink>
            <NavLink to="login">Login</NavLink>
            <NavLink to="adminPanel">Admin Panel</NavLink>
            <NavLink to="flightTable">Flight Table</NavLink>
          </nav>
        </header>
        <main>
          <Outlet />
        </main>
      </div>
    </div>
  );
}

export default RootLayout;
