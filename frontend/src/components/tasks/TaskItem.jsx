import { useTasks } from "../../context/TaskContext";

export default function TaskItem({
  task,
  onSelectTask,
  setIsCreatingTask
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

  return (
    <div
      onClick={() => {
        setIsCreatingTask?.(false);
        onSelectTask?.(task);
      }}
      className="
        bg-slate-800
        rounded-xl
        p-5
        border
        border-slate-600
        flex
        items-center
        justify-between
        hover:border-blue-500
        hover:-translate-y-1
        duration-200
        shadow-lg
        hover:bg-slate-700/60
        transition
        cursor-pointer
      "
    >
      <div>

        <h3 className="font-semibold text-white">
          {task.title}
        </h3>

        <div className="flex items-center gap-2 mt-2 text-xs">

          <span className="bg-slate-600 px-2 py-1 rounded">

            {task.priority}

          </span>

          <span className="text-slate-400">

            {currentList?.label || task.list}

          </span>

        </div>

      </div>

      <span
        className={
          task.completed
            ? "text-green-400"
            : "text-slate-500"
        }
      >
        {task.completed ? "✔" : "›"}
      </span>
          </div>
  );
}