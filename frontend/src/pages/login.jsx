import Input from "../components/Input";
import Button from "../components/Button";

export default function Login() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-96">
        
        <h1 className="text-2xl font-bold mb-6 text-center">
          Bem-vindo 👋
        </h1>

        <div className="flex flex-col gap-4">
          <Input label="Email" type="email" placeholder="seuemail@email.com" />
          <Input label="Senha" type="password" placeholder="••••••••" />
        </div>

        <Button className="w-full mt-6">
          Entrar
        </Button>

        <p className="text-sm mt-4 text-center">
          Não possui conta?{" "}
          <a className="text-blue-600 cursor-pointer">Cadastre-se</a>
        </p>

      </div>
    </div>
  );
}

