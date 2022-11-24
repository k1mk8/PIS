import React from 'react';
import Tabs from './tabs.js'
import Add from './addFile.js'

import './App.css'

const Tab = ({ children }) => <div className="tab">{children}</div>;

class App extends React.Component{

    render() {
        return (
            <body>
                <h2 style={{color: "red"}}>PiS Projekt</h2>
            <Tabs defaultActive={0}>
                <Tab title="Strona startowa">
                    <p>Witamy na stronie!</p>
                </Tab>
                <Tab title="Wyszukiwanie aktów">
                    <p>Wyszukiwanie aktów... Coming soon</p>
                </Tab>
                <Tab title="Dodawanie aktów">
                    <Add/>
                </Tab>
                <Tab title="Admin">
                    <p>Logowanie aktów... Coming soon</p>
                </Tab>
            </Tabs>
            </body>
        )
    }
}
export default App;