import React from 'react';

class Acts extends React.Component{
    constructor(props){
        super(props);
    }

    load (url) {
        return new Promise(async function (resolve) {
            const res = await fetch(url)
            resolve(res.json())
        })
    }

    showDocUnauthorized(event){
        const promise = this.load('http://localhost:8082/lawTexts/notAccepted');
        promise.then((objects) => {
                for (const object of objects){
                    alert('Informacje o dokumentach: \nID: ' + JSON.stringify(object.id) + '\nName: ' + JSON.stringify(object.name) + '\nUpload Date: ' + JSON.stringify(object.uploadDate) + '\nAccepted: ' + JSON.stringify(object.accepted) + '\nFile: ' + JSON.stringify(object.file) + '\nReferences: ' + JSON.stringify(object.references));
                }
            }
        );
        event.preventDefault();
    }

    showDocAuthorized(event){
        const promise = this.load('http://localhost:8082/lawTexts/accepted');
        promise.then((objects) => {
                for (const object of objects){
                    alert('Informacje o dokumentach: \nID: ' + JSON.stringify(object.id) + '\nName: ' + JSON.stringify(object.name) + '\nUpload Date: ' + JSON.stringify(object.uploadDate) + '\nAccepted: ' + JSON.stringify(object.accepted) + '\nFile: ' + JSON.stringify(object.file) + '\nReferences: ' + JSON.stringify(object.references));
                }
            }
        );
        event.preventDefault();
    }

    render()
    {
        return (
            <div>
                <button onClick={this.showDocUnauthorized.bind(this)}>Niezatwierdzone</button>
                <button onClick={this.showDocAuthorized.bind(this)}>Zatwierdzone</button>
            </div>
        )
    }

}
export default Acts;