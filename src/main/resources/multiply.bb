clear X;
X = 4;

clear Y;
Y = 6;

clear Z;

answer = (X * Y);

while X != 0 {
    clear W;
    while Y != 0 {
        incr Z;
        incr W;
        decr Y;
    }
    while W != 0 {
        incr Y;
        decr W;
    }
    decr X;
}