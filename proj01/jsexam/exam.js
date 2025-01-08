var varI = 50;

console.log(varI);

const a = !true;

console.log(a);

const i = 1;
const j = "1";

// 자바 스크립트는 문자와 숫자도 같다고 인식한다.
console.log(i == j);

// 타입과 값이 같을 때 사용하는 연산
console.log(i === j);

const device = "iphone";

switch (device) {
  case "iphone":
    console.log("아이폰!");
  case "ipad":
    console.log("아이패드!");
  case "갤럭시 노트":
    console.log("갤럭시 노트!");
  default:
    console.log("잘못된 입력!");
}

function printCat() {
  const img = document.createElement("img");
  img.src = "https://www.boomplay.com/lyrics/168709125"; // 고양이 이미지 URL
  img.alt = "귀여운 고양이";
  document.body.appendChild(img); // 이미지 추가
}

function drawCat(type = "basic") {
  if (type === "basic") {
    console.log("  /\\_/\\   ");
    console.log(" ( o.o )  ");
    console.log("  > ^ <   ");
  } else if (type === "cute") {
    console.log("   /\\_/\\  ");
    console.log("  ( o.o ) ");
    console.log("  > ^ <   ");
    console.log(" /       \\");
    console.log("|         |");
    console.log(" \\_______/");
  } else if (type === "sitting") {
    console.log("   /\\_/\\   ");
    console.log("  ( ^.^ )  ");
    console.log("   > ^ <   ");
    console.log("  /     \\  ");
    console.log(" (       ) ");
    console.log("   \\___/   ");
  }
}

drawCat("cute");

drawCat("basic");

drawCat("sitting");

const drawCat2 = () => {
  if (type === "basic") {
    console.log("  /\\_/\\   ");
    console.log(" ( o.o )  ");
    console.log("  > ^ <   ");
  } else if (type === "cute") {
    console.log("   /\\_/\\  ");
    console.log("  ( o.o ) ");
    console.log("  > ^ <   ");
    console.log(" /       \\");
    console.log("|         |");
    console.log(" \\_______/");
  } else if (type === "sitting") {
    console.log("   /\\_/\\   ");
    console.log("  ( ^.^ )  ");
    console.log("   > ^ <   ");
    console.log("  /     \\  ");
    console.log(" (       ) ");
    console.log("   \\___/   ");
  }
};
