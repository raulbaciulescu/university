function [x,ni]=sor(A,b,omega,err,nitmax)
%RELAX Successive overrelaxation (SOR) method
%call [z,ni]=relax(A,b,omega,err,nitmax)
%parameters
%A - system matrix
%b - right hand side vector
%omega - relaxation parameter
%x0 - starting vector
%err - tolerance (default 1e-3)
%nitmax - maximum number of iterations (default 50)
%x - solution
%ni -actual number of iterations

%check parameters
if nargin < 5, nitmax=50; end
if nargin < 4, err=1e-3; end
x0=zeros(size(b));
if (omega<=0) || (omega>=2)
    error('ilegal relaxation parameter')
end
[m,n]=size(A);
if (m~=n) || (n~=length(b))
    error('ilegal size')
end
%compute T and c (prepare iterations)
M=1/omega*diag(diag(A))+tril(A,-1);
N=M-A;
% T=M\N;
% c=M\b;
%alfa=norm(T)
%alfa=max(abs(eig(T)))
x=x0;
for i=1:nitmax
    x0=x;
    x=M\(N*x0+b); 
    %x=T*x+c;
    %if norm(x-x0,inf)<(1-alfa)/alfa*err
    if norm(x-x0,inf)<err*norm(x,inf)
        ni=i;
        return
    end
end
error('iteration number exceeded')