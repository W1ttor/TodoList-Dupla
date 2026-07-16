import TaskItem from "./TaskItem";

export default function TodayView({
  title,
  tasks
}) {
  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded p-6">

      <h2 className="text-2xl font-semibold mb-4">
        {title}
      </h2>

      <button
        className="
          w-full
          text-left
          bg-slate-700/30
          border
          border-slate-600
          rounded
          p-3
          mb-4
          hover:bg-slate-700/50
          transition
        "
      >
        + Add New Task
      </button>

      <div className="flex flex-col gap-3">
        {tasks.length > 0 ? (
          tasks.map(task => (
            <TaskItem
              key={task.id}
              task={task}
            />
          ))
        ) : (
          <p className="text-slate-400">
            Nenhuma tarefa.
          </p>
        )}
      </div>

    </div>
  );
}