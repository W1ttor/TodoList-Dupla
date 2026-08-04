import { useTasks } from "../../context/TaskContext";
import TaskItem from "./TaskItem";

export default function ListsDashboard({
  setSelectedTask,
  setIsCreatingTask,
  setCreationMode
}) {
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
        onClick={() => {
          setSelectedTask(null);
          setCreationMode("default");
          setIsCreatingTask(true);
        }}
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

      <div className="flex flex-col gap-3 p-4">

        {filteredTasks.map(task => (
          <TaskItem
            key={task.id}
            task={task}
            setIsCreatingTask={setIsCreatingTask}
            onSelectTask={setSelectedTask}
          />
        ))}

      </div>

    </div>
  );
}