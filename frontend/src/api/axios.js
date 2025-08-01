// src/api/axios.js
// Axios instance for API requests
import axios from 'axios';

const api = axios.create({
  baseURL:  'http://localhost:8080/api', // Backend API base URL
  timeout: 100000, // 100 seconds timeout
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add a response interceptor to handle backend error responses
api.interceptors.response.use(
  (response) => response,
  (error) => {
    let customError = {
      status: error.response?.status,
      message: 'An error occurred',
      errors: null,
    };
    if (error.response && error.response.data) {
      const data = error.response.data;
      // Validation errors (400)
      if (data.errors) {
        customError.message = 'Validation failed';
        customError.errors = data.errors;
      } else if (data.error) {
        // Duplicate, not found, or other known errors (409, 404, etc.)
        customError.message = data.message || data.error;
        customError.errors = data.errors || null;
      } else if (data.message) {
        customError.message = data.message;
      }
    } else if (error.message) {
      customError.message = error.message;
    }
    return Promise.reject(customError);
  }
);

export default api;
