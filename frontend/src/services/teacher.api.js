// teacher.api.js
// API calls for /teacher endpoints
import apiClient from '../utils/apiClient';

export const fetchQuestions = () => apiClient.get('/teacher/questions');
export const createAssignment = (data) => apiClient.post('/teacher/assignments', data);
