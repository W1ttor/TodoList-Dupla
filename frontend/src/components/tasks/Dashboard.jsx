import { useTasks } from "../../context/TaskContext";
import {
  AlertTriangle,
  Clock3,
  CheckCircle2,
  ListTodo,
  ArrowUpRight,
  Activity,
  CalendarDays
} from "lucide-react";

export default function Dashboard() {

  const { tasks } = useTasks();

  /* ==========================
     TASK DATA
  ========================== */

  const activeTasks = tasks.filter(
    task => !task.completed
  );

  const completedTasks = tasks.filter(
    task => task.completed
  );

  const now = new Date();

  const startOfToday = new Date();
  startOfToday.setHours(0, 0, 0, 0);

  const endOfToday = new Date();
  endOfToday.setHours(23, 59, 59, 999);


  const overdueTasks = activeTasks.filter(task => {

    if (!task.dueDate) {
      return false;
    }

    const due = new Date(
      `${task.dueDate}T${task.dueTime || "23:59"}`
    );

    return due < now;
  });


  const todayTasks = activeTasks.filter(task => {

    if (!task.dueDate) {
      return false;
    }

    const due = new Date(
      `${task.dueDate}T${task.dueTime || "23:59"}`
    );

    return (
      due >= startOfToday &&
      due <= endOfToday
    );
  });


  /* ==========================
     PRODUCTIVITY
  ========================== */

  const totalTasks = tasks.length;

  const completionRate =
    totalTasks > 0
      ? Math.round(
          (completedTasks.length / totalTasks) * 100
        )
      : 0;


  /* ==========================
     DATE
  ========================== */

  const currentDate = new Intl.DateTimeFormat(
    "en-US",
    {
      weekday: "long",
      month: "long",
      day: "numeric",
      year: "numeric"
    }
  ).format(now);


  return (

    <div className="space-y-6">

      {/* =====================================
          HEADER
      ===================================== */}

      <div className="flex items-end justify-between">

        <div>

          <div className="flex items-center gap-2 mb-2">

            <span
              className="
                w-1.5
                h-1.5
                rounded-full
                bg-cyan-400
                shadow-[0_0_10px_rgba(34,211,238,0.9)]
              "
            />

            <span
              className="
                text-[11px]
                uppercase
                tracking-[0.25em]
                text-cyan-400
                font-semibold
              "
            >
              Overview
            </span>

          </div>

       

          <p className="text-sm text-slate-400 mt-1">
            Your productivity at a glance.
          </p>

        </div>


        <div
          className="
            flex
            items-center
            gap-2
            text-xs
            text-slate-400
          "
        >

          <CalendarDays
            size={14}
            className="text-cyan-400"
          />

          {currentDate}

        </div>

      </div>


      {/* =====================================
          STAT CARDS
      ===================================== */}

      <div className="grid grid-cols-4 gap-4">

        <StatCard
          title="Active"
          value={activeTasks.length}
          icon={<ListTodo size={19} />}
          accent="blue"
        />

        <StatCard
          title="Today"
          value={todayTasks.length}
          icon={<Clock3 size={19} />}
          accent="cyan"
        />

        <StatCard
          title="Overdue"
          value={overdueTasks.length}
          icon={<AlertTriangle size={19} />}
          accent="red"
        />

        <StatCard
          title="Completed"
          value={completedTasks.length}
          icon={<CheckCircle2 size={19} />}
          accent="green"
        />

      </div>


      {/* =====================================
          MAIN AREA
      ===================================== */}

      <div className="grid grid-cols-[1.1fr_0.9fr] gap-4">


        {/* =================================
            PRODUCTIVITY
        ================================= */}

        <section
          className="
            group
            relative
            overflow-hidden
            rounded-2xl
            border
            border-slate-600
            bg-slate-800/65
            p-6
            transition-all
            duration-300
            hover:border-cyan-400/40
            hover:bg-slate-800/80
            hover:-translate-y-0.5
            hover:shadow-[0_10px_35px_rgba(0,0,0,0.25)]
          "
        >

          {/* Hover glow */}

          <div
            className="
              pointer-events-none
              absolute
              -right-20
              -top-20
              w-48
              h-48
              rounded-full
              bg-cyan-400/5
              blur-3xl
              opacity-60
              group-hover:opacity-100
              transition-opacity
              duration-300
            "
          />

          <div className="relative">

            <div className="flex items-start justify-between">

              <div>

                <div className="flex items-center gap-2">

                  <Activity
                    size={17}
                    className="
                      text-cyan-400
                      transition-transform
                      duration-300
                      group-hover:scale-110
                    "
                  />

                  <h3 className="font-semibold text-white">
                    Productivity
                  </h3>

                </div>

                <p className="text-xs text-slate-400 mt-1">
                  Overall completion
                </p>

              </div>


              <span
                className="
                  text-2xl
                  font-bold
                  text-cyan-400
                  transition-all
                  duration-300
                  group-hover:text-cyan-300
                  group-hover:drop-shadow-[0_0_8px_rgba(34,211,238,0.35)]
                "
              >
                {completionRate}%
              </span>

            </div>


            {/* PROGRESS */}

            <div className="mt-7">

              <div
                className="
                  h-2.5
                  w-full
                  rounded-full
                  bg-slate-700
                  border
                  border-slate-600/60
                  overflow-hidden
                "
              >

                <div
                  className="
                    h-full
                    rounded-full
                    bg-gradient-to-r
                    from-blue-500
                    via-cyan-400
                    to-cyan-300
                    transition-all
                    duration-700
                    shadow-[0_0_12px_rgba(34,211,238,0.35)]
                  "
                  style={{
                    width: `${completionRate}%`
                  }}
                />

              </div>

            </div>


            <div
              className="
                flex
                justify-between
                mt-3
                text-[11px]
                text-slate-400
              "
            >

              <span>
                {completedTasks.length} completed
              </span>

              <span>
                {totalTasks} total
              </span>

            </div>

          </div>

        </section>


        {/* =================================
            TODAY
        ================================= */}

        <section
          className="
            group
            relative
            overflow-hidden
            rounded-2xl
            border
            border-slate-600
            bg-slate-800/65
            p-6
            transition-all
            duration-300
            hover:border-cyan-400/40
            hover:bg-slate-800/80
            hover:-translate-y-0.5
            hover:shadow-[0_10px_35px_rgba(0,0,0,0.25)]
          "
        >

          <div className="flex items-center justify-between mb-5">

            <div>

              <div className="flex items-center gap-2">

                <Clock3
                  size={17}
                  className="
                    text-cyan-400
                    transition-transform
                    duration-300
                    group-hover:scale-110
                  "
                />

                <h3 className="font-semibold text-white">
                  Today's Tasks
                </h3>

              </div>

              <p className="text-xs text-slate-400 mt-1">
                Tasks scheduled for today
              </p>

            </div>


            <span
              className="
                min-w-7
                h-7
                px-2
                flex
                items-center
                justify-center
                rounded-lg
                bg-slate-700
                border
                border-slate-600
                text-xs
                font-semibold
                text-slate-200
              "
            >
              {todayTasks.length}
            </span>

          </div>


          {todayTasks.length === 0 ? (

            <div
              className="
                h-24
                flex
                items-center
                justify-center
                rounded-xl
                border
                border-dashed
                border-slate-700
                bg-slate-900/20
                text-xs
                text-slate-500
              "
            >

              No tasks scheduled for today.

            </div>

          ) : (

            <div className="space-y-2">

              {todayTasks.slice(0, 4).map(task => (

                <TaskItem
                  key={task.id}
                  task={task}
                />

              ))}

            </div>

          )}

        </section>

      </div>


      {/* =====================================
          OVERDUE
      ===================================== */}

      {overdueTasks.length > 0 && (

        <section
          className="
            group
            rounded-2xl
            border
            border-red-400/30
            bg-slate-800/65
            overflow-hidden
            transition-all
            duration-300
            hover:border-red-400/50
            hover:bg-slate-800/80
            hover:shadow-[0_10px_30px_rgba(248,113,113,0.08)]
          "
        >

          <div
            className="
              flex
              items-center
              justify-between
              px-6
              py-4
              border-b
              border-red-400/20
            "
          >

            <div className="flex items-center gap-3">

              <div
                className="
                  flex
                  items-center
                  justify-center
                  w-8
                  h-8
                  rounded-lg
                  bg-red-400/10
                  border
                  border-red-400/20
                "
              >

                <AlertTriangle
                  size={17}
                  className="text-red-400"
                />

              </div>

              <div>

                <h3 className="text-sm font-semibold text-white">
                  Overdue Tasks
                </h3>

                <p className="text-xs text-slate-400">
                  Requires your attention
                </p>

              </div>

            </div>


            <span
              className="
                text-xs
                font-semibold
                text-red-300
                bg-red-400/10
                border
                border-red-400/20
                px-2.5
                py-1
                rounded-full
              "
            >
              {overdueTasks.length}
            </span>

          </div>


          <div className="divide-y divide-slate-700/60">

            {overdueTasks.slice(0, 3).map(task => (

              <OverdueItem
                key={task.id}
                task={task}
              />

            ))}

          </div>

        </section>

      )}

    </div>
  );
}


