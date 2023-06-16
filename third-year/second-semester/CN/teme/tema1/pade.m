function R = pade(f,m,k)
syms C a R bt vp x
first = zeros(k,k,'sym');
for i = 1:k
    for j = 1:k
        tmp = m + i - j;
        if tmp > 0
            subs(diff(f, x, tmp), 0)
            first(i, j) = subs(diff(f, x, tmp), 0) / factorial(tmp);
        end
    end
end

third = zeros(k, 1, 'sym');
for i = 1:k
    tmp = m + i;
    third(i, 1) = -subs(diff(f, x, tmp), 0) / factorial(tmp);
end
second=zeros(max(m,k)+1, 1, 'sym');
second(2:k+1)=first\third;
second(1)=1;

for j=0:m
    tmp=sym('0');
    for l=0:j
        tmp=tmp+(subs(diff(f, x, j-l), 0) / factorial(j-l))*second(l+1,1);
    end
    a(j+1)=tmp;
end

vp=sum(a.*x.^(0:m));
vq=sum(second(1:k+1)'.*x.^(0:k));
R=vp/vq;
end