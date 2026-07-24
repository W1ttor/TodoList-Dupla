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



  return (
    <div
      onClick={() => {
        setIsCreatingTask?.(false);
        onSelectTask?.(task);
      }}
      className="
        bg-slate-700/40
        rounded
        p-3
        border
        border-slate-600
        flex
        items-center
        justify-between
        hover:bg-slate-700/60
        transition
        cursor-pointer
      "
    >
      <div>

        <h3 className="font-medium">
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