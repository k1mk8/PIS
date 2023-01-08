import React, { useState } from "react"
import "../styles/find.css"
import ShowDoc from './showDoc'

const Find = () => {
  const [users, setUsers] = useState([]);
  const [layout, setLayout] = useState();
  const [file, setFile] = useState();
  const [id, setId] = useState();
  const [title, setTitle] = useState();
  const [content, setContent] = useState();
  sessionStorage.clear();

   const findDoc = (name) => {

        if(!name){
            fetch('http://34.235.25.155:8082/lawTexts', {
                        method: 'GET',
                        headers: {
                          'Content-Type': 'application/json'
                        }
                      }).then(response => {return response.json()}).then(data => {setUsers(data)})
        }
        else if(title==1){
            fetch('http://34.235.25.155:8082/lawTexts/findByName/'+name+'', {
                        method: 'GET',
                        headers: {
                          'Content-Type': 'application/json'
                        }
                      }).then(response => {return response.json()}).then(data => {setUsers(data)})
            setTitle(0);
        }
        else if(content==1){
            fetch('http://34.235.25.155:8082/lawTexts/findByRawText/'+name+'', {
                                    method: 'GET',
                                    headers: {
                                      'Content-Type': 'application/json'
                                    }
                                  }).then(response => {return response.json()}).then(data => {setUsers(data)})
        }
        setContent(0);
   }

   async function showDoc(id) {
     const response = await fetch('http://34.235.25.155:8082/lawTexts/display/'+id+'');
     const stringResponse = await response.text();
     await setFile(stringResponse);
     return stringResponse
   }

   async function showDoc2(id) {
        await localStorage.setItem('file', file);
      }

    const handleDoc = async e => {
        e.preventDefault();
        const file = await showDoc(id)
        localStorage.setItem('file', file);
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
                  <button class="logout" type="submit" onClick={e => setTitle(1)}>Szukaj po nazwie</button> <button class="logout" type="submit" onClick={e => setContent(1)}>Szukaj po zawartości</button>
                </div>
              </form>
      {users.length > 0 && (
        <ul class="ak">
          {users.map(user => (
            <form onSubmit={handleDoc}>
                <li class="akt" key={user.id}> ID: {user.id} Nazwa: {user.name} Data dodania: {user.uploadDate}
                <div class="button"> <button onClick={e => setId(user.id)}class="accept">Pokaż</button></div> </li>
            </form>
          ))}
        </ul>
      )}
    </div>
  )
}
export default Find
