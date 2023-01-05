import React from "react"

class Login extends React.Component {
    render(){
        return (
            <div className="Auth-form-container">
              <form className="Auth-form">
                <div className="Auth-form-content">
                  <h3 className="Auth-form-title">Zaloguj się</h3>
                  <div className="form-group mt-3">
                    <label>Email</label>
                    <input
                      type="email"
                      className="form-control mt-1"
                      placeholder="Email..."
                    />
                  </div>
                  <div className="form-group mt-3">
                    <label>Hasło</label>
                    <input
                      type="password"
                      className="form-control mt-1"
                      placeholder="Hasło..."
                    />
                  </div>
                  <div className="d-grid gap-2 mt-3">
                    <button type="submit" className="btn btn-primary">
                      Zaloguj
                    </button>
                  </div>
                </div>
              </form>
            </div>
          )
    }
}

export default Login;