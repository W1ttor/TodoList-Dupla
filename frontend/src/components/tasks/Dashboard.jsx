import { useTasks } from "../../context/TaskContext";
import {
  AlertTriangle,
  Clock,
  CheckCircle2,
  ListTodo
} from "lucide-react";

export default function Dashboard() {

  const { tasks } = useTasks();

  const activeTasks = tasks.filter(
    task => !task.completed
  );

  const completedTasks = tasks.filter(
    task => task.completed
  );

  const today = new Date();

  const startOfToday = new Date();
  startOfToday.setHours(0, 0, 0, 0);

  const endOfToday = new Date();
  endOfToday.setHours(23, 59, 59, 999);

  const overdueTasks = activeTasks.filter(task => {

    if (!task.dueDate) {
      return false;
    }

    const due = new Date(`${task.dueDate}T${task.dueTime || "23:59"}`);

    return due < today;
  });

  const todayTasks = activeTasks.filter(task => {

    if (!task.dueDate) {
      return false;
    }

    const due = new Date(`${task.dueDate}T${task.dueTime || "23:59"}`);

    return (
      due >= startOfToday &&
      due <= endOfToday
    );
  });

  return (
    <div className="space-y-8">

      {/* RESUMO */}

      <div className="grid grid-cols-4 gap-5">

        <DashboardCard
          icon={<ListTodo size={22} />}
          title="Active Tasks"
          value={activeTasks.length}
        />

        <DashboardCard
          icon={<Clock size={22} />}
          title="Due Today"
          value={todayTasks.length}
        />

        <DashboardCard
          icon={<AlertTriangle size={22} />}
          title="Overdue"
          value={overdueTasks.length}
        />

        <DashboardCard
          icon={<CheckCircle2 size={22} />}
          title="Completed"
          value={completedTasks.length}
        />

      </div>


      {/* ATRASADAS */}

      <section className="bg-slate-800/40 border border-slate-600 rounded-xl p-6">

        <div className="flex items-center justify-between mb-5">

          <div>
            <h2 className="text-xl font-semibold">
              Overdue Tasks
            </h2>

            <p className="text-sm text-slate-400 mt-1">
              Tasks that have passed their deadline.
            </p>
          </div>

          <AlertTriangle
            size={22}
            className="text-red-400"
          />

        </div>

        {overdueTasks.length === 0 ? (

          <p className="text-slate-400">
            No overdue tasks.
          </p>

        ) : (

          <div className="space-y-3">

            {overdueTasks.map(task => (

              <div
                key={task.id}
                className="
                  flex
                  items-center
                  justify-between
                  bg-slate-800
                  border
                  border-red-500/20
                  rounded-lg
                  p-4
                "
              >

                <div>

                  <h3 className="font-medium text-white">
                    {task.title}
                  </h3>

                  <p className="text-xs text-slate-400 mt-1">
                    Due: {task.dueDate}
                    {task.dueTime && ` at ${task.dueTime}`}
                  </p>

                </div>

                <span className="text-xs text-red-400 font-medium">
                  Overdue
                </span>

              </div>

            ))}

          </div>

        )}

      </section>


      {/* HOJE */}

      <section className="bg-slate-800/40 border border-slate-600 rounded-xl p-6">

        <div className="flex items-center justify-between mb-5">

          <div>
            <h2 className="text-xl font-semibold">
              Due Today
            </h2>

            <p className="text-sm text-slate-400 mt-1">
              Tasks that need attention today.
            </p>
          </div>

          <Clock
            size={22}
            className="text-yellow-400"
          />

        </div>

        {todayTasks.length === 0 ? (

          <p className="text-slate-400">
            No tasks due today.
          </p>

        ) : (

          <div className="space-y-3">

            {todayTasks.map(task => (

              <div
                key={task.id}
                className="
                  flex
                  items-center
                  justify-between
                  bg-slate-800
                  border
                  border-slate-600
                  rounded-lg
                  p-4
                "
              >

                <div>

                  <h3 className="font-medium text-white">
                    {task.title}
                  </h3>

                  <p className="text-xs text-slate-400 mt-1">
                    {task.dueTime
                      ? `Due at ${task.dueTime}`
                      : "No specific time"
                    }
                  </p>

                </div>

                <span className="text-xs text-yellow-400 font-medium">
                  Today
                </span>

              </div>

            ))}

          </div>

        )}

      </section>

    </div>
  );
}


function DashboardCard({
  icon,
  title,
  value
}) {

  return (
    <div
      className="
        bg-slate-800/40
        border
        border-slate-600
        rounded-xl
        p-5
      "
    >

      <div className="flex items-center justify-between">

        <div className="text-slate-400">
          {icon}
        </div>

        <span className="text-3xl font-bold text-white">
          {value}
        </span>

      </div>

      <p className="text-sm text-slate-400 mt-4">
        {title}
      </p>

    </div>
  );
}