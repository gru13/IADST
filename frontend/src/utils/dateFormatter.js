// dateFormatter.js
// Date formatting helpers
export const formatDate = (date) => {
  if (!date) return '';
  return new Date(date).toLocaleDateString();
};
