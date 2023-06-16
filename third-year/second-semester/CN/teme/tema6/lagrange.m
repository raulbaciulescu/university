function fi=lagrange(x,y,xi,make_plot)
%LAGRANGE - compute Lagrange interpolation polynomial
%call fi=Lagrange(x,y,xi)
% x,y - nodes coordinates
% xi - evaluation points

if nargin < 3
    error('Illegal number of arguments')
end
if nargin == 3
    make_plot = false;
end

if make_plot
    hold on
    xlabel('l')
    ylabel('x')
end
[mu,nu]=size(xi);
fi=zeros(mu,nu);
np1=length(y);
for i=1:np1
    L=ones(mu,nu);
    for j=1:np1
        if j~=i
            L=L.*(xi-x(j))/(x(i)-x(j));
        end
    end
    if make_plot
            if length(L) == 2
                plot(x(i), L(1), 'ro')
                plot(x(i), L(2), 'g*')
            else
                plot(i, L, 'bo')
            end
        end
    fi=fi+L*y(i);
end

    if make_plot
        hold off
    end
end