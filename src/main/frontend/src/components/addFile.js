import React from 'react';
import '../styles/addFile.css'

class Add extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            selectedFile: File,
            setSelectedFile: File,
            isFilePicked: Boolean,
            setisFilePicked: Boolean,
            isSelected: Boolean
        }
    }
    changeHandler = (event) => {
        this.setSelectedFile(event.target.files[0]);
        this.isSelected = true;
    }
    render() {
        return(
            <div>
                <p>Prosze załączyć plik i wcisnąć przycisk dodaj</p>
                <input type="file" name="file" onChange={this.changeHandler}/>
                <div>{'\n'}</div>
                <button >Dodaj</button>
            </div>
        );
    }
}
export default Add;