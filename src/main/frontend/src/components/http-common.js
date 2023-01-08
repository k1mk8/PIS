import axios from "axios";

export default axios.create({
    baseURL: "http://34.235.25.155:8082",
    headers: {
        "Content-type": "application/json"
    }
});