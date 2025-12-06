import React, { useState } from "react";
import Input from "../components/Input";
import Button from "../components/Button";
import logo from "../assets/imgs/gemini.png";
import abstrato from "../assets/imgs/abstrato.jpg";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [isRegistering, setIsRegistering] = useState(false);

  const [name, setName] = useState(""); // <-- NOVO estado do nome
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const navigate = useNavigate();

  const handleRegisterClick = () => {
    setIsRegistering(!isRegistering);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = {
      name: name,                // <-- Enviando nome ao backend
      email: email,
      password: password,
      confirmPassword: confirmPassword,
    };

    console.log("Enviando dados:", userData);

    try {
      const response = await fetch("http://localhost:8080/api/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        navigate("/home");
      } else {
        alert("Erro ao cadastrar!");
      }
    } catch (error) {
      console.error("Erro no cadastro:", error);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      <div className="flex w-full max-w-2xl bg-white shadow-xl rounded-2xl overflow-hidden">

        {/* LADO ESQUERDO */}
        <div className="w-1/2 bg-black text-white p-0 relative overflow-hidden">
          <img src={abstrato} alt="abstrato" className="w-full h-full object-cover" />
          <img src={logo} alt="logo" className="w-32 absolute top-4 left-4 z-10" />
        </div>

        {/* LADO DIREITO */}
        <div className="w-1/2 p-12 flex flex-col justify-center">
          <h2 className="text-3xl font-bold mb-8">
            {isRegistering ? "Cadastrar" : "Entrar"}
          </h2>

          <form onSubmit={handleSubmit}>
            <div className="flex flex-col gap-4">

              {/* INPUT DE NOME – aparece só no cadastro */}
              {isRegistering && (
                <Input
                  label="Nome"
                  type="text"
                  placeholder="Seu nome completo"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                />
              )}

              <Input
                label="E-mail"
                type="email"
                placeholder="email@email.com"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />

              <Input
                label="Senha"
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />

              {isRegistering && (
                <Input
                  label="Confirmar Senha"
                  type="password"
                  placeholder="••••••••"
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                />
              )}
            </div>

            <Button className="w-full mt-6 bg-gray-400 hover:bg-gray-500 text-black font-semibold">
              {isRegistering ? "Cadastrar" : "Entrar"}
            </Button>

            <div className="flex items-center gap-2 mt-6">
              <div className="flex-1 h-px bg-gray-300" />
              <span className="text-gray-500 text-sm">ou</span>
              <div className="flex-1 h-px bg-gray-300" />
            </div>

            <div className="flex gap-4 mt-4">
              <button className="flex-1 py-2 border rounded-md">Google</button>
              <button className="flex-1 py-2 border rounded-md">Facebook</button>
            </div>

            <p className="text-sm mt-6 text-center">
              {isRegistering ? (
                <span
                  onClick={handleRegisterClick}
                  className="text-gray-600 cursor-pointer font-semibold"
                >
                  Já tem uma conta? Faça login
                </span>
              ) : (
                <span
                  onClick={handleRegisterClick}
                  className="text-gray-600 cursor-pointer font-semibold"
                >
                  Cadastre-se
                </span>
              )}
            </p>
          </form>
        </div>

      </div>
    </div>
  );
}
