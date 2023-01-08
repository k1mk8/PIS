import React, { useState } from 'react';
import PropTypes from 'prop-types';
import '../styles/login.css';

async function loginUser(credentials) {
 return fetch('http://34.235.25.155:8082/login', {
   method: 'POST',
   headers: {
     'Content-Type': 'application/json'
   },
   body: JSON.stringify(credentials)
 })
   .then(data => data.text())
}

export default function Login({ setToken }) {
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();

  const handleSubmit = async e => {
      e.preventDefault();
      const token = await loginUser({
        username,
        password
      });
      sessionStorage.setItem('username', username);
      setToken(token);
      return token;
  }
   return(
        <div className="login-wrapper">
          <h1 class="napis">Please Log In</h1>
          <form onSubmit={handleSubmit}>
            <label class="cos">
              <p>Username</p>
              <input class="user" type="text" onChange={e => setUserName(e.target.value)} />
            </label>
            <label class="cos">
              <p>Password</p>
              <input class="user" type="password" onChange={e => setPassword(e.target.value)} />
            </label>
            <div>
              <button class="logout" type="submit">Submit</button>
            </div>
          </form>
        </div>
  )
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired
};