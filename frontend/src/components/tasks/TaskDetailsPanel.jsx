export default function TaskDetailsPanel({
  task,
  isCreatingTask,
  setSelectedTask,
  setIsCreatingTask
}) {

  if (!task && !isCreatingTask) {
    return (
      <div className="bg-slate-800/40 border border-slate-600 rounded p-6">
        <p className="text-slate-400">
          Selecione uma tarefa.
        </p>
      </div>
    );
  }

  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded p-6 h-full">

      <div className="flex items-center justify-between mb-6">

        <h2 className="text-2xl font-bold">
          {isCreatingTask
            ? "Nova Task"
            : "Editar Task"}
        </h2>

        <button
          onClick={() => {
            setSelectedTask(null);
            setIsCreatingTask(false);
          }}
          className="
            text-slate-400
            hover:text-white
            transition
            text-xl
          "
        >
          ✕
        </button>

      </div>

      <div className="space-y-5">

        <div>
          <p className="text-slate-400 text-sm mb-1">
            Description
          </p>

          <p>
            {isCreatingTask
              ? "-"
              : task?.description || "-"}
          </p>
        </div>

        <div>
          <p className="text-slate-400 text-sm mb-1">
            List
          </p>

          <p>
            {isCreatingTask
              ? "-"
              : task?.list || "-"}
          </p>
        </div>

        <div>
          <p className="text-slate-400 text-sm mb-1">
            Due Date
          </p>

          <p>
            {isCreatingTask
              ? "-"
              : task?.dueDate || "-"}
          </p>
        </div>

        <div>
          <p className="text-slate-400 text-sm mb-1">
            Tags
          </p>

          <p>
            {isCreatingTask
              ? "-"
              : task?.tags?.join(", ") || "-"}
          </p>
        </div>

      </div>

    </div>
  );
}