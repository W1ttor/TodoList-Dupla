import { useTasks } from "../../context/TaskContext";
import { CalendarDays, Check } from "lucide-react";

export default function TaskItem({
  task,
  onSelectTask,
  setIsCreatingTask,
  selected,
  onToggleSelect
}) {

  const { lists } = useTasks();

  const currentList = lists.find(
    listItem => listItem.id === task.list
  );

  const priorityColor = {
    Low: "bg-green-500",
    Medium: "bg-yellow-500",
    High: "bg-red-500"
  };

  function formatDate(date) {
    if (!date) return "";

    const [year, month, day] = date.split("-");

    return `${day}-${month}-${year.slice(-2)}`;
  }

  return (
    <div
      className={`
        bg-slate-800
        rounded-xl
        p-5
        border
        border-slate-600
        flex
        items-center
        gap-4
        hover:border-blue-500
        hover:-translate-y-1
        duration-200
        shadow-lg
        hover:bg-slate-700/60
        transition
        cursor-pointer
        ${selected ? "border-blue-500 bg-slate-700/60" : ""}
      `}
    >

      {/* CHECKBOX */}
      <input
        type="checkbox"
        checked={selected}
        onChange={(e) => {
          e.stopPropagation();
          onToggleSelect?.(task.id);
        }}
        onClick={(e) => e.stopPropagation()}
        className="
          w-4
          h-4
          shrink-0
          accent-blue-500
          cursor-pointer
        "
      />

      {/* CONTEÚDO */}
      <div
        className="flex-1"
        onClick={() => {
          setIsCreatingTask?.(false);
          onSelectTask?.(task);
        }}
      >

        <h3 className="font-semibold text-white">
          {task.title}
        </h3>

        <div className="flex items-center gap-3 mt-2 text-xs">

          {/* PRIORIDADE */}
          <span
            className={`
              ${priorityColor[task.priority]}
              px-2
              py-1
              rounded
              text-slate-900
              font-medium
            `}
          >
            {task.priority}
          </span>

          {/* DATA */}
          {task.dueDate && (
            <span className="flex items-center gap-1 text-slate-400">
              <CalendarDays
                size={13}
                strokeWidth={2}
              />

              {formatDate(task.dueDate)}
            </span>
          )}

          {/* LISTA */}
          <span className="flex items-center gap-1.5 text-slate-300">

            <span
              className={`
                w-2.5
                h-2.5
                rounded-full
                ${currentList?.color || "bg-slate-500"}
              `}
            />

            {currentList?.label || "No List"}

          </span>

        </div>

      </div>

      {/* STATUS */}
      <span
        onClick={(e) => e.stopPropagation()}
        className={`
          flex
          items-center
          justify-center
          w-6
          h-6
          rounded-full
          transition
          ${
            task.completed
              ? "text-green-400"
              : "text-slate-500"
          }
        `}
      >
        {task.completed ? (
          <Check
            size={18}
            strokeWidth={2.5}
          />
        ) : (
          <span className="text-lg leading-none">
            ›
          </span>
        )}
      </span>
    </div>
  );
}