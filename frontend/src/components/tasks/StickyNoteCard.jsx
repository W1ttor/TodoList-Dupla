import { motion } from "framer-motion";
import { Pencil, Trash2 } from "lucide-react";

export default function StickyNoteCard({
  note,
  onEdit,
  onDelete
}) {
  return (
    <motion.div
      className={`
        ${note.color}
        relative
        rounded-lg
        shadow-md
        p-5
        min-h-[220px]
        text-gray-800
        overflow-hidden
        group
      `}
      initial={{
        opacity: 0,
        y: 30,
        scale: 0.95
      }}
      animate={{
        opacity: 1,
        y: 0,
        scale: 1
      }}
      exit={{
        opacity: 0,
        y: -20,
        scale: 0.9
      }}
      transition={{
        duration: 0.25
      }}
      whileHover={{
        y: -4
      }}
    >
      {/* Overlay */}
      <div
        className="
          absolute
          inset-0
          bg-black/20
          opacity-0
          group-hover:opacity-100
          transition-all
          duration-200
          rounded-lg
        "
      />

      {/* Botões */}
      <div
        className="
          absolute
          top-3
          right-3
          flex
          gap-2
          opacity-0
          group-hover:opacity-100
          transition-all
          duration-200
          z-10
        "
      >
        <button
          onClick={() => onEdit(note)}
          className="
            p-2
            rounded-lg
            bg-white/70
            backdrop-blur-sm
            hover:bg-white
            transition
            shadow-sm
          "
        >
          <Pencil size={16} />
        </button>

        <button
          onClick={() => onDelete(note)}
          className="
            p-2
            rounded-lg
            bg-white/70
            backdrop-blur-sm
            hover:bg-red-500
            hover:text-white
            transition
            shadow-sm
          "
        >
          <Trash2 size={16} />
        </button>
      </div>

      {/* Conteúdo */}
      <div className="relative z-0">
        <h3 className="font-bold text-xl mb-3">
          {note.title}
        </h3>

        <ul className="space-y-1 text-sm">
          {note.content.map((item, index) => (
            <li key={index}>
              • {item}
            </li>
          ))}
        </ul>
      </div>
    </motion.div>
  );
}