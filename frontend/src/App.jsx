import { Routes, Route } from "react-router-dom";
import Login from "./pages/login.jsx";
import Home from "./pages/home.jsx";  // Importa a página inicial

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/home" element={<Home />} /> {/* Página inicial */}
    </Routes>
  );
}
