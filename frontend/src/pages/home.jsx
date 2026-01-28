import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Home() {
  // controla exibição do modal de logout 
  const [showModal, setShowModal] = useState(false);

  // controla a sidebar 
  const [sidebarOpen, setSidebarOpen] = useState(true);

  const navigate = useNavigate();

  // handlers de logout 
  const handleSignOutClick = () => {
    setShowModal(true);
  };

  const confirmSignOut = () => {
    setShowModal(false);
    navigate("/"); // retorna pro login
  };

  const cancelSignOut = () => {
    setShowModal(false);
  };


  return (
    <div className="flex min-h-screen bg-gray-100 text-gray-800">

    
      <aside
        className={`transition-all duration-300 flex flex-col
  ${sidebarOpen 
    ? "w-64 p-5 border-r border-slate-700 bg-gradient-to-br from-slate-700 via-gray-700 to-black" 
    : "w-0 p-0 border-none overflow-hidden"
  }`}
      >
        {/* ===== Top bar do menu (com título "Menu", hamburger e three-dots) ===== */}
        <div className="flex items-center justify-between mb-4">
          {/* esquerda: título "Menu"  */}
          <div className={`${sidebarOpen ? "flex items-center gap-3" : "hidden"}`}>
            <button
              aria-label="Abrir/fechar menu"
              onClick={() => setSidebarOpen(false)}
              className="p-1 rounded hover:bg-gray-100"
            >
              {/* Ícone hamburger (fechar) */}
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                <path d="M3 6h18M3 12h18M3 18h18" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" />
              </svg>
            </button>

            <span className="text-lg font-semibold">Menu</span>
          </div>

    
        </div>

        {/* ===== Search (campo) ===== */}
        <div className={`${sidebarOpen ? "" : "hidden"}`}>
          <input
            type="text"
            placeholder="Search"
            className="w-full border rounded px-3 py-2 text-sm"
          />
        </div>

        {/* ===== Tasks Section ===== */}
        <div className={`${sidebarOpen ? "mt-4" : "hidden"}`}>
          <h2 className="text-xs font-semibold text-gray-400 uppercase mb-2">
            Tasks
          </h2>

          <ul className="flex flex-col gap-2">
            <li className="flex justify-between items-center cursor-pointer hover:bg-gray-100 px-2 py-1 rounded">
              <span className="flex items-center gap-2">➤ Upcoming</span>
              <span className="text-xs bg-gray-200 px-2 rounded">12</span>
            </li>

            <li className="flex justify-between items-center cursor-pointer hover:bg-gray-100 px-2 py-1 rounded bg-gray-100 font-semibold">
              <span className="flex items-center gap-2">📄 Today</span>
              <span className="text-xs bg-gray-200 px-2 rounded">5</span>
            </li>

            <li className="flex items-center gap-2 cursor-pointer hover:bg-gray-100 px-2 py-1 rounded">
              📅 Calendar
            </li>

            <li className="flex items-center gap-2 cursor-pointer hover:bg-gray-100 px-2 py-1 rounded">
              🧱 Sticky Wall
            </li>
          </ul>
        </div>

        {/* ===== Lists ===== */}
        <div className={`${sidebarOpen ? "mt-6" : "hidden"}`}>
          <h2 className="text-xs font-semibold text-gray-400 uppercase mb-2">Lists</h2>
          <ul className="flex flex-col gap-2">
            <li className="flex items-center justify-between px-2 py-1 rounded hover:bg-gray-100 cursor-pointer">
              <div className="flex items-center gap-2">
                <span className="w-3 h-3 bg-red-400 rounded-full"></span>
                Personal
              </div>
              <span className="text-xs bg-gray-200 px-2 rounded">3</span>
            </li>

            <li className="flex items-center justify-between px-2 py-1 rounded hover:bg-gray-100 cursor-pointer">
              <div className="flex items-center gap-2">
                <span className="w-3 h-3 bg-blue-400 rounded-full"></span>
                Work
              </div>
              <span className="text-xs bg-gray-200 px-2 rounded">6</span>
            </li>

            <li className="flex items-center justify-between px-2 py-1 rounded hover:bg-gray-100 cursor-pointer">
              <div className="flex items-center gap-2">
                <span className="w-3 h-3 bg-yellow-400 rounded-full"></span>
                List 1
              </div>
              <span className="text-xs bg-gray-200 px-2 rounded">3</span>
            </li>

            <li className="text-blue-600 cursor-pointer px-2 py-1 hover:bg-gray-100 rounded">
              + Add New List
            </li>
          </ul>
        </div>

        {/* ===== Tags ===== */}
        <div className={`${sidebarOpen ? "mt-6" : "hidden"}`}>
          <h2 className="text-xs font-semibold text-gray-400 uppercase mb-2">Tags</h2>
          <div className="flex flex-wrap gap-2">
            <span className="bg-teal-100 text-teal-800 px-3 py-1 rounded text-xs">Tag 1</span>
            <span className="bg-red-100 text-red-800 px-3 py-1 rounded text-xs">Tag 2</span>
            <span className="bg-gray-200 text-gray-600 px-3 py-1 rounded text-xs cursor-pointer">+ Add Tag</span>
          </div>
        </div>

        {/* ===== Bottom actions (settings / sign out) - mantém o handler do sign out === */}
        <div className={`${sidebarOpen ? "mt-auto flex flex-col gap-2" : "hidden"}`}>
          <span className="cursor-pointer text-gray-600 hover:text-black">⚙ Settings</span>

          <span
            onClick={handleSignOutClick}
            className="cursor-pointer text-gray-600 hover:text-black"
          >
            ↩ Sign out
          </span>
        </div>
      </aside>

      {/* ===== MAIN CONTENT ===== */}
      <main className="flex-1 p-10 bg-gradient-to-br from-slate-700 via-gray-700 to-black min-h-screen text-slate-100">
      
        {/* Botão de abrir menu ajustado para Dark Mode */}
        {!sidebarOpen && (
          <button
            onClick={() => setSidebarOpen(true)}
            className="mb-4 p-2 rounded hover:bg-slate-600 transition-colors"
            aria-label="Abrir menu"
          >
            <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
              <path d="M3 6h18M3 12h18M3 18h18" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" />
            </svg>
          </button>
        )}

        {/* Top title (Today + badge) */}
        <div className="flex items-center gap-3 mb-8">
          <h1 className="text-4xl font-bold">Today</h1>
          {/* Badge com fundo mais suave para não "estourar" o branco */}
          <span className="text-xl bg-slate-600/50 px-3 py-1 rounded border border-slate-500">5</span>
        </div>

        {/* New Task Input - Agora escuro com borda sutil */}
        <div className="bg-slate-800/40 border border-slate-600 rounded p-3 mb-6 cursor-pointer hover:bg-slate-700/50 transition-all text-slate-400">
          + Add New Task
        </div>

        {/* Task List - Cards translúcidos e elegantes */}
        <ul className="flex flex-col gap-3">
          <li className="bg-slate-800/40 border border-slate-600 rounded p-3 hover:border-slate-400 transition-colors">Research content ideas</li>
          <li className="bg-slate-800/40 border border-slate-600 rounded p-3 hover:border-slate-400 transition-colors">Create a database of guest authors</li>
          <li className="bg-slate-800/40 border border-slate-600 rounded p-3 hover:border-slate-400 transition-colors">Renew driver's license</li>
          <li className="bg-slate-800/40 border border-slate-600 rounded p-3 hover:border-slate-400 transition-colors">Consult accountant</li>
          <li className="bg-slate-800/40 border border-slate-600 rounded p-3 hover:border-slate-400 transition-colors">Print business card</li>
        </ul>
      </main>

      {/* sair da página inicial */}
      {showModal && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-80 text-center">
            <h2 className="text-lg font-semibold mb-4">Deseja realmente sair?</h2>

            <div className="flex gap-3 justify-center">
              <button onClick={confirmSignOut} className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg transition">Sair</button>
              <button onClick={cancelSignOut} className="bg-gray-300 hover:bg-gray-400 text-black px-4 py-2 rounded-lg transition">Cancelar</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
