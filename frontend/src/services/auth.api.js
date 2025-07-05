// auth.api.js
// API calls for /auth endpoints
import apiClient from '../utils/apiClient';

export const login = (credentials) => apiClient.post('/auth/login', credentials);
export const logout = () => apiClient.post('/auth/logout');
