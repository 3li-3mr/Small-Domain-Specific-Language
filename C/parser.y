%{
#include <stdio.h>
#include <stdlib.h>
int yylex();
void yyerror(const char *s);
%}

%token NUMBER

%%

input:

  | input line
  ;

line:
    '\n' 
    | arith '\n'  { printf("%d\n", $1); }
  ;

arith:
    expression {$$ = $1; }
    | arith '+' expression {$$ = $1 + $3; }
    | arith '-' expression {$$ = $1 - $3; }

expression:
    factor  {$$ = $1; }
    | expression '*' factor {$$ = $1 * $3; }
    | expression '/' factor {if($3 == 0) {yyerror("division by zero"); $$ = 0; } else{$$ = $1 / $3; }}

factor:
    NUMBER  {$$ = $1; }
    | '(' arith ')' {$$ = $2; }

%%
void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
}

int main() {
    printf("Calculator started. Enter expressions:\n");
    yyparse();
    return 0;
}