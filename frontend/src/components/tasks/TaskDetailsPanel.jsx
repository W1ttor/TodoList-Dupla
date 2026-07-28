import { useEffect, useState } from "react";
import { useTasks } from "../../context/TaskContext";
import toast from "react-hot-toast";
import Modal from "../layout/Modal";
import TagModal from "./TagModal";

export default function TaskDetailsPanel({
  task,
  isCreatingTask,
  setSelectedTask,
  setIsCreatingTask
}) {

const {
    createTask,
  updateTask,
  deleteTask,
  lists,
  tags: availableTags,
  setTags: setAvailableTags
} = useTasks();


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
      task?.tags || []
    );

    const [priority, setPriority] = useState(
        task?.priority || "Medium"
      );

    const [completed, setCompleted] = useState(
        task?.completed || false      );

    const [showTagModal, setShowTagModal] = useState(false);    


    const [showDeleteModal, setShowDeleteModal] = useState(false);
  

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

    setTags(task?.tags || []);  

    setPriority(
      task.priority || "Medium"
    );

    setCompleted(
      task.completed || false
    );

  } else if (isCreatingTask) {

    setTitle("");

    setDescription("");

    setList("Personal");

    setDueDate("");

    setTags([]);

    setPriority("Medium");

    setCompleted(false);

  }

}, [task, isCreatingTask]);



function handleSave() {

  const taskData = {

    ...(task || {}),

    title,

    description,

    list,

    dueDate,

    priority,

    completed,

    tags

  };

  if (isCreatingTask) {

    createTask(taskData);

  } else {

    updateTask(taskData);

  }

  setSelectedTask(null);

  setIsCreatingTask(false);

}

function handleDelete() {

  deleteTask(task.id);

  toast.success("Task deleted.");

  setSelectedTask(null);

  setIsCreatingTask(false);

}


function handleCreateTag(newTag) {

  


  setAvailableTags(prev => [
    ...prev,
    newTag
  ]);

  setTags(prev => [
    ...prev,
    newTag.label
  ]);

  setShowTagModal(false);

}


    if (!task && !isCreatingTask)  {
    return (
      <div className="bg-slate-800/40 border border-slate-600 rounded-xl p-6 sticky top-8">
        <p className="text-slate-400">
          Selecione uma tarefa.
        </p>
      </div>
    );
  }


