import { useTasks } from "../../context/TaskContext";
import TodayView from "./TodayView";

export default function UpcomingDashboard() {
  const { tasks } = useTasks();

  const todayTasks = tasks.filter(task => task.section === "today");
  const tomorrowTasks = tasks.filter(task => task.section === "tomorrow");
  const weekTasks = tasks.filter(task => task.section === "week");

  return (
    <div className="space-y-6">
      <TodayView title="Today" tasks={todayTasks} />

      <div className="grid grid-cols-2 gap-6">
        <TodayView title="Tomorrow" tasks={tomorrowTasks} />
        <TodayView title="This Week" tasks={weekTasks} />
      </div>
    </div>
  );
}