export default function TaskItem({
  task,
  onSelectTask,
  setIsCreatingTask
}) {
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
      <span>{task.title}</span>

      <span>›</span>
    </div>
  );
}