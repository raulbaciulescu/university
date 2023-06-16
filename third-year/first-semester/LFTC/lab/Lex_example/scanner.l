/*** Definition Section ***/
%{
#include <string.h>


int errorFound = 0;
int lineNumber = 1;


%}


REAL_NUMBER  [+-]?(0|[1-9][0-9]*)(\.[0-9]+)?
IDENTIFIER   [a-z][a-z0-9_]*
STRING       \".*\"

/*** Rule Section ***/
%%
					/* yytext is the text in the buffer */
if                                    	{ printf(yytext); }
while                                   { printf(yytext); }
for                                    	{ printf(yytext); }
write                                   { printf(yytext); }
read                                    { printf(yytext); }
else                                    { printf(yytext); }
int                                    	{ printf(yytext); }
real                                    { printf(yytext); }
char                                    { printf(yytext); }
\<{2}									{ printf("sunt << \n"); }
\;                                    	{ printf(yytext); }
\(                                    	{ printf(yytext); }
\)                                    	{ printf(yytext); }
\,                                    	{ printf(yytext); }
\+                                    	{ printf(yytext); }
\-                                    	{ printf(yytext); }
\*                                    	{ printf(yytext); }
\/                                    	{ printf(yytext); }
\%                                    	{ printf(yytext); }
\>                                    	{ printf(yytext); }
\<                                    	{ printf("sunt < \n"); }
\>=                                    	{ printf(yytext); }
\<=                                    	{ printf(yytext); }
\=                                    	{ printf(yytext); }
\==                                    	{ printf(yytext); }
\and                                    { printf(yytext); }
\or                                    	{ printf(yytext); }
\.                                    	{ printf(yytext); }
\!=                                    	{ printf(yytext); }
{IDENTIFIER}				{  printf("%s is simbol \n", yytext); }
{REAL_NUMBER}                           { printf("%s is simbol \n",yytext); }
{STRING}				{  printf("%s is simbol \n",yytext); }
[\n]					{ ++lineNumber; }
[ \t\n]+             			{ ; /* eat up whitespace */ }
.                			{ errorFound = 1; printf("Illegal token %s at line %d !", yytext, lineNumber); printf("\n"); }
%%

/* yywrap() - wraps the above rule section */
int yywrap() {}

int main(int argc, char** argv) {

	FILE *fp;
	fp = fopen(argv[1], "r");
	
	/* yyin - takes the file pointer which contains the input*/
	yyin = fp;

	/* yylex() - this is the main flex function which runs the Rule Section*/ 
	yylex();
	
  
	return 0;
}