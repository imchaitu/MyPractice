# There are various syntaxes for if condition.
# We will discuss here few of them.
# Using '[ ]', '[[ ]]' and '(( ))'

varValue='testValue'
if [ -z "$varValue" ]; then
    echo "Var is empty"
else
    echo "Var value is: $varValue"
fi
# '[ ]' is basically a 'test' command in bash. This will either return true or false.
# Basing on the above test results, if condition is executed.
# Please note that space after '[' and before ']' is mandatory.
# This is because '[' is an alias to 'test' command you need to give space between command and it's arguments.
# There are various test we can perform
[ "test" = "test" ] && echo "Strings are equal" # Comparing 2 strings are equal.
[ "test" != "best" ] && echo "Strings are not euqual" # True if strings are not equal.
[ "$varValue" = "testValue" ] && echo "Variable value matches" # Comapring variable value to a string. Note that '"' is mandatory around variable.
[ -z "$varVal" ] && echo "String is empty" # True if the given string (or variable) is empty
[ -n "$varVal" ] && echo "String is not empty" # True if string is not empty
[ 4 -eq 4 ] && echo "Numbers are equal" # True if numbers are equal
[ 4 -ne 5 ] && echo "Number are not equal" # True if numbers are not equal
[ 5 -lt 6 ] && echo "n1 is less than n2" # True if n1 is less than n2. n1 -> First number, n2 -> Second number
[ 4 -le 5 ] && echo "n1 is less than or equal to n2" #True if n1 is less than or equal to n2.
[ 5 -gt 3 ] && echo "n1 is greater than n2" # True if n1 is greater than n2.
[ 5 -ge 4 ] && echo "n1 is greater than or equal to n2" # True if n1 is greater than or equal to n2

[ 4 -eq 4 -a 5 -lt 6 ] && echo "True"


