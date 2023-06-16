function R=cholesky(A,trace)
%CHOLESKY - Cholesky factorization
%call R=Cholesky(A)
%A - HPD matrix
%R - upper triangular matrix

if nargin<2, trace=0; end
[m,n]=size(A);
if n ~= m
    error("A is not a sqare matrix");
end
if any(eig(A) <= 0)
    error("A is not a sqare matrix");
end
if trace, disp(A); end
for k=1:m
    if trace
        fprintf('step %d:\n', k);
    end
    if A(k,k)<=0
        error('matrix is not HPD')
    end
    for j=k+1:m
        before = A(j, j:m)
        A(j,j:m)=A(j,j:m)-A(k,j:m)*A(k,j)/A(k,k);
        after = A(j, j:m)
    end
    A(k,k:m)=A(k,k:m)/sqrt(A(k,k));
    if trace, disp(triu(A)); end
end
R=triu(A);
end
