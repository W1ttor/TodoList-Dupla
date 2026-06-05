import { useState } from "react";

export default function StickyNoteModal({
  note,
  onClose,
  onSave
}) {
  const [title, setTitle] = useState(
    note?.title || ""
  );

  const [content, setContent] = useState(
    note?.content?.join("\n") || ""
  );

  const handleSubmit = () => {
    onSave({
      ...note,
      title,
      content: content
        .split("\n")
        .filter(item => item.trim())
    });
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">

      <div className="bg-slate-800 border border-slate-600 rounded-xl p-6 w-[500px]">

        <h2 className="text-2xl font-semibold text-white mb-5">

          {note
            ? "Editar Nota"
            : "Nova Nota"}

        </h2>

        <div className="flex flex-col gap-4">

          <input
            type="text"
            placeholder="Título"
            value={title}
            onChange={e => setTitle(e.target.value)}
            className="
              bg-slate-700
              border
              border-slate-600
              rounded
              p-3
              text-white
            "
          />

          <textarea
            rows="6"
            placeholder="Conteúdo"
            value={content}
            onChange={e => setContent(e.target.value)}
            className="
              bg-slate-700
              border
              border-slate-600
              rounded
              p-3
              text-white
            "
          />

          <div className="flex justify-end gap-3">

            <button
              onClick={onClose}
              className="px-4 py-2 rounded bg-slate-600"
            >
              Cancelar
            </button>

            <button
              onClick={handleSubmit}
              className="px-4 py-2 rounded bg-blue-500 text-white"
            >
              Salvar
            </button>

          </div>

        </div>

      </div>
    </div>
  );
}