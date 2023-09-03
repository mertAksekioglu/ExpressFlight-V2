import { Outlet, NavLink, ScrollRestoration } from "react-router-dom";
import React from "react";
import "./RootLayout.scss";
import Logo from "../assets/vectors/logo.svg";
import Wallpaper from "../assets/images/background-night.png";

function RootLayout() {
  return (
    <div className="background-container">
      <div className="app-container">
        <ScrollRestoration />
        <header className="header">
          <div className="logo-container">
            <img class="logo" src={Logo} />
            <h1 class="noto-sans">ExpressFlight</h1>
          </div>
          <nav>
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
      <img src={Wallpaper} className="background-image" />
    </div>
  );
}

export default RootLayout;
