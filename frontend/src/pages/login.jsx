import Input from "../components/Input";
import Button from "../components/Button";
import logo from "../assets/imgs/gemini.png";
import abstrato from "../assets/imgs/abstrato.jpg"

export default function Login() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">

      <div className="flex w-full max-w-2xl bg-white shadow-xl rounded-2xl overflow-hidden">

        {/* LADO ESQUERDO */}
        <div className="w-1/2 bg-black text-white p-0 relative overflow-hidden">
          <img
    src={abstrato}
    alt="abstrato"
    className="w-full h-full object-cover"
  />


                <img 
    src={logo}
    alt="logo"
    className="w-32 absolute top-4 left-4 z-10"
  />
        </div>

        {/* LADO DIREITO */}
        <div className="w-1/2 p-12 flex flex-col justify-center">

          <h2 className="text-3xl font-bold mb-8">
            Entrar
          </h2>

          <div className="flex flex-col gap-4">
            <Input 
              label="E-mail"
              type="email"
              placeholder="email@email.com"
            />

            <Input 
              label="Senha"
              type="password"
              placeholder="••••••••"
            />
          </div>

          <Button className="w-full mt-6 bg-gray-400 hover:bg-gray-500 text-black font-semibold">
            Entrar
          </Button>

          <div className="flex items-center gap-2 mt-6">
            <div className="flex-1 h-px bg-gray-300" />
            <span className="text-gray-500 text-sm">ou</span>
            <div className="flex-1 h-px bg-gray-300" />
          </div>

          {/* LOGIN SOCIAL */}
          <div className="flex gap-4 mt-4">
            <button className="flex-1 py-2 border rounded-md">Google</button>
            <button className="flex-1 py-2 border rounded-md">Facebook</button>
          </div>

          <p className="text-sm mt-6 text-center">
            Não tem uma conta?{" "}
            <a className="text-gray-600 cursor-pointer font-semibold">
              Cadastre-se
            </a>
          </p>

        </div>

      </div>
    </div>
  );
}
