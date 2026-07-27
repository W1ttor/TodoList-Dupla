import { useState } from "react";

export default function TagModal({
  onClose,
  onSave
}) {

  const [tagName, setTagName] = useState("");

  function handleSave() {

    if (!tagName.trim()) return;

    onSave({
      id: Date.now(),
      label: tagName,
      color: "bg-cyan-300"
    });

  }

  return (
    <div className="fixed inset-0 bg-black/60 flex items-center justify-center z-50">

      <div className="bg-slate-800 border border-slate-700 rounded-xl w-96 p-6">

        <h2 className="text-xl font-semibold text-white mb-6">
          New Tag
        </h2>

        <input
          value={tagName}
          onChange={(e) => setTagName(e.target.value)}
          placeholder="Tag name"
          className="w-full bg-slate-700 border border-slate-600 rounded-lg p-3 outline-none focus:border-blue-500"
        />

        <div className="flex justify-end gap-3 mt-6">

          <button
            onClick={onClose}
            className="px-4 py-2 rounded-lg bg-slate-700 hover:bg-slate-600 transition"
          >
            Cancel
          </button>

          <button
            onClick={handleSave}
            className="px-4 py-2 rounded-lg bg-blue-600 hover:bg-blue-700 transition"
          >
            Save
          </button>

        </div>

      </div>

    </div>
  );
}