// 과거 예제제
// function increaseAndPrint(n, callback){
//     setTimeout(()=> {
//         const increased = n + 1;
//         console.log(increased)

//         if(callback){
//             callback(increased);
//         }
//     }, 1000)
// }

// increaseAndPrint(0, (n)=>{
//     increaseAndPrint(n, (n)=>{

//     })
// })

// promise 객체를 사용
// const mypromise = new Promise()

function increaseAndPrint(n) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const value = n + 1;
      if (value === 5) {
        const error = new Error();
        error.name = "값이 5입니다.";
        reject(error);
        return;
      }
      console.log(value);
      resolve(value);
    }, 1000);
  });
}

// increaseAndPrint(0)
//   .then((n) => {
//     return increaseAndPrint(n);
//   })
//   .then((n) => {
//     return increaseAndPrint(n);
//   })
//   .then((n) => {
//     return increaseAndPrint(n);
//   })
//   .then((n) => {
//     return increaseAndPrint(n);
//   })
//   .then((n) => {
//     return increaseAndPrint(n);
//   })
//   .catch((e) => {
//     console.error(e);
//   });

increaseAndPrint(0)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .catch((e) => {
    console.error(e);
  });
