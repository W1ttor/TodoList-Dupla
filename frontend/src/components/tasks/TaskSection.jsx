import { useTasks } from "../../context/TaskContext";
import { useState } from "react";
import UpcomingDashboard from "./UpcomingDashboard";
import TodayDashboard from "./TodayDashboard";
import StickyWallDashboard from "./StickyWallDashboard";
import CalendarDashboard from "./CalendarDashboard";
import ListsDashboard from "./ListsDashboard";
import TaskDetailsPanel from "./TaskDetailsPanel";

export default function TaskSection({
  sidebarOpen,
  setSidebarOpen
}) {
  const {
    activeMenu,
    counts,
    titles
  } = useTasks();


const [selectedTask, setSelectedTask] = useState(null);

const [isCreatingTask, setIsCreatingTask] = useState(false);

  return (
    <main className="flex-1 p-10 bg-gradient-to-br from-slate-700 via-gray-700 to-black min-h-screen text-slate-100">

      {!sidebarOpen && (
        <button
          onClick={() => setSidebarOpen(true)}
          className="mb-4 p-2 rounded hover:bg-slate-600 transition text-white"
        >
          ☰
        </button>
      )}

      <div className="flex items-center gap-3 mb-8">
        <h1 className="text-4xl font-bold">
          {titles[activeMenu]}
        </h1>

        {counts[activeMenu] > 0 && (
          <span className="text-xl bg-slate-600/50 px-3 py-1 rounded border border-slate-500">
            {counts[activeMenu]}
          </span>
        )}
      </div>

      {/* DASHBOARDS PRINCIPAIS */}

    {activeMenu === "upcoming" && (

  <div
    className={
      (selectedTask || isCreatingTask)
        ? "grid grid-cols-[2fr_380px] gap-8"
        : ""
    }
  >

    <UpcomingDashboard
      selectedTask={selectedTask}
      setSelectedTask={setSelectedTask}
      isCreatingTask={isCreatingTask}
      setIsCreatingTask={setIsCreatingTask}
    />

    {(selectedTask || isCreatingTask) && (
      <TaskDetailsPanel
        task={selectedTask}
        isCreatingTask={isCreatingTask}
        setSelectedTask={setSelectedTask}
        setIsCreatingTask={setIsCreatingTask}
      />
    )}

  </div>

)}

      {activeMenu === "today" && (
        <div
  className={
  (selectedTask || isCreatingTask)
    ? "grid grid-cols-[2fr_380px] gap-8"
    : ""
}
>

  <TodayDashboard
    selectedTask={selectedTask}
    setSelectedTask={setSelectedTask}
    isCreatingTask={isCreatingTask}
    setIsCreatingTask={setIsCreatingTask}
  />

  {(selectedTask || isCreatingTask) && (
    <TaskDetailsPanel
        task={selectedTask}
        isCreatingTask={isCreatingTask}
        setSelectedTask={setSelectedTask}
        setIsCreatingTask={setIsCreatingTask}
    />
)}
</div>
      )}

      {activeMenu === "sticky" && (
        <StickyWallDashboard />
      )}

      {activeMenu === "calendar" && (
        <CalendarDashboard />
      )}

      {/* LISTAS */}

      {[
        "personal",
        "work",
        "list1"
      ].includes(activeMenu) && (
        <div
  className={
    (selectedTask || isCreatingTask)
      ? "grid grid-cols-[2fr_380px] gap-8"
      : ""
  }
>

  <ListsDashboard
    setSelectedTask={setSelectedTask}
    setIsCreatingTask={setIsCreatingTask}
  />

  {(selectedTask || isCreatingTask) && (
    <TaskDetailsPanel
      task={selectedTask}
      isCreatingTask={isCreatingTask}
      setSelectedTask={setSelectedTask}
      setIsCreatingTask={setIsCreatingTask}
    />
  )}

</div>
      )}

    </main>
  );
}