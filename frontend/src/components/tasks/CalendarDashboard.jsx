import { useState } from "react";
import { useTasks } from "../../context/TaskContext";
import { ChevronLeft, ChevronRight } from "lucide-react";

export default function CalendarDashboard() {
  const [viewMode, setViewMode] = useState("week");
  const [currentDate, setCurrentDate] = useState(new Date());


  const { tasks } = useTasks();


  const hours = [
    "09:00",
    "10:00",
    "11:00",
    "12:00",
    "13:00",
    "14:00"
  ];

  const weekDays = [
    "Mon",
    "Tue",
    "Wed",
    "Thu",
    "Fri",
    "Sat",
    "Sun"
  ];

  /* ---------------- DATE INFO ---------------- */

  const currentDay = currentDate.toLocaleDateString("en-US", {
    weekday: "long"
  });

  const currentMonth = currentDate.toLocaleDateString("en-US", {
    month: "long"
  });

  const currentYear = currentDate.getFullYear();

  const month = currentDate.getMonth();
  const year = currentDate.getFullYear();

  /* ---------------- NAVIGATION ---------------- */

  const handlePrev = () => {
    if (viewMode === "month") {
      setCurrentDate(new Date(year, month - 1, 1));
    }

    if (viewMode === "week") {
      const prevWeek = new Date(currentDate);
      prevWeek.setDate(currentDate.getDate() - 7);
      setCurrentDate(prevWeek);
    }

    if (viewMode === "day") {
      const prevDay = new Date(currentDate);
      prevDay.setDate(currentDate.getDate() - 1);
      setCurrentDate(prevDay);
    }
  };

  const handleNext = () => {
    if (viewMode === "month") {
      setCurrentDate(new Date(year, month + 1, 1));
    }

    if (viewMode === "week") {
      const nextWeek = new Date(currentDate);
      nextWeek.setDate(currentDate.getDate() + 7);
      setCurrentDate(nextWeek);
    }

    if (viewMode === "day") {
      const nextDay = new Date(currentDate);
      nextDay.setDate(currentDate.getDate() + 1);
      setCurrentDate(nextDay);
    }
  };

  /* ---------------- MONTH LOGIC ---------------- */

  const firstDayOfMonth = new Date(year, month, 1).getDay();

  const adjustedFirstDay = firstDayOfMonth === 0
    ? 6
    : firstDayOfMonth - 1;

  const daysInMonth = new Date(year, month + 1, 0).getDate();

  const monthCells = [];

  for (let i = 0; i < adjustedFirstDay; i++) {
    monthCells.push(null);
  }

  for (let day = 1; day <= daysInMonth; day++) {
    monthCells.push(day);
  }

  /* ---------------- WEEK LOGIC ---------------- */

  const startOfWeek = new Date(currentDate);

  const dayIndex = currentDate.getDay();

  const adjustedDayIndex = dayIndex === 0
    ? 6
    : dayIndex - 1;

  startOfWeek.setDate(currentDate.getDate() - adjustedDayIndex);

const calendarEvents = tasks
  .filter(task => task.dueDate)
  .map(task => {

    const [year, month, day] = task.dueDate.split("-");

    const date = new Date(
      Number(year),
      Number(month) - 1,
      Number(day)
    );

    date.setHours(0, 0, 0, 0);
    
    return {
      id: task.id,
      title: task.title,
      date,
      hour: "09:00",

      color:
        task.priority === "High"
          ? "bg-red-500/30"
          : task.priority === "Medium"
          ? "bg-yellow-500/30"
          : "bg-green-500/30"
    };
  });


const weekDates = Array.from({ length: 7 }, (_, i) => {

  const date = new Date(startOfWeek);

  date.setDate(startOfWeek.getDate() + i);

  return date;

});



  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded-2xl p-6">

      {/* HEADER */}
      <div className="flex items-center justify-between mb-8">

        <div>
          <h2 className="text-4xl font-bold text-slate-100">
            {currentMonth} {currentYear}
          </h2>
        </div>

    
      </div>

      {/* TOP BAR */}
      <div className="flex items-start justify-between mb-10">

        <div className="flex flex-col gap-5">

          {/* TABS */}
          <div className="flex items-center bg-slate-700/30 border border-slate-600 rounded-xl p-1 w-fit">

            {["day", "week", "month"].map(mode => (
              <button
                key={mode}
                onClick={() => setViewMode(mode)}
                className={`
                  px-5 py-2
                  rounded-lg
                  text-sm
                  font-medium
                  capitalize
                  transition-all
                  duration-200
                  ${
                    viewMode === mode
                      ? "bg-slate-200 text-slate-900"
                      : "text-slate-300 hover:bg-slate-600/40"
                  }
                `}
              >
                {mode}
              </button>
            ))}
          </div>

          {/* CURRENT DAY */}
          <div>
            <p className="text-xs tracking-[0.35em] uppercase text-slate-400 font-semibold">
              {currentDay}
            </p>
          </div>
        </div>

        {/* ARROWS */}
        <div className="flex gap-2">

          <button
            onClick={handlePrev}
            className="
              w-11
              h-11
              flex
              items-center
              justify-center
              rounded-xl
              bg-slate-700/40
              border
              border-slate-600
              hover:bg-slate-600/50
              transition
            "
          >
            <ChevronLeft size={18} />
          </button>

          <button
            onClick={handleNext}
            className="
              w-11
              h-11
              flex
              items-center
              justify-center
              rounded-xl
              bg-slate-700/40
              border
              border-slate-600
              hover:bg-slate-600/50
              transition
            "
          >
            <ChevronRight size={18} />
          </button>

        </div>
      </div>

      {/* DAY VIEW */}
      {viewMode === "day" && (
        <div className="border border-slate-600 rounded-2xl overflow-hidden">

          {hours.map(hour => (
            <div
              key={hour}
              className="grid grid-cols-[100px_1fr] border-b border-slate-700 min-h-[100px]"
            >

              <div className="border-r border-slate-700 flex items-start justify-center pt-4 text-slate-400 text-sm">
                {hour}
              </div>

              <div className="p-3">
                {calendarEvents
  .filter(event =>

    event.hour === hour &&

    event.date.getDate() === currentDate.getDate() &&

    event.date.getMonth() === currentDate.getMonth() &&

    event.date.getFullYear() === currentDate.getFullYear()

  )
  .map(event => (

    <div
      key={event.id}
      className={`${event.color} rounded-lg px-2 py-1 text-xs mb-2`}
    >
      {event.title}
    </div>

))}
              </div>
            </div>
          ))}
        </div>
      )}

      {/* WEEK VIEW */}
      {viewMode === "week" && (
        <div className="overflow-hidden border border-slate-600 rounded-2xl">

          {/* WEEK HEADER */}
          <div className="grid grid-cols-8 border-b border-slate-700">

            <div></div>

           {weekDates.map((date, index) => (

  <div
    key={index}
    className="p-4 text-center border-l border-slate-700"
  >
    <p className="text-xs uppercase tracking-widest text-slate-400 mb-1">
      {weekDays[index]}
    </p>

    <p className="text-lg font-semibold text-slate-100">
      {date.getDate()}
    </p>
  </div>

))}
          </div>

          {/* WEEK BODY */}
          {hours.map(hour => (
            <div
              key={hour}
              className="grid grid-cols-8 min-h-[100px] border-b border-slate-700"
            >

              <div className="flex items-start justify-center pt-4 text-sm text-slate-400 border-r border-slate-700">
                {hour}
              </div>

              {weekDates.map((date, index) => {

  const events = calendarEvents.filter(event =>

    event.hour === hour &&

    event.date.getDate() === date.getDate() &&

    event.date.getMonth() === date.getMonth() &&

    event.date.getFullYear() === date.getFullYear()

  );

  return (

    <div
      key={index}
      className="border-l border-slate-700 p-2"
    >

      {events.map(event => (

        <div
          key={event.id}
          className={`${event.color} rounded-lg px-2 py-1 text-xs mb-2`}
        >
          {event.title}
        </div>

      ))}

    </div>

  );

})}

   </div>
          ))}
        </div>
      )}


      {/* MONTH VIEW */}
      {viewMode === "month" && (
        <div>

          {/* DAYS HEADER */}
          <div className="grid grid-cols-7 mb-4">
            {weekDays.map(day => (
              <div
                key={day}
                className="text-center text-slate-300 font-semibold"
              >
                {day}
              </div>
            ))}
          </div>

          {/* MONTH GRID */}
          <div className="grid grid-cols-7 gap-3">

            {monthCells.map((day, index) => {

              const events = calendarEvents.filter(event =>

  event.date.getDate() === day &&

  event.date.getMonth() === month &&

  event.date.getFullYear() === year

);

              return (
                <div
                  key={index}
                  className={`
                    min-h-[140px]
                    rounded-2xl
                    border
                    p-3
                    ${
                      day
                        ? "border-slate-600 bg-slate-700/20"
                        : "border-transparent bg-transparent"
                    }
                  `}
                >

                  {day && (
                    <>
                      <p className="font-semibold text-slate-100 mb-3">
                        {day}
                      </p>

                      <div className="flex flex-col gap-2">

                        {events.map(event => (
                          <div
                            key={event.id}
                            className={`
                              ${event.color}
                              rounded-lg
                              px-2
                              py-1
                              text-xs
                              truncate
                            `}
                          >
                            {event.title}
                          </div>
                        ))}
                      </div>
                    </>
                  )}
                </div>
              );
            })}
          </div>
        </div>
      )}
    </div>
  );
}