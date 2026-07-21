import { useEffect, useState } from "react";

export default function TaskDetailsPanel({
  task,
  isCreatingTask,
  setSelectedTask,
  setIsCreatingTask
}) {

const [title, setTitle] = useState(
      task?.title || ""
    );

    const [description, setDescription] = useState(
      task?.description || ""
    );

    const [list, setList] = useState(
      task?.list || "Personal"
    );

    const [dueDate, setDueDate] = useState(
      task?.dueDate || ""
    );

    const [tags, setTags] = useState(
      task?.tags?.join(", ") || ""
    );  

useEffect(() => {

  if (task) {

    setTitle(task.title || "");

    setDescription(
      task.description || ""
    );

    setList(
      task.list || "Personal"
    );

    setDueDate(
      task.dueDate || ""
    );

    setTags(
      task.tags?.join(", ") || ""
    );

  } else if (isCreatingTask) {

    setTitle("");

    setDescription("");

    setList("Personal");

    setDueDate("");

    setTags("");

  }

}, [task, isCreatingTask]);



function handleSave() {

  const taskData = {

    title,

    description,

    list,

    dueDate,

    tags: tags
      .split(",")
      .map(tag => tag.trim())
      .filter(Boolean)

  };

  console.log(taskData);

}


    if (!task && !isCreatingTask)  {
    return (
      <div className="bg-slate-800/40 border border-slate-600 rounded p-6">
        <p className="text-slate-400">
          Selecione uma tarefa.
        </p>
      </div>
    );
  }
 
  return (
    <div className="bg-slate-800/40 border border-slate-600 rounded p-6 h-full">

      <div className="flex items-center justify-between mb-6">

        <h2 className="text-2xl font-bold">
          {isCreatingTask
            ? "Nova Task"
            : "Editar Task"}
        </h2>

        <button
          onClick={() => {
            setSelectedTask(null);
            setIsCreatingTask(false);
          }}
          className="
            text-slate-400
            hover:text-white
            transition
            text-xl
          "
        >
          ✕
        </button>

      </div>

  <div className="space-y-6">

    <div>
      <label className="text-sm text-slate-400">
        Title
      </label>

      <input
        value={title}
        onChange={(e) =>
          setTitle(e.target.value)
        }
        className="w-full mt-2 bg-slate-700 border border-slate-600 rounded-lg p-3 outline-none focus:border-blue-500"
        placeholder="Task title"
      />
    </div>

    <div>
      <label className="text-sm text-slate-400">
        Description
      </label>

      <textarea
        rows={5}
        value={description}
        onChange={(e) =>
          setDescription(e.target.value)
        }
        className="w-full mt-2 bg-slate-700 border border-slate-600 rounded-lg p-3 resize-none outline-none focus:border-blue-500"
        placeholder="Task description"
      />
    </div>

    <div>
      <label className="text-sm text-slate-400">
        List
      </label>

      <select
        value={list}
        onChange={(e) =>
          setList(e.target.value)
        }
        className="w-full mt-2 bg-slate-700 border border-slate-600 rounded-lg p-3"
      >
        <option>Personal</option>
        <option>Work</option>
        <option>List 1</option>
      </select>
    </div>

    <div>
      <label className="text-sm text-slate-400">
        Due Date
      </label>

      <input
        type="date"
        value={dueDate}
        onChange={(e) =>
          setDueDate(e.target.value)
        }
        className="w-full mt-2 bg-slate-700 border border-slate-600 rounded-lg p-3"
      />
    </div>

    <div>
      <label className="text-sm text-slate-400">
        Tags
      </label>

      <input
        value={tags}
        onChange={(e) =>
          setTags(e.target.value)
        }
        placeholder="Work, Study..."
        className="w-full mt-2 bg-slate-700 border border-slate-600 rounded-lg p-3"
      />
    </div>

    <div className="flex justify-end gap-3 pt-4">

      <button
        onClick={() => {
          setSelectedTask(null);
          setIsCreatingTask(false);
        }}
        className="px-5 py-2 rounded-lg bg-slate-700 hover:bg-slate-600 transition"
      >
        Cancel
      </button>

      <button
        onClick={handleSave} className="px-5 py-2 rounded-lg bg-blue-600 hover:bg-blue-700 transition"
      >
        Save
      </button>

    </div>

  </div>

    </div>
  );
}