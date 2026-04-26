import { useTasks } from "../../context/TaskContext";
import TaskItem from "./TaskItem";

export default function TodayDashboard() {
  const { tasks } = useTasks();

  const todayTasks = tasks.filter(task => task.section === "today");

  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded p-6">

      <button className="w-full text-left bg-slate-700/30 border border-slate-600 rounded p-3 mb-4 hover:bg-slate-700/50 transition">
        + Add New Task
      </button>

      <div className="flex flex-col gap-3">
        {todayTasks.map(task => (
          <TaskItem key={task.id} task={task} />
        ))}
      </div>

    </div>
  );
}