const numbers = {
  _a: 1,
  _b: 2,
  get a() {
    console("get a 함수() 실행");
    return this._a;
  },
  get sum() {
    return this._a + this._b;
  },
  set a(value) {
    this._a = value;
  },
  get b() {
    return this._b;
  },
  set b(value) {
    this._b = value;
  },
};
