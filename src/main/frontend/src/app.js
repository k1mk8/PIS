import React from 'react';
import Navbar from './components/Navbar.js'
import './styles/app.css'
import Find from './components/find'
import HomePage from './components/homePage.js';
import Add from './components/addFile'
import Login from './components/login'
import "bootstrap/dist/css/bootstrap.min.css"
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

class App extends React.Component{

    render() {
        return (
            <Router>
                <div>
                    <Navbar />
                    <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="/stronastartowa" element={<HomePage />} />
                        <Route path="/wyszukiwanieaktow" element={<Find />} />
                        <Route path="/dodawanieaktow" element={<Add />} />
                        <Route path="/admin" element={<Login />} />
                    </Routes>
                </div>
            </Router>
        )
    }
}
export default App;