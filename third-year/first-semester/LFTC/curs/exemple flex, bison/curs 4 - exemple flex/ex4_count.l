%{
	int num_lines=1, num_chars=0;	/*…*/
%}

%option noyywrap

%%
\n   ++num_lines; ++num_chars; 
.    ++num_chars; 

%%
int main()
{
    yylex();
    printf( "# of lines = %d, # of chars = %d\n",
                 num_lines, num_chars );
	return 0;
}
