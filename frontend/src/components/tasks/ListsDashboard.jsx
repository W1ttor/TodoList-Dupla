import { useTasks } from "../../context/TaskContext";

export default function ListsDashboard() {

  const {
    tasks,
    activeMenu
  } = useTasks();

  const filteredTasks = tasks.filter(
    task => task.list === activeMenu
  );

  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded-xl overflow-hidden">

      <button
        className="
          w-full
          p-4
          text-left
          border-b
          border-slate-700
          hover:bg-slate-700/30
          transition
        "
      >
        + Add New Task
      </button>

      {filteredTasks.map(task => (
        <div
          key={task.id}
          className="
            flex
            items-center
            gap-4
            p-4
            border-b
            border-slate-700
            hover:bg-slate-700/20
          "
        >
          <input type="checkbox" />

          <span>
            {task.title}
          </span>
        </div>
      ))}
    </div>
  );
}