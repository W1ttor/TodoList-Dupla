import { useState } from "react";
import stickyNotes from "../../data/stickyNotes";

import StickyNoteCard from "./StickyNoteCard";
import StickyNoteModal from "./StickyNoteModal";
import Modal from "../layout/Modal";
import { AnimatePresence } from "framer-motion";

export default function StickyWallDashboard() {
  const [notes, setNotes] = useState(stickyNotes);

  const [showNoteModal, setShowNoteModal] = useState(false);
  const [selectedNote, setSelectedNote] = useState(null);

  const [showDeleteModal, setShowDeleteModal] = useState(false);

  function handleCreate() {
    setSelectedNote(null);
    setShowNoteModal(true);
  }

  function handleEdit(note) {
    setSelectedNote(note);
    setShowNoteModal(true);
  }

  function handleDelete(note) {
    setSelectedNote(note);
    setShowDeleteModal(true);
  }

  function confirmDelete() {
    setNotes(notes.filter(note => note.id !== selectedNote.id));

    setShowDeleteModal(false);
    setSelectedNote(null);
  }

  function saveNote(noteData) {
    if (selectedNote) {
      setNotes(
        notes.map(note =>
          note.id === selectedNote.id
            ? { ...note, ...noteData }
            : note
        )
      );
    } else {
      const newNote = {
        id: Date.now(),
        ...noteData
      };

      setNotes([...notes, newNote]);
    }

    setShowNoteModal(false);
    setSelectedNote(null);
  }

  return (
    <>
      <div className="bg-slate-800/40 border border-slate-600 rounded p-6">

        <div className="grid grid-cols-3 gap-6">

          <AnimatePresence>
          {notes.map(note => (
            <StickyNoteCard
              key={note.id}
              note={note}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          ))}
          </AnimatePresence>

          <button
            onClick={handleCreate}
            className="
              bg-slate-700/40
              border
              border-slate-500
              rounded-lg
              min-h-[220px]
              flex
              items-center
              justify-center
              text-5xl
              text-slate-300
              hover:bg-slate-700/60
              transition
            "
          >
            +
          </button>

        </div>
      </div>

      {showNoteModal && (
        <StickyNoteModal
          note={selectedNote}
          onClose={() => {
            setShowNoteModal(false);
            setSelectedNote(null);
          }}
          onSave={saveNote}
        />
      )}

      {showDeleteModal && (
        <Modal
          title="Excluir nota"
          message="Deseja realmente excluir esta nota?"
          confirmText="Excluir"
          cancelText="Cancelar"
          onConfirm={confirmDelete}
          onCancel={() => setShowDeleteModal(false)}
        />
      )}
    </>
  );
}