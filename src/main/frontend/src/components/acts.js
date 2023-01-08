import React, { useState } from "react"
import "../styles/acts.css"

const Acts = () => {
  const [users, setUsers] = useState([])

  const fetchData = () => {
    fetch("http://34.235.25.155:8082/lawTexts/notAccepted")
      .then(response => {
        return response.json()
      })
      .then(data => {
        setUsers(data)
      })
  }

  const accept = (id) => {
      const token = sessionStorage.getItem('token');
      const username = sessionStorage.getItem('username');
      fetch('http://34.235.25.155:8082/lawTexts/accept/'+id+'', {
         method: 'POST',
         headers: {
           'Content-Type': 'application/json'
         },
         body: JSON.stringify({username, token})
       })
       fetchData();
   }

   const reject = (id) => {
         const token = sessionStorage.getItem('token');
         const username = sessionStorage.getItem('username');
         fetch('http://34.235.25.155:8082/lawTexts/reject/'+id+'', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({username, token})
          })
          fetchData();
   }
  return (
    <div class="akty">
      {users.length > 0 && (
        <ul class="ak">
          {users.map(user => (
            <li class="akt" key={user.id}> ID: {user.id} Nazwa: {user.name} Data dodania: {user.uploadDate}
                <div class="button"> <button class="accept" onClick={() => {accept(user.id)}}>Akceptuj</button>
                <button class="accept" onClick={() => {reject(user.id)}}>Odrzuć</button></div> </li>
          ))}
        </ul>
      )}
      <button className="data" onClick={fetchData}>Pokaż niezakceptowane akty</button>
    </div>
  )
}

export default Acts