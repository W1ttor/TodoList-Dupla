import { useState } from "react";
import calendarEvents from "../../data/calendarEvents";

export default function CalendarDashboard() {
  const [viewMode, setViewMode] = useState("week");

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

  const monthDays = Array.from({ length: 31 }, (_, i) => i + 1);

  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded-xl p-6">

      {/* HEADER */}
      <div className="flex items-center justify-between mb-6">

        <h2 className="text-4xl font-bold">
          May 2026
        </h2>

        <button className="px-4 py-2 rounded bg-slate-700 hover:bg-slate-600 transition">
          Add Event
        </button>
      </div>

      {/* TOP BAR */}
      <div className="flex items-center justify-between mb-8">

        <div className="flex gap-3">
          {["day", "week", "month"].map(mode => (
            <button
              key={mode}
              onClick={() => setViewMode(mode)}
              className={`
                px-4 py-2 rounded transition capitalize
                ${
                  viewMode === mode
                    ? "bg-blue-500 text-white"
                    : "bg-slate-700 text-slate-300 hover:bg-slate-600"
                }
              `}
            >
              {mode}
            </button>
          ))}
        </div>

        <div className="flex gap-3">
          <button className="px-3 py-2 bg-slate-700 rounded hover:bg-slate-600">
            ◀
          </button>

          <button className="px-3 py-2 bg-slate-700 rounded hover:bg-slate-600">
            ▶
          </button>
        </div>
      </div>

      {/* DAY VIEW */}
      {viewMode === "day" && (
        <div className="border border-slate-600 rounded-lg overflow-hidden">

          {hours.map(hour => (
            <div
              key={hour}
              className="grid grid-cols-[100px_1fr] border-b border-slate-700 min-h-[90px]"
            >

              <div className="border-r border-slate-700 flex items-start justify-center pt-4 text-slate-400 text-sm">
                {hour}
              </div>

              <div className="p-3">
                {calendarEvents
                  .filter(event => event.hour === hour)
                  .map(event => (
                    <div
                      key={event.id}
                      className={`
                        ${event.color}
                        border border-slate-500
                        rounded-lg
                        p-3
                        mb-2
                      `}
                    >
                      <p className="font-semibold">
                        {event.title}
                      </p>
                    </div>
                  ))}
              </div>
            </div>
          ))}
        </div>
      )}

      {/* WEEK VIEW */}
      {viewMode === "week" && (
        <div className="overflow-hidden border border-slate-600 rounded-lg">

          <div className="grid grid-cols-8 border-b border-slate-700">

            <div></div>

            {weekDays.map(day => (
              <div
                key={day}
                className="p-4 text-center font-semibold border-l border-slate-700"
              >
                {day}
              </div>
            ))}
          </div>

          {hours.map(hour => (
            <div
              key={hour}
              className="grid grid-cols-8 min-h-[100px] border-b border-slate-700"
            >

              <div className="flex items-start justify-center pt-4 text-sm text-slate-400 border-r border-slate-700">
                {hour}
              </div>

              {weekDays.map(day => {
                const events = calendarEvents.filter(
                  event =>
                    event.weekDay === day &&
                    event.hour === hour
                );

                return (
                  <div
                    key={day}
                    className="border-l border-slate-700 p-2"
                  >
                    {events.map(event => (
                      <div
                        key={event.id}
                        className={`
                          ${event.color}
                          rounded-lg
                          p-2
                          text-sm
                          mb-2
                          border border-slate-500
                        `}
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

          <div className="grid grid-cols-7 mb-3">
            {weekDays.map(day => (
              <div
                key={day}
                className="text-center font-semibold text-slate-300"
              >
                {day}
              </div>
            ))}
          </div>

          <div className="grid grid-cols-7 gap-3">

            {monthDays.map(day => {
              const events = calendarEvents.filter(
                event => event.day === day
              );

              return (
                <div
                  key={day}
                  className="min-h-[120px] rounded-lg border border-slate-600 bg-slate-700/20 p-2"
                >

                  <p className="font-semibold mb-2">
                    {day}
                  </p>

                  <div className="flex flex-col gap-1">

                    {events.map(event => (
                      <div
                        key={event.id}
                        className={`
                          ${event.color}
                          rounded
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
                </div>
              );
            })}
          </div>
        </div>
      )}
    </div>
  );
}