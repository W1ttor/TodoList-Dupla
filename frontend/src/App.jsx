import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './styles/index.css'

function App() {
  const [count, setCount] = useState(0)

  return (
  <div className="p-10 bg-green-500 text-white text-4xl font-bold">
    Site Funcionando! 🚀
  </div>
)
}

export default App
