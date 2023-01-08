import React from 'react';
import Navbar from './components/Navbar.js'
import AdminLogout from './components/adminLogout.js'
import './styles/app.css'
import Find from './components/find'
import HomePage from './components/homePage.js';
import Add from './components/addFile'
import Login from './components/login'
import "bootstrap/dist/css/bootstrap.min.css"
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import useToken from './components/useToken';
import Acts from './components/acts'
import ShowDoc from './components/showDoc'

function App (){
    const { token, setToken } = useToken();
    if(!token) {
       return (
                   <Router>
                       <div>
                           <Navbar />
                           <Routes>
                               <Route path="/" element={<HomePage />} />
                               <Route path="/stronastartowa" element={<HomePage />} />
                               <Route path="/wyszukiwanieaktow" element={<Find />} />
                               <Route path="/dodawanieaktow" element={<Add />} />
                               <Route path="/admin" element={<Login setToken={setToken} />} />
                               <Route path="/dokument" element={<ShowDoc />} />
                           </Routes>
                       </div>
                   </Router>
               )
    }
    if(token){
        return (
            <Router>
                <div>
                    <Navbar />
                    <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="/stronastartowa" element={<HomePage />} />
                        <Route path="/wyszukiwanieaktow" element={<Find />} />
                        <Route path="/dodawanieaktow" element={<Add />} />
                        <Route path="/adminwyloguj" element={<AdminLogout />} />
                        <Route path="/akty" element={<Acts />} />
                    </Routes>
                </div>
            </Router>
        )
    }
}
export default App;