import { createContext, useContext, useEffect, useState } from "react";
import mockTasks from "../data/tasks";
import stickyNotes from "../data/stickyNotes";

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

  /*
   * Quantidade de notas do Sticky Wall
   *
   * Primeiro tenta recuperar as notas salvas no localStorage.
   * Se ainda não existir nada salvo, utiliza as notas mock.
   */
  const [stickyNotesCount, setStickyNotesCount] = useState(() => {
    const savedNotes = localStorage.getItem("stickyNotes");

    if (savedNotes) {
      try {
        const parsedNotes = JSON.parse(savedNotes);

        return Array.isArray(parsedNotes)
          ? parsedNotes.length
          : 0;
      } catch {
        return 0;
      }
    }

    return stickyNotes.length;
  });

  const titles = {
    today: "Today",
    upcoming: "Upcoming",
    calendar: "Calendar",
    sticky: "Sticky Wall"
  };

  lists.forEach(list => {
    titles[list.id] = list.label;
  });

  // Buscar tarefas do localStorage
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

  // Salvar tarefas no localStorage
  useEffect(() => {
    localStorage.setItem(
      "tasks",
      JSON.stringify(tasks)
    );
  }, [tasks]);

  async function fetchTasks() {
    try {
      const token = localStorage.getItem("token");

      const response = await fetch(
        "http://26.51.220.173:2020/v1/tasks",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      if (!response.ok) {
        throw new Error("Erro ao buscar tarefas");
      }

      const data = await response.json();

      setTasks(data);

    } catch (error) {
      console.error(
        "Erro ao carregar tarefas:",
        error
      );
    }
  }

  function getTaskSection(dueDate) {

    if (!dueDate) {
      return "today";
    }

    const today = new Date();

    today.setHours(0, 0, 0, 0);

    const [year, month, day] =
      dueDate.split("-");

    const taskDate = new Date(
      Number(year),
      Number(month) - 1,
      Number(day)
    );

    taskDate.setHours(0, 0, 0, 0);

    const diffDays = Math.floor(
      (taskDate - today) /
      (1000 * 60 * 60 * 24)
    );

    console.log({
      dueDate,
      today,
      taskDate,
      diffDays
    });

    if (diffDays <= 0) {
      return "today";
    }

    if (diffDays === 1) {
      return "tomorrow";
    }

    return "week";
  }

  function createTask(taskData) {

    const newTask = {
      id: Date.now(),
      ...taskData,
      section: getTaskSection(
        taskData.dueDate
      )
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
          ? {
              ...taskData,
              section: getTaskSection(
                taskData.dueDate
              )
            }
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
      prev.filter(
        task => task.id !== id
      )
    );

  }


  function deleteList(listId) {


    setLists(prev =>
      prev.filter(list => list.id !== listId)
    );

    setTasks(prev =>
      prev.map(task =>
        task.list === listId
          ? {
              ...task,
              list: null
            }
          : task
      )
    );

  }


  const counts = {
    today: tasks.filter(
      task => task.section === "today"
    ).length,

    upcoming: tasks.filter(
      task =>
        task.section === "today" ||
        task.section === "tomorrow" ||
        task.section === "week"
    ).length,

    calendar: 0,

    sticky: stickyNotesCount
  };

  lists.forEach(list => {

    counts[list.id] =
      tasks.filter(
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

        stickyNotesCount,
        setStickyNotesCount,

        fetchTasks,

        createTask,
        updateTask,
        deleteTask,
        deleteList
      }}
    >
      {children}
    </TaskContext.Provider>
  );
}

export function useTasks() {
  return useContext(TaskContext);
}