/* ==================================================
   STAT CARD
================================================== */

function StatCard({
  title,
  value,
  icon,
  accent
}) {

  const accents = {

    blue: {
      icon: "text-blue-400",
      border: "hover:border-blue-400/50",
      glow: "group-hover:bg-blue-400/10",
      shadow: "group-hover:shadow-[0_8px_30px_rgba(59,130,246,0.12)]"
    },

    cyan: {
      icon: "text-cyan-400",
      border: "hover:border-cyan-400/50",
      glow: "group-hover:bg-cyan-400/10",
      shadow: "group-hover:shadow-[0_8px_30px_rgba(34,211,238,0.12)]"
    },

    red: {
      icon: "text-red-400",
      border: "hover:border-red-400/50",
      glow: "group-hover:bg-red-400/10",
      shadow: "group-hover:shadow-[0_8px_30px_rgba(248,113,113,0.12)]"
    },

    green: {
      icon: "text-emerald-400",
      border: "hover:border-emerald-400/50",
      glow: "group-hover:bg-emerald-400/10",
      shadow: "group-hover:shadow-[0_8px_30px_rgba(52,211,153,0.12)]"
    }

  };


  const style = accents[accent];


  return (

    <div
      className={`
        group
        relative
        overflow-hidden
        rounded-xl
        border
        border-slate-600
        bg-slate-800/65
        px-5
        py-4
        cursor-default
        transition-all
        duration-300
        hover:-translate-y-1
        hover:bg-slate-800/90
        ${style.border}
        ${style.shadow}
      `}
    >

      {/* Accent glow */}

      <div
        className={`
          pointer-events-none
          absolute
          -right-8
          -top-8
          w-24
          h-24
          rounded-full
          blur-2xl
          opacity-0
          transition-opacity
          duration-300
          ${style.glow}
          group-hover:opacity-100
        `}
      />


      <div className="relative">

        <div className="flex items-center justify-between">

          <span
            className={`
              ${style.icon}
              transition-all
              duration-300
              group-hover:scale-110
            `}
          >
            {icon}
          </span>


          <ArrowUpRight
            size={15}
            className="
              text-slate-600
              opacity-0
              translate-x-1
              -translate-y-1
              transition-all
              duration-300
              group-hover:opacity-100
              group-hover:text-slate-300
              group-hover:translate-x-0
              group-hover:translate-y-0
            "
          />

        </div>


        <div className="mt-4">

          <p
            className="
              text-xs
              font-medium
              text-slate-400
              transition-colors
              duration-300
              group-hover:text-slate-300
            "
          >
            {title}
          </p>


          <span
            className="
              block
              text-3xl
              font-bold
              text-white
              mt-1
              transition-all
              duration-300
              group-hover:translate-x-0.5
            "
          >
            {value}
          </span>

        </div>

      </div>

    </div>

  );
}


