export function getTaskSection(task) {

  if (!task.dueDate) return "today";

  const today = new Date();

  today.setHours(0, 0, 0, 0);

  const due = new Date(task.dueDate);

  due.setHours(0, 0, 0, 0);

  const diffDays = Math.floor(
    (due - today) / (1000 * 60 * 60 * 24)
  );

  if (diffDays <= 0) return "today";

  if (diffDays === 1) return "tomorrow";

  if (diffDays <= 7) return "week";

  return "future";

}