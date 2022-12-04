import React from "react";
import {Container, Grid, Link} from "@mui/material";
import {socialLogin} from "./service/ApiService";

function Login() {

  const handleSocialLogin = (provider) => {
    socialLogin(provider);
  }

  return (
      <Container component="main" maxWidth="xs" style={{marginTop: "8%"}}>
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justifyContent="center"
        >
          <Grid item xs={3}>
            <Link onClick={() => handleSocialLogin("kakao")}>
              <img align="center"
                   src={'/kakao_login_medium_wide.png'}
                   alt="kakao_login_medium_wide.png"
              />
            </Link>
          </Grid>
        </Grid>
      </Container>
  );
}

export default Login;
