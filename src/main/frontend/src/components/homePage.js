import document from '../images/documents.jpg'
import React from 'react';
import '../styles/homePage.css'

class HomePage extends React.Component{

    render() {
        return (
            <div class="background">
                <div class="transbox">
                    <p> Witamy na stronie PiSAT służacej do wyszukiwanie i dodawania aktów prawnych! </p>
                    <p> Wybierz zakładkę aby rozpocząć</p>
                </div>
            </div>
        )
    }
}
export default HomePage;