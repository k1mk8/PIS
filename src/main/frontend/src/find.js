import React from "react";

class Find extends React.Component{

    constructor(props){
        super(props);
        this.state = {value: ''};
        
        this.findDoc = this.findDoc.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        this.setState({value: event.target.value});
    }

    load (url) {
      return new Promise(async function (resolve, reject) {
        const res = await fetch(url)
        resolve(res.json())
      })
    }

    findDoc(event){
        alert('Podano taka nazwe dokumentu: ' + this.state.value);
        if(this.state.value == ''){
            const promise = this.load('http://localhost:8082/lawTexts');
            promise.then((objects) => {
                  for (const object of objects){
                        alert('Informacje o dokumentach: \nID: ' + JSON.stringify(object.id) + '\nName: ' + JSON.stringify(object.name) + '\nUpload Date: ' + JSON.stringify(object.uploadDate) + '\nAccepted: ' + JSON.stringify(object.accepted) + '\nFile: ' + JSON.stringify(object.file) + '\nReferences: ' + JSON.stringify(object.references));
                        }
                  }
            );
        }
        else
        {
            const promise = this.load(`localhost:8082/lawTexts/findByName/${this.state.value}`);
                 promise.then((objects) => {
                       for (const object of objects){
                             alert('Informacje o dokumentach: \nID: ' + JSON.stringify(object.id) + '\nName: ' + JSON.stringify(object.name) + '\nUpload Date: ' + JSON.stringify(object.uploadDate) + '\nAccepted: ' + JSON.stringify(object.accepted) + '\nFile: ' + JSON.stringify(object.file) + '\nReferences: ' + JSON.stringify(object.references));
                             }
                       }
                 );
        }
        event.preventDefault();
    }

    render()
    {
        return (
            <div>
                <form>
                    <label>
                        Dokument: {'\n'}
                    </label>
                    <div>{'\n'}</div>
                    <input typu="text" value={this.state.value} onChange={this.handleChange}/>
                </form>
                <button onClick={this.findDoc.bind(this)}>Szukaj</button>
            </div>
        )
    }
}

export default Find;