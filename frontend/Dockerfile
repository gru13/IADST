# frontend/Dockerfile

# IMPORTANT: Build context must be the 'frontend' directory containing package.json
# Example: docker build -t my-frontend -f frontend/Dockerfile frontend
# Or use docker-compose with build.context: ./frontend

# Use official Node.js image as the base image
FROM node:16-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code into the container
COPY . .

# Build the React app for production
RUN npm run build

# Expose the port the app runs on (default React port)
EXPOSE 3000

# Serve the React app using a simple static file server
# You can use any static file server like `serve` or `http-server`
RUN npm install -g serve

CMD ["serve", "-s", "build", "-l", "3000"]
