import { useTasks } from "../../context/TaskContext";
import { useState } from "react";
import UpcomingDashboard from "./UpcomingDashboard";
import TodayDashboard from "./TodayDashboard";
import StickyWallDashboard from "./StickyWallDashboard";
import CalendarDashboard from "./CalendarDashboard";
import ListsDashboard from "./ListsDashboard";
import TaskDetailsPanel from "./TaskDetailsPanel";
import Dashboard from "./Dashboard";
import PriorityDashboard from "./PriorityDashboard";

export default function TaskSection({
  sidebarOpen,
  setSidebarOpen
}) {
  const {
    activeMenu,
    counts,
    titles,
    lists
  } = useTasks();


const [selectedTask, setSelectedTask] = useState(null);

const [isCreatingTask, setIsCreatingTask] = useState(false);

const [creationMode, setCreationMode] = useState("default");


console.log("ACTIVE MENU:", activeMenu);
console.log("SELECTED TASK:", selectedTask);
console.log("CREATING TASK:", isCreatingTask);

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
    {activeMenu === "dashboard" && (
        <Dashboard />
      )}

    {activeMenu === "upcoming" && (

  <div
    className={
      (selectedTask || isCreatingTask)
        ? "grid grid-cols-[2fr_430px] gap-8"
        : ""
    }
  >

    <UpcomingDashboard
  selectedTask={selectedTask}
  setSelectedTask={setSelectedTask}
  isCreatingTask={isCreatingTask}
  setIsCreatingTask={setIsCreatingTask}
  setCreationMode={setCreationMode}
/>

    {(selectedTask || isCreatingTask) && (
      <TaskDetailsPanel
        task={selectedTask}
        isCreatingTask={isCreatingTask}
        setSelectedTask={setSelectedTask}
        setIsCreatingTask={setIsCreatingTask}
        creationMode={creationMode}
      />
    )}

  </div>

)}

      {activeMenu === "today" && (
        <div
  className={
  (selectedTask || isCreatingTask)
    ? "grid grid-cols-[2fr_430px] gap-8"
    : ""
}
>

  <TodayDashboard
  selectedTask={selectedTask}
  setSelectedTask={setSelectedTask}
  isCreatingTask={isCreatingTask}
  setIsCreatingTask={setIsCreatingTask}
  setCreationMode={setCreationMode}
/>

  {(selectedTask || isCreatingTask) && (
  <TaskDetailsPanel
    task={selectedTask}
    isCreatingTask={isCreatingTask}
    creationMode={creationMode}
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
        <div
          className={
            (selectedTask || isCreatingTask)
              ? "grid grid-cols-[2fr_430px] gap-8"
              : ""
          }
        >
          <CalendarDashboard
            setSelectedTask={setSelectedTask}
            setIsCreatingTask={setIsCreatingTask}
          />

          {(selectedTask || isCreatingTask) && (
            <TaskDetailsPanel
              task={selectedTask}
              isCreatingTask={isCreatingTask}
              creationMode={creationMode}
              setSelectedTask={setSelectedTask}
              setIsCreatingTask={setIsCreatingTask}
            />
          )}
        </div>
      )}

      {/* LISTAS */}

      {lists.some(list => list.id === activeMenu) && (
        <div
  className={
    (selectedTask || isCreatingTask)
      ? "grid grid-cols-[2fr_430px] gap-8"
      : ""
  }
>

    <ListsDashboard
      setSelectedTask={setSelectedTask}
      setIsCreatingTask={setIsCreatingTask}
      setCreationMode={setCreationMode}
    />

  {(selectedTask || isCreatingTask) && (
    <TaskDetailsPanel
      task={selectedTask}
      isCreatingTask={isCreatingTask}
      setSelectedTask={setSelectedTask}
      setIsCreatingTask={setIsCreatingTask}
      creationMode={creationMode}
    />
  )}

</div>
      )}


{/* ==========================
    PRIORIDADES
========================== */}

{activeMenu === "priority-low" && (

  <div
    className={
      (selectedTask || isCreatingTask)
        ? "grid grid-cols-[2fr_430px] gap-8"
        : ""
    }
  >

    <PriorityDashboard

      priority="Low"

      setSelectedTask={
        setSelectedTask
      }

      setIsCreatingTask={
        setIsCreatingTask
      }

      setCreationMode={
        setCreationMode
      }


      defaultPriority="Low"

    />


    {(selectedTask || isCreatingTask) && (

      <TaskDetailsPanel

        task={selectedTask}

        isCreatingTask={
          isCreatingTask
        }

        setSelectedTask={
          setSelectedTask
        }

        setIsCreatingTask={
          setIsCreatingTask
        }

        creationMode={
          creationMode
        }

      />

    )}

  </div>

)}


{activeMenu === "priority-medium" && (

  <div
    className={
      (selectedTask || isCreatingTask)
        ? "grid grid-cols-[2fr_430px] gap-8"
        : ""
    }
  >

    <PriorityDashboard

      priority="Medium"

      setSelectedTask={
        setSelectedTask
      }

      setIsCreatingTask={
        setIsCreatingTask
      }

      setCreationMode={
        setCreationMode
      }
      defaultPriority="Medium"
    />


    {(selectedTask || isCreatingTask) && (

      <TaskDetailsPanel

        task={selectedTask}

        isCreatingTask={
          isCreatingTask
        }

        setSelectedTask={
          setSelectedTask
        }

        setIsCreatingTask={
          setIsCreatingTask
        }

        creationMode={
          creationMode
        }

      />

    )}

  </div>

)}


{activeMenu === "priority-high" && (

  <div
    className={
      (selectedTask || isCreatingTask)
        ? "grid grid-cols-[2fr_430px] gap-8"
        : ""
    }
  >

    <PriorityDashboard

      priority="High"

      setSelectedTask={
        setSelectedTask
      }

      setIsCreatingTask={
        setIsCreatingTask
      }

      setCreationMode={
        setCreationMode
      }

      defaultPriority="High"
    />


    {(selectedTask || isCreatingTask) && (

      <TaskDetailsPanel

        task={selectedTask}

        isCreatingTask={
          isCreatingTask
        }

        setSelectedTask={
          setSelectedTask
        }

        setIsCreatingTask={
          setIsCreatingTask
        }

        creationMode={
          creationMode
        }

      />

    )}

  </div>

)}

    </main>
  );
}