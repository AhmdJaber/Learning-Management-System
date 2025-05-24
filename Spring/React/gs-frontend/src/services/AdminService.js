import axios from "axios"

const RestURL = 'http://localhost:8080/admin/get'

export const allAdmins = () => axios.get(RestURL); 
