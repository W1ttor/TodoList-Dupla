import { createContext, useContext, useEffect, useState } from "react";
import mockTasks from "../data/tasks";

const TaskContext = createContext();

export function TaskProvider({ children }) {
  const [tasks, setTasks] = useState([]);

const [lists, setLists] = useState([
  {
    id: "personal",
    label: "Personal",
    color: "bg-red-400"
  },
  {
    id: "work",
    label: "Work",
    color: "bg-blue-400"
  },
  {
    id: "list1",
    label: "List 1",
    color: "bg-yellow-400"
  }
]);


const [tags, setTags] = useState([
  {
    id: 1,
    label: "Tag 1",
    color: "bg-cyan-300"
  },
  {
    id: 2,
    label: "Tag 2",
    color: "bg-red-300"
  }
]);




  const [activeMenu, setActiveMenu] = useState("today");

  const titles = {
  today: "Today",
  upcoming: "Upcoming",
  calendar: "Calendar",
  sticky: "Sticky Wall"
};

lists.forEach(list => {
  titles[list.id] = list.label;
});

  // buscar tarefas backend
  useEffect(() => {
    const savedTasks = localStorage.getItem("tasks");

  if (savedTasks) {

    setTasks(
      JSON.parse(savedTasks)
    );

  } else {

    setTasks(mockTasks);

  }

}, []);

useEffect(() => {

  localStorage.setItem(
    "tasks",
    JSON.stringify(tasks)
  );

}, [tasks]);

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

function createTask(taskData) {

  const newTask = {
    id: Date.now(),
    ...taskData
  };

  setTasks(prev => [
    ...prev,
    newTask
  ]);

  taskData.tags.forEach(tag => {

    const exists = tags.some(
      item =>
        item.label.toLowerCase() ===
        tag.toLowerCase()
    );

    if (!exists) {

      setTags(prev => [
        ...prev,
        {
          id: Date.now() + Math.random(),
          label: tag,
          color: "bg-cyan-300"
        }
      ]);

    }

  });

}

function updateTask(taskData) {

  setTasks(prev =>
    prev.map(task =>
      task.id === taskData.id
        ? taskData
        : task
    )
  );

  taskData.tags.forEach(tag => {

    const exists = tags.some(
      item =>
        item.label.toLowerCase() ===
        tag.toLowerCase()
    );

    if (!exists) {

      setTags(prev => [
        ...prev,
        {
          id: Date.now() + Math.random(),
          label: tag,
          color: "bg-cyan-300"
        }
      ]);

    }

  });

}

function deleteTask(id) {

  setTasks(prev =>
    prev.filter(task => task.id !== id)
  );

}



  const counts = {
  today: tasks.filter(
    task => task.section === "today"
  ).length,

  upcoming: tasks.filter(
    task =>
      task.section === "tomorrow" ||
      task.section === "week"
  ).length,

  calendar: 0,
  sticky: 0
};

lists.forEach(list => {
  counts[list.id] = tasks.filter(
    task => task.list === list.id
  ).length;
});

  return (
    <TaskContext.Provider
      value={{
  tasks,
  setTasks,

  lists,
  setLists,

  tags,
  setTags,

  activeMenu,
  setActiveMenu,

  titles,
  counts,

  fetchTasks,

  createTask,
  updateTask,
  deleteTask
}}
    >
      {children}
    </TaskContext.Provider>
  );
}

export function useTasks() {
  return useContext(TaskContext);
}