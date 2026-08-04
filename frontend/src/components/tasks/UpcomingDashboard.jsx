import { useTasks } from "../../context/TaskContext";
import TodayView from "./TodayView";
import { getTaskSection } from "../../utils/taskUtils";

export default function UpcomingDashboard({
  selectedTask,
  setSelectedTask,
  isCreatingTask,
  setIsCreatingTask,
  setCreationMode
}) {

  const { tasks } = useTasks();

  const todayTasks = tasks.filter(
    task => getTaskSection(task) === "today"
  );

  const tomorrowTasks = tasks.filter(
    task => getTaskSection(task) === "tomorrow"
  );

  const weekTasks = tasks.filter(
    task => getTaskSection(task) === "week"
  );

  function handleSelectTask(task) {
    setIsCreatingTask(false);
    setSelectedTask(task);
  }

  return (
    <div className="space-y-6">

      <TodayView
        title="Today"
        tasks={todayTasks}
        onSelectTask={handleSelectTask}
        setSelectedTask={setSelectedTask}
        setIsCreatingTask={setIsCreatingTask}
        setCreationMode={setCreationMode}
      />

      <div className="grid grid-cols-2 gap-6">

        <TodayView
          title="Tomorrow"
          tasks={tomorrowTasks}
          onSelectTask={handleSelectTask}
          setSelectedTask={setSelectedTask}
          setIsCreatingTask={setIsCreatingTask}
          setCreationMode={setCreationMode}
        />

        <TodayView
          title="This Week"
          tasks={weekTasks}
          onSelectTask={handleSelectTask}
          setSelectedTask={setSelectedTask}
          setIsCreatingTask={setIsCreatingTask}
          setCreationMode={setCreationMode}
        />

      </div>

    </div>
  );
}