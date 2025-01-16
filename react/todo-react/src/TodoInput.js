const TodoInput = ({ addTodo }) => {
  const keyDownHandler = (e) => {
    console.log(e.key);
    if (e.key === "Enter") {
      console.log(e.target.value);
      if (e.target.value === "") return;
      addTodo(e.target.value);
      e.target.value = "";
    }
  };
  return (
    <input
      type="text"
      placeholder="할일을 입력하세요."
      onKeyDown={keyDownHandler}
    />
  );
};

export default TodoInput;
