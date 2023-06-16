function x = lup(A,b, trace)
[L,U,P]=luptr(A,1);
y=forwardsubsttr(L,P*b,trace);
x=backsubsttr(U,y,trace);
end
