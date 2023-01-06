import React from "react";
import '../styles/find.css'
import App from "../app";

class AdminLogout extends React.Component{

    async logout() {
        const username = sessionStorage.getItem('username');
        const token = sessionStorage.getItem('token');
        fetch('http://localhost:8082/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username,
                token
            })
        })
        sessionStorage.clear();
    }

    render()
    {
        <App/>;
        return (
            <div>
                <form>
                    <label>
                        Jesteś zalogowany!{'\n'}
                    </label>
                    <div>{'\n'}</div>
                </form>
                <button onClick={this.logout.bind(this)}>Wyloguj</button>
            </div>
        )
    }
}

export default AdminLogout;