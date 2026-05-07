import { useState } from "react";

export default function CalendarDashboard() {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [viewMode, setViewMode] = useState("month");

  const monthNames = [
    "January", "February", "March", "April",
    "May", "June", "July", "August",
    "September", "October", "November", "December"
  ];

  const weekDays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

  const year = currentDate.getFullYear();
  const month = currentDate.getMonth();
  const today = new Date();

  const prev = () => {
    if (viewMode === "month") {
      setCurrentDate(new Date(year, month - 1, 1));
    } else if (viewMode === "week") {
      setCurrentDate(new Date(year, month, currentDate.getDate() - 7));
    } else {
      setCurrentDate(new Date(year, month, currentDate.getDate() - 1));
    }
  };

  const next = () => {
    if (viewMode === "month") {
      setCurrentDate(new Date(year, month + 1, 1));
    } else if (viewMode === "week") {
      setCurrentDate(new Date(year, month, currentDate.getDate() + 7));
    } else {
      setCurrentDate(new Date(year, month, currentDate.getDate() + 1));
    }
  };

  const renderMonthView = () => {
    const firstDayOfMonth = new Date(year, month, 1).getDay();
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    const days = [];

    for (let i = 0; i < firstDayOfMonth; i++) {
      days.push(null);
    }

    for (let day = 1; day <= daysInMonth; day++) {
      days.push(day);
    }

    return (
      <>
        <div className="grid grid-cols-7 gap-2 mb-3">
          {weekDays.map(day => (
            <div key={day} className="text-center font-semibold text-slate-300">
              {day}
            </div>
          ))}
        </div>

        <div className="grid grid-cols-7 gap-2">
          {days.map((day, index) => {
            const isToday =
              day &&
              today.getDate() === day &&
              today.getMonth() === month &&
              today.getFullYear() === year;

            return (
              <div
                key={index}
                className={`
                  h-16 rounded flex items-center justify-center border
                  ${day ? "border-slate-600" : "border-transparent"}
                  ${isToday ? "bg-blue-500 text-white font-bold" : "bg-slate-700/30 text-slate-200"}
                `}
              >
                {day || ""}
              </div>
            );
          })}
        </div>
      </>
    );
  };

  const renderWeekView = () => {
    const start = new Date(currentDate);
    start.setDate(currentDate.getDate() - currentDate.getDay());

    const days = Array.from({ length: 7 }, (_, i) => {
      const d = new Date(start);
      d.setDate(start.getDate() + i);
      return d;
    });

    return (
      <div className="grid grid-cols-7 gap-2">
        {days.map((date, index) => {
          const isToday =
            date.getDate() === today.getDate() &&
            date.getMonth() === today.getMonth() &&
            date.getFullYear() === today.getFullYear();

          return (
            <div
              key={index}
              className={`
                h-24 rounded border flex flex-col items-center justify-center
                ${isToday ? "bg-blue-500 text-white" : "bg-slate-700/30 border-slate-600"}
              `}
            >
              <span className="text-sm">{weekDays[index]}</span>
              <span className="text-xl font-bold">{date.getDate()}</span>
            </div>
          );
        })}
      </div>
    );
  };

  const renderDayView = () => {
    return (
      <div className="h-32 rounded border border-slate-600 bg-slate-700/30 flex flex-col items-center justify-center">
        <span className="text-lg text-slate-300">
          {weekDays[currentDate.getDay()]}
        </span>
        <span className="text-4xl font-bold">
          {currentDate.getDate()}
        </span>
      </div>
    );
  };

  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded p-6">

      {/* HEADER */}
      <div className="flex items-center justify-between mb-6">
        <button
          onClick={prev}
          className="px-4 py-2 bg-slate-700 rounded hover:bg-slate-600 transition"
        >
          ◀
        </button>

        <h2 className="text-2xl font-bold">
          {monthNames[currentDate.getMonth()]} {currentDate.getFullYear()}
        </h2>

        <button
          onClick={next}
          className="px-4 py-2 bg-slate-700 rounded hover:bg-slate-600 transition"
        >
          ▶
        </button>
      </div>

      {/* MODE BUTTONS */}
      <div className="flex gap-3 mb-6">
        {["day", "week", "month"].map(mode => (
          <button
            key={mode}
            onClick={() => setViewMode(mode)}
            className={`px-4 py-2 rounded transition ${
              viewMode === mode
                ? "bg-blue-500 text-white"
                : "bg-slate-700 text-slate-300 hover:bg-slate-600"
            }`}
          >
            {mode.charAt(0).toUpperCase() + mode.slice(1)}
          </button>
        ))}
      </div>

      {/* VIEWS */}
      {viewMode === "day" && renderDayView()}
      {viewMode === "week" && renderWeekView()}
      {viewMode === "month" && renderMonthView()}
    </div>
  );
}