import stickyNotes from "../../data/stickyNotes";

export default function StickyWallDashboard() {
  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded p-6">
      <div className="grid grid-cols-3 gap-6">

        {stickyNotes.map(note => (
          <div
            key={note.id}
            className={`${note.color} rounded-lg shadow-md p-5 min-h-[220px] text-gray-800`}
          >
            <h3 className="font-bold text-xl mb-3">
              {note.title}
            </h3>

            <ul className="space-y-1 text-sm">
              {note.content.map((item, index) => (
                <li key={index}>- {item}</li>
              ))}
            </ul>
          </div>
        ))}

        {/* CARD ADD */}
        <button className="bg-slate-700/40 border border-slate-500 rounded-lg min-h-[220px] flex items-center justify-center text-5xl text-slate-300 hover:bg-slate-700/60 transition">
          +
        </button>

      </div>
    </div>
  );
}