garmmar NameList;

list: '[' elements ']' ;
elements: element ( ',' element ) * ;
element: NAME | list ;


NAME: ( a...z | A...Z ) + ;

