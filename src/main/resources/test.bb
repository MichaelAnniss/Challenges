function test(X) {
    decr X;
}

function test2(X) {
    incr X;
}

X = 5;
Y = 3;
Z = (5 + 2) * X; // should be 7 * 5 = 35

while X != Y {
   test2(Y);
}

while X != 3 {
   test(X);
}