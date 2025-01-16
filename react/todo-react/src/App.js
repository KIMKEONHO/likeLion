import TodoBox from "./TodoBox";
import CompleteNoteBox from "./note/CompleteNoteBox";
import NoteBox from "./note/NoteBox";

function App() {
  return (
    <div className="propcard">
      <div className="card" style={{ flex: "1", marginRight: "1rem" }}>
        <NoteBox />
      </div>
      <div className="card" style={{ flex: "1" }}>
        <CompleteNoteBox />
      </div>
    </div>
  );
}

export default App;
