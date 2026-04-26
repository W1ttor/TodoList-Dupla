import { createContext, useContext, useEffect, useState } from "react";
import mockTasks from "../data/tasks";

const TaskContext = createContext();

export function TaskProvider({ children }) {
  const [tasks, setTasks] = useState([]);
  const [activeMenu, setActiveMenu] = useState("today");

  const titles = {
    today: "Today",
    upcoming: "Upcoming",
    calendar: "Calendar",
    sticky: "Sticky Wall",
    personal: "Personal",
    work: "Work",
    list1: "List 1"
  };

  // buscar tarefas backend
  useEffect(() => {
    setTasks(mockTasks);
  }, []);

  async function fetchTasks() {
    try {
      const token = localStorage.getItem("token");

      const response = await fetch("http://26.51.220.173:2020/v1/tasks", {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      if (!response.ok) {
        throw new Error("Erro ao buscar tarefas");
      }

      const data = await response.json();
      setTasks(data);

    } catch (error) {
      console.error("Erro ao carregar tarefas:", error);
    }
  }

  const counts = {
    today: tasks.filter(task => task.type === "today").length,
    upcoming: tasks.filter(task => task.type === "upcoming").length,
    calendar: tasks.filter(task => task.type === "calendar").length,
    sticky: tasks.filter(task => task.type === "sticky").length,
    personal: tasks.filter(task => task.type === "personal").length,
    work: tasks.filter(task => task.type === "work").length,
    list1: tasks.filter(task => task.type === "list1").length,
  };

  return (
    <TaskContext.Provider
      value={{
        tasks,
        setTasks,
        activeMenu,
        setActiveMenu,
        titles,
        counts,
        fetchTasks
      }}
    >
      {children}
    </TaskContext.Provider>
  );
}

export function useTasks() {
  return useContext(TaskContext);
}