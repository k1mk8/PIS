import React, { useState } from 'react';
import './addFile.css'

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
        this.setIsSelected(true);
    }
    render() {
        return(
            <div>
                <p>Prosze załączyć plik i wcisnąć przycisk dodaj</p>
                <input type="file" name="file" onChange={this.changeHandler}/>
                {this.isSelected ? (
                    <div>
                        <p>Filename: {this.selectedFile.name}</p>
                        <p>Filetype: {this.selectedFile.type}</p>
                        <p>Size in bytes: {this.selectedFile.size}</p>
                        <p>lastModifiedDate: {' '}
                        {this.selectedFile.lastModifiedDate.toLocaleDateString()}
                        </p>
                    </div>
                ) : (
                    <p>Wybierz plik aby zobaczyć szczegóły</p>
                )}
                <button >Dodaj</button>
            </div>
        );
    }
}
export default Add;