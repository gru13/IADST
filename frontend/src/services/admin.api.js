// admin.api.js
// API calls for /admin endpoints
import apiClient from '../utils/apiClient';

export const fetchTeachers = () => apiClient.get('/admin/teachers');
export const fetchStudents = () => apiClient.get('/admin/students');
export const fetchCourses = () => apiClient.get('/admin/courses');
