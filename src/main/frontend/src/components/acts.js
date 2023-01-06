import React, { useState } from "react"

const Acts = () => {
  const [users, setUsers] = useState([])

  const fetchData = () => {
    fetch("http://localhost:8082/lawTexts/notAccepted")
      .then(response => {
        return response.json()
      })
      .then(data => {
        setUsers(data)
      })
  }

  return (
    <div>
      <button onClick={fetchData}>Pokaż niezakceptowane akty</button>
      {users.length > 0 && (
        <ul>
          {users.map(user => (
            <li key={user.id}> ID: {user.id} Nazwa: {user.name} Data dodania: {user.uploadDate} <button>Akceptuj</button> <button>Odrzuć</button> </li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default Acts