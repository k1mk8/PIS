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

    findDoc(event){
        alert('Podano taka nazwe dokumentu: ' + this.state.value);
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