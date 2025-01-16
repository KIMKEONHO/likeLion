import { useRef, useState } from "react";
import TodoInput from "./TodoInput";
import TodoList from "./TodoList";
import NotoBox from "./note/NoteBox";

const TodoBox = () => {
  const [todoList, setTodoList] = useState([
    { id: 1, title: "리액트 공부하기" },
    { id: 2, title: "스프링 공부하기" },
    { id: 3, title: "커피마시기" },
    { id: 4, title: "점심 맛있게 먹기" },
  ]);

  const id = useRef(Math.max(...todoList.map((todo) => todo.id)) + 1);

  const addTodoList = (title) => {
    const newTodo = {
      id: id.current++,
      title: title,
    };
    setTodoList([...todoList, newTodo]);
  };

  const deleteTodoList = (id) => {
    setTodoList(todoList.filter((todo) => todo.id !== id));
  };

  const updateTodoList = (todo) => {
    const updatedTodoList = todoList.map((item) =>
      item.id === todo.id ? { ...item, title: todo.title } : item
    );
    setTodoList(updatedTodoList);
  };

  return (
    <div>
      <TodoInput addTodo={addTodoList} />
      <TodoList
        todoList={todoList}
        deleteHandler={deleteTodoList}
        updateHandler={updateTodoList}
      />
      <NotoBox /> {/* NotoBox 추가 */}
    </div>
  );
};

export default TodoBox;
