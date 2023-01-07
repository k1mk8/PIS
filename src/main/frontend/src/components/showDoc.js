import file from './find.js'
import '../styles/showDoc.css'
import React, { useState } from "react"




    export default function ShowDoc(){
     const getPDF = () => {
        const tokenString = sessionStorage.getItem('file');
        const userToken = tokenString;
        return userToken
      }

      const [PDF, setPDF] = useState(getPDF());

        return (<div class="wyn">
            <embed class="pdf" src={`data:application/pdf;base64,${PDF}`} />
            </div>)
}