import React from "react";
import "./index.css";
import App from "./App";
import Login from "./Login";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Box, Typography} from "@mui/material";
import SocialLogin from "./SocialLogin";

function Copyright() {
  return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"Copyright © "}
        hackathon restart 2022 samsung, {new Date().getFullYear()}
        {"."}
      </Typography>
  );
}

function AppRouter() {
  return (
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<App />} />
            <Route path="login" element={<Login />} />
            <Route path="sociallogin" element={<SocialLogin />} />
          </Routes>
        </BrowserRouter>
        <Box mt={5}>
          <Copyright />
        </Box>
      </div>
  );
};

export default AppRouter;
