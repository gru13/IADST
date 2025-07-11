import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'



// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    allowedHosts: ["5173-gru13-iadst-t0gmxcpaxyy.ws-us120.gitpod.io"]
  },
})
