import React from "react";
import '../styles/adminLogout.css'

class AdminLogout extends React.Component{

    async logout() {
        const username = sessionStorage.getItem('username');
        const token = sessionStorage.getItem('token');
        fetch('http://34.235.25.155:8082/logout', {
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
        window.location.reload(false);
    }

    render()
    {
        return (
            <div class="wyloguj">
                <form>
                    <label class="log">
                        Jesteś zalogowany!{'\n'}
                    </label>
                    <div>{'\n'}</div>
                </form>
                <button class="logout" onClick={this.logout.bind(this)}>Wyloguj</button>
            </div>
        )
    }
}

export default AdminLogout;