/* ==================================================
   TODAY ITEM
================================================== */

function TaskItem({ task }) {

  return (

    <div
      className="
        group/item
        flex
        items-center
        justify-between
        rounded-lg
        bg-slate-900/50
        border
        border-slate-700
        px-3
        py-2.5
        transition-all
        duration-200
        hover:bg-slate-700/50
        hover:border-slate-600
      "
    >

      <div className="flex items-center gap-3 min-w-0">

        <span
          className="
            w-1.5
            h-1.5
            rounded-full
            bg-cyan-400
            shadow-[0_0_7px_rgba(34,211,238,0.6)]
            shrink-0
          "
        />

        <span
          className="
            text-sm
            text-slate-300
            truncate
            transition-colors
            group-hover/item:text-white
          "
        >
          {task.title}
        </span>

      </div>


      <span
        className="
          text-[11px]
          text-slate-400
          ml-3
          shrink-0
        "
      >
        {task.dueTime || "--:--"}
      </span>

    </div>

  );
}


/* ==================================================
   OVERDUE ITEM
================================================== */

function OverdueItem({ task }) {

  return (

    <div
      className="
        group/item
        flex
        items-center
        justify-between
        px-6
        py-3.5
        transition-all
        duration-200
        hover:bg-red-400/[0.05]
      "
    >

      <div className="flex items-center gap-3 min-w-0">

        <span
          className="
            w-1.5
            h-1.5
            rounded-full
            bg-red-400
            shadow-[0_0_7px_rgba(248,113,113,0.7)]
            shrink-0
          "
        />

        <span
          className="
            text-sm
            text-slate-300
            truncate
            transition-colors
            group-hover/item:text-white
          "
        >
          {task.title}
        </span>

      </div>


      <span
        className="
          text-[11px]
          text-red-400
          shrink-0
          ml-3
        "
      >
        {task.dueDate}
      </span>

    </div>

  );
}