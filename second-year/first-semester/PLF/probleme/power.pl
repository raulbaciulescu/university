power(_,0,1):-!.
power(X, N, R):-
    N1=N-1,
    power(X,N1,R1),
    R=R1*R.
