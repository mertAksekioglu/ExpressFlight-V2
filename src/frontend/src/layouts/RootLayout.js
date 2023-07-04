import { Outlet, NavLink, ScrollRestoration } from "react-router-dom";
import React from "react";

function RootLayout() {
  return (
    <div className="root-layout">
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
  );
}

export default RootLayout;