const priorityColor = {
  Low: "text-green-400",
  Medium: "text-yellow-400",
  High: "text-red-400"
};

 
  return (
<>
    
   <div className="bg-slate-800/40 border border-slate-600 rounded p-6 h-full -mt-16">

      <div className="flex items-start justify-between border-b border-slate-700 pb-5 mb-7">

         <h2 className="text-2xl font-bold text-white">

          {isCreatingTask
           ? "New Task"
           : "Edit Task"}

          </h2>

        <button
          onClick={() => {
            setSelectedTask(null);
            setIsCreatingTask(false);
          }}
          className="
            w-9
            h-9
            rounded-lg
            flex
            items-center
            justify-center
            hover:bg-slate-700
            text-slate-400
            hover:text-white
            transition
            "
        >
          ✕
        </button>

      </div>

  <div className="space-y-6">

    <div>
      <label className="block text-xs uppercase tracking-wider text-slate-400 mb-2 font-semibold">
        Title
      </label>

      <input
        value={title}
        onChange={(e) =>
          setTitle(e.target.value)
        }
        className="w-full bg-slate-800 border border-slate-700 rounded-xl px-4 py-3 outline-none transition focus:border-blue-500 focus:ring-2 focus:ring-blue-500/20"
        placeholder="Task title"
      />
    </div>

    <div>
      <label className="block text-xs uppercase tracking-wider text-slate-400 mb-2 font-semibold">
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
      <label className="block text-xs uppercase tracking-wider text-slate-400 mb-2 font-semibold">
        List
      </label>

      <select
        value={list}
        onChange={(e) =>
          setList(e.target.value)
        }
        className="w-full bg-slate-800 border border-slate-700 rounded-xl px-4 py-3 outline-none transition focus:border-blue-500 focus:ring-2 focus:ring-blue-500/20"
      >
          {lists.map(listItem => (

            <option
              key={listItem.id}
              value={listItem.id}
            >
              {listItem.label}
            </option>

          ))}
      </select>
    </div>

    <div>
      <label className="block text-xs uppercase tracking-wider text-slate-400 mb-2 font-semibold">
        Due Date
      </label>

      <input
        type="date"
        value={dueDate}
        onChange={(e) =>
          setDueDate(e.target.value)
        }
        className="w-full bg-slate-800 border border-slate-700 rounded-xl px-4 py-3 outline-none transition focus:border-blue-500 focus:ring-2 focus:ring-blue-500/20"
      />
    </div>

    <div>

      <label className="block text-xs uppercase tracking-wider text-slate-400 mb-2 font-semibold">
        Priority
      </label>

<select
  value={priority}
  onChange={(e) => setPriority(e.target.value)}
  className={`w-full bg-slate-800 border border-slate-700 rounded-xl px-4 py-3 ${priorityColor[priority]}`}
>
  <option value="Low">Low</option>
  <option value="Medium">Medium</option>
  <option value="High">High</option>
</select>

    </div>





    <div>
      <label className="block text-xs uppercase tracking-wider text-slate-400 mb-2 font-semibold">
        Tags
      </label>



<div className="flex flex-wrap gap-2 mt-3">

  {tags.map(tag => (

    <div
      key={tag}
      className="
        px-3
        py-1
        rounded-lg
        bg-sky-300
        text-slate-900
        text-sm
        flex
        items-center
        gap-2
      "
    >
      {tag}

      <button
        onClick={() =>
          setTags(
            tags.filter(t => t !== tag)
          )
        }
      >
        ×
      </button>

    </div>

  ))}

<button
  onClick={() => setShowTagModal(true)}
  className="
    text-blue-400
    text-sm
    hover:text-blue-300
  "
>
  + Add Tag
</button>

</div>
    </div>

<div className="flex items-center justify-between">

  <label className="text-xs uppercase tracking-wider text-slate-400 font-semibold">
    Completed
  </label>

  <button
    type="button"
    onClick={() => setCompleted(!completed)}
    className={`
      w-11
      h-6
      rounded-full
      transition
      relative
      ${
        completed
          ? "bg-blue-600"
          : "bg-slate-600"
      }
    `}
  >
    <span
      className={`
        absolute
        top-0.5
        left-0.5
        w-5
        h-5
        rounded-full
        bg-white
        transition
        ${
          completed
            ? "translate-x-5"
            : ""
        }
      `}
    />
  </button>

</div>



<div className="flex justify-between items-center pt-8 border-t border-slate-700">

  <button 
    onClick={() => setShowDeleteModal(true)}
    className="
      text-red-400
      hover:text-red-300
      transition
      font-medium
    "
  >
    Delete
  </button>

  <div className="flex gap-3">

    <button
      onClick={()=>{
        setSelectedTask(null);
        setIsCreatingTask(false);
      }}
      className="
        px-5
        py-3
        rounded-xl
        bg-slate-700
        hover:bg-slate-600
        transition
      "
    >
      Cancel
    </button>

    <button
      onClick={handleSave}
      className="
        px-6
        py-3
        rounded-xl
        bg-blue-600
        hover:bg-blue-700
        transition
        font-medium
      "
    >
      Save
    </button>

  </div>

</div>

  </div>

    </div>

 {showDeleteModal && (
      <Modal
        title="Delete task"
        message="This action cannot be undone."
        confirmText="Delete"
        cancelText="Cancel"
        onCancel={() => setShowDeleteModal(false)}
        onConfirm={() => {
          handleDelete();
          setShowDeleteModal(false);
        }}
      />
    )}


    {showTagModal && (

  <TagModal
    onClose={() => setShowTagModal(false)}
    onSave={handleCreateTag}
  />

)}

    </>
  );
}