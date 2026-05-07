/* ==========================
   SIDEBAR
========================= */
export default function Sidebar({
  activeMenu,
  setActiveMenu,
  sidebarOpen,
  setSidebarOpen,
  handleSignOutClick
})

{
  const tasksMenu = [
    { id: "upcoming", label: "➤ Upcoming", count: 0 },
    { id: "today", label: "📄 Today", count: 0 },
    { id: "calendar", label: "📅 Calendar", count: 0 },
    { id: "sticky", label: "🧱 Sticky Wall", count: 0 }
  ];

  const listsMenu = [
    { id: "personal", label: "Personal", color: "bg-red-400", count: 0 },
    { id: "work", label: "Work", color: "bg-blue-400", count: 0 },
    { id: "list1", label: "List 1", color: "bg-yellow-400", count: 0 }
  ];

  return (
    <aside
      className={`transition-all duration-300 flex flex-col
      ${
        sidebarOpen
          ? "w-64 p-5 border-r border-slate-700 bg-gradient-to-br from-slate-700 via-gray-700 to-black"
          : "w-0 p-0 border-none overflow-hidden"
      }`}
    >
      {sidebarOpen && (
        <>
          {/* HEADER */}
          <div className="flex items-center justify-between mb-4">
            <div className="flex items-center gap-3">
              <button
                onClick={() => setSidebarOpen(false)}
                className="p-1 rounded hover:bg-slate-600 transition text-slate-200"
              >
                ☰
              </button>
              <span className="text-lg font-semibold text-slate-100">
                Menu
              </span>
            </div>
          </div>

          {/* SEARCH */}
          <input
            type="text"
            placeholder="Search"
            className="w-full border rounded px-3 py-2 text-sm mb-4"
          />

          {/* TASKS */}
          <h2 className="text-xs font-semibold text-gray-400 uppercase mb-2">
            Tasks
          </h2>

          <ul className="flex flex-col gap-2">
            {tasksMenu.map((item) => (
              <li
                key={item.id}
                onClick={() => setActiveMenu(item.id)}
                className={`flex justify-between items-center cursor-pointer px-2 py-1 rounded transition-all duration-200
                ${
                  activeMenu === item.id
                    ? "bg-gray-100 text-black font-semibold"
                    : "hover:bg-gray-100 hover:text-black text-slate-200"
                }`}
              >
                <span>{item.label}</span>

                {item.count > 0 && (
                  <span className="text-xs bg-gray-200 px-2 rounded">
                    {item.count}
                  </span>
                )}
              </li>
            ))}
          </ul>

          {/* LISTS */}
          <h2 className="text-xs font-semibold text-gray-400 uppercase mt-6 mb-2">
            Lists
          </h2>

          <ul className="flex flex-col gap-2">
            {listsMenu.map((item) => (
              <li
                key={item.id}
                onClick={() => setActiveMenu(item.id)}
                className={`flex justify-between items-center cursor-pointer px-2 py-1 rounded transition-all duration-200
                ${
                  activeMenu === item.id
                    ? "bg-gray-100 text-black font-semibold"
                    : "hover:bg-gray-100 hover:text-black text-slate-200"
                }`}
              >
                <div className="flex items-center gap-2">
                  <span className={`w-3 h-3 rounded-full ${item.color}`} />
                  {item.label}
                </div>

                {item.count > 0 && (
                  <span className="text-xs bg-gray-200 px-2 rounded">
                    {item.count}
                  </span>
                )}
              </li>
            ))}

            {/* ADD NEW LIST */}
            <li className="text-blue-400 cursor-pointer px-2 py-1 hover:bg-gray-100 hover:text-black rounded transition">
              + Add New List
            </li>
          </ul>

          {/* TAGS */}
          <div className="mt-6">
            <h2 className="text-xs font-semibold text-gray-400 uppercase mb-2">
              Tags
            </h2>

            <div className="flex flex-wrap gap-2">
              <span className="bg-teal-100 text-teal-800 px-3 py-1 rounded text-xs">
                Tag 1
              </span>
              <span className="bg-red-100 text-red-800 px-3 py-1 rounded text-xs">
                Tag 2
              </span>
              <span className="bg-gray-200 text-gray-600 px-3 py-1 rounded text-xs cursor-pointer">
                + Add Tag
              </span>
            </div>
          </div>

          {/* FOOTER */}
          <div className="mt-auto flex flex-col gap-2 pt-6">
            <span className="cursor-pointer text-slate-300 hover:text-white transition">
              ⚙ Settings
            </span>

            <span
              onClick={handleSignOutClick}
              className="cursor-pointer text-slate-300 hover:text-red-400 transition"
            >
              ↩ Sign out
            </span>
          </div>
        </>
      )}
    </aside>
  );
}
