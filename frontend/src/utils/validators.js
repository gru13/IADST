// validators.js
// Form validation rules
export const isRequired = (value) => value !== undefined && value !== null && value !== '';
export const isEmail = (value) => /.+@.+\..+/.test(value);
