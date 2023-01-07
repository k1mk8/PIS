import React, { useState } from "react"
import "../styles/find.css"
import ShowDoc from './showDoc'

const Find = () => {
  const [users, setUsers] = useState([]);
  const [layout, setLayout] = useState();
  const [file, setFile] = useState();

   const findDoc = (name) => {
         fetch('http://localhost:8082/lawTexts/findByName/'+name+'', {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
          }).then(response => {return response.json()}).then(data => {setUsers(data)})
   }

   async function showDoc(id) {
     const response = await fetch('http://localhost:8082/lawTexts/display/'+id+'');
     const stringResponse = await response.text();
     await setFile(stringResponse);
   }

   async function showDoc2(id) {
        await sessionStorage.setItem('file', file);
      }

   const newWindow = (id) => {
           showDoc(id);
           showDoc2();
           window.open('http://localhost:3000/dokument');
      }

   const handleSubmit = async e => {
         e.preventDefault();
         const token = await findDoc(layout);
         return token;
     }

  return (
    <div class="akty">
    <form onSubmit={handleSubmit}>
        <label class="cos">
                  <p>Szukanie Aktów</p>
                  <input class="user" type="text" onChange={e => setLayout(e.target.value)} />
                </label>
                <div>
                  <button class="logout" type="submit">Szukaj</button>
                </div>
              </form>
      {users.length > 0 && (
        <ul class="ak">
          {users.map(user => (
            <li class="akt" key={user.id}> ID: {user.id} Nazwa: {user.name} Data dodania: {user.uploadDate}
                <div class="button"> <button class="accept" onClick={() => {newWindow(user.id)}}>Pokaż</button></div> </li>
          ))}
        </ul>
      )}
    </div>
  )
}
export default Find
