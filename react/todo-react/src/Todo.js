import { useState } from "react";

const Todo = ({ todo, deleteHandler, updateHandler }) => {
  const [updateMod, setUpdateMode] = useState(false);
  const [title, setTitle] = useState(todo.title);

  const updateModeHandler = () => {
    setUpdateMode(true);
  };

  const changeHandler = (e) => {
    setTitle(e.target.value);
  };

  const updater = () => {
    updateHandler({ id: todo.id, title: title });
    setUpdateMode(false);
  };

  if (updateMod) {
    return (
      <>
        <input type="text" value={title} onChange={changeHandler} />
        <button onClick={updater}> 수정</button>
      </>
    );
  } else {
    return (
      <>
        {todo.title}
        <button onClick={() => deleteHandler(todo.id)}>삭제</button>
        <button onClick={() => updateModeHandler(todo.id)}>수정</button>
      </>
    );
  }
};

export default Todo